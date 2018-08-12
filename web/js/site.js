function passtoB64(texto) {
    //var elem = $("div")[0].outerHTML;
    var blob = new Blob([texto], {
        "type": "text/html"
    });
    var reader = new FileReader();
    reader.onload = function (evt) {
        if (evt.target.readyState === 2) {
            /*console.log(
                        // full data-uri
                        evt.target.result
                        // base64 portion of data-uri
                    , evt.target.result.slice(22, evt.target.result.length));*/
            //window.open(evt.target.result)
            console.log(evt.target.result);
        };
    };
    reader.readAsDataURL(blob);
}

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$(function () {
    var baseURL = "http://localhost:8080/"
    $('.form-control-register').fadeOut("fast", "linear");

    $('.form-control-login').on('submit', function (e) {
        e.preventDefault();
        var formObject = $(this);
        var data = formObject.serializeObject();
        if (data.username) {
            formObject.fadeOut("fast", "linear", function () {
                consultarUsuario(data);
            });
        } else {
            swal("¡Debe ingresar su usuario!");
        }

    });

    $('.form-control-register').on('submit', function (e) {
        e.preventDefault();
        var formObject = $(this);
        var data = formObject.serializeObject();
        if (data.fullName) {
            registerUser(data);
        } else {
            swal("¡Debe ingresar su nombre completo!");
        }

    });

    function registerUser(user) {
        $.ajax({
            type: "POST",
            url: baseURL + "user/create",
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                if (!data) {
                    swal("Ocurrió un error al obtener los datos.");
                    return;
                }
                if (data.status === 'SUCCESS') {
                    //todo ok, pedir silla
                    getChairRoulette(user);
                } else {
                    swal(data.message);
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Error al conectarse al backend");
            }
        });
    }

    function consultarUsuario(user) {
        $.get(baseURL + "user/" + user.username, function (data) {
            if (!data) {
                swal("Ocurrió un error al obtener los datos.");
                return;
            }
            if (!data.info) {
                //usuario no registrado
                $('#username-txt').val(user.username);
                $('.form-control-register').fadeIn("fast", "linear");
            } else {
                $('#contenido-juego').fadeOut("fast", "linear", mostrarContenidoJuego);
                getChairRoulette(data.info);
            }

        }).fail(function () {
            swal("Error al conectarse al backend");
        });
    }

    function getChairRoulette(user) {
        console.log(user);
        swal('bienvenido: ' + user.fullName + ' buscaremos una silla para ti, por favor espera...');
    }

    function mostrarContenidoJuego() {
        $.get(window.location.origin + window.location.pathname + "partials/game.html", function (htmlexterno) {
            $('#contenido-juego').html(htmlexterno);
            $('#contenido-juego').fadeIn("fast", "linear");
        });

    }

});