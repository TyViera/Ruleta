
$(function () {

    $('.form-control-bet').on('submit', function (e) {
        e.preventDefault();
        var formObject = $(this);
        var data = formObject.serializeObject();
        console.log(data); return;
        if (data.username) {
            formObject.fadeOut("fast", "linear", function () {
                consultarUsuario(data);
            });
        } else {
            swal("Error", "¡Debe ingresar su usuario!", "error");
        }
    });

    $('.bet-type-form').on('change', function (e) {
        var typeValueForm = $('.bet-type-form').val();
        console.log(typeValueForm)
        var typeValue;
        typeValue = undefined;
        for (var i = 0; i < fullInformationBets.length; i++) {
            if (typeValueForm == fullInformationBets[i].id) {
                typeValue = fullInformationBets[i].type;
            }
        }

        var number = false,
            even = false,
            colour = false,
            half = false;
        if (typeValue == 'N') {
            //number
            number = true;
        } else if (typeValue == 'C') {
            //Colour
            colour = true;
        } else if (typeValue == 'E') {
            //Even/Odd
            even = true;
        } else if (typeValue == 'H') {
            //High/low
            half = true;
        }

        if (number) {
            $('.form-group-number').removeClass('hidden');
        } else {
            $('.form-group-number').addClass('hidden');
        }

        if (even) {
            $('.form-group-even').removeClass('hidden');
        } else {
            $('.form-group-even').addClass('hidden');
        }

        if (colour) {
            $('.form-group-colour').removeClass('hidden');
        } else {
            $('.form-group-colour').addClass('hidden');
        }

        if (half) {
            $('.form-group-half').removeClass('hidden');
        } else {
            $('.form-group-half').addClass('hidden');
        }
    });

});
//populate table
function loadRouletteGame() {
    $.get(baseURL + "roulette/game", function (data) {
        if (!data) {
            swal("Error", "Ocurrió un error al obtener los datos.", "error");
            return;
        }
        if (!data.info) {
            //no info
            swal("Sin datos");
        } else {
            //numbers
            var htmlF, htmlS, htmlT, dataTD;
            htmlF = '<tr class="nums">';
            htmlS = '<tr class="nums">';
            htmlT = '<tr class="nums">';

            for (var i = 0; i < data.info.length; i++) {
                dataTD = '<td class="num ' + data.info[i].colour;
                if (i == 0) {
                    dataTD += ' zero" rowspan="3"';
                } else {
                    dataTD += '"';
                }
                dataTD += '>';
                dataTD += ' <span>' + data.info[i].number + '</span>';
                dataTD += '</td>';
                switch (i % 3) {
                    case 0:
                        htmlF += dataTD;
                        break;
                    case 1:
                        htmlT += dataTD;
                        break;
                    case 2:
                        htmlS += dataTD;
                        break;
                }
            }
            htmlF += '</tr>';
            htmlS += '</tr>';
            htmlT += '</tr>';
            $('.table-roulette').html(htmlF + htmlS + htmlT);

        }

    }).fail(function () {
        swal("Error", "Error al conectarse al backend", "error");
    });
}

function setHeaderInfoForUser(user) {
    var baseText = 'Hola: ' + user.username + ', actualmente tienes ' + user.points + ' puntos de crédito.';
    $('.text-info-greet').text(baseText);
}

function saveDataUserToForm(user) {
    $('.username-bet').val(user.username);
    $('.username-points').val(user.points);

}

function loadBets() {
    fullInformationBets = [];
    $.get(baseURL + "roulette/bets", function (data) {
        if (!data) {
            swal("Error", "Ocurrió un error al obtener los datos.", "error");
            return;
        }
        if (!data.info) {
            //no info
            swal("Sin datos");
        } else {
            //numbers
            var optionsHtml = '';
            fullInformationBets = data.info;
            for (var i = 0; i < fullInformationBets.length; i++) {
                optionsHtml += '<option value="' + fullInformationBets[i].id + '">' + fullInformationBets[i].name + '</option>'
            }
            $('.bet-type-form').html(optionsHtml);
            $('.bet-type-form').trigger('change');
        }

    }).fail(function () {
        swal("Error", "Error al conectarse al backend", "error");
    });
}
