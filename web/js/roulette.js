
$(function () {

    $('.form-control-bet').on('submit', function (e) {
        e.preventDefault();
        var formObject = $(this);
        var data = formObject.serializeObject();
        var typeValueForm = $('.bet-type-form').val();
        var typeValue, selectedValue;
        var dataSend;
        typeValue = undefined;

        if (!data.betPoints) {
            swal("Error", "¡Debe ingresar los puntos a apostar!", "error");
            return;
        }
        console.log(data['user.points'])
        if (parseInt(data.betPoints) > data['user.points']) {
            swal("Error", "No hay puntos suficientes para esta jugada", "error");
            return;
        }

        for (var i = 0; i < fullInformationBets.length; i++) {
            if (typeValueForm == fullInformationBets[i].id) {
                selectedValue = fullInformationBets[i];
                typeValue = fullInformationBets[i].type;
                i = fullInformationBets.length;
            }
        }

        dataSend = {
            id: null,
            wonPoints: 0,
            betPoints: data.betPoints,
            selectedBet: '',
            user: {
                username: data['user.username']
            },
            bet: {
                id: typeValueForm
            }
        };

        if (typeValue == 'N') {
            //number
            if (!data.selectedBetNumbers) {
                swal("Error", "Debe ingresar los numeros a apostar", "error");
                return;
            }

            dataSend.selectedBet = data.selectedBetNumbers;
            var numeros = dataSend.selectedBet.split(',');
            if (numeros.length > selectedValue.betCount) {
                swal("Error", "Solo se pueden ingresar " + selectedValue.betCount + " número(s)", "error");
                return;
            }
            for (var i = 0; i < numeros.length; i++) {
                if (numeros[i] > 36) {
                    swal("Error", "El valor máximo es 36", "error");
                    return;
                }
            }
        } else if (typeValue == 'C') {
            //Colour
            if (!data.selectedBetColour) {
                swal("Error", "Debe seleccionar el color a apostar", "error");
                return;
            }
            dataSend.selectedBet = data.selectedBetColour;
        } else if (typeValue == 'E') {
            //Even/Odd
            if (!data.selectedBetEven) {
                swal("Error", "Debe seleccionar la apuesta", "error");
                return;
            }
            dataSend.selectedBet = data.selectedBetEven;
        } else if (typeValue == 'H') {
            //High/low
            if (!data.selectedBetHalf) {
                swal("Error", "Debe seleccionar la apuesta", "error");
                return;
            }
            dataSend.selectedBet = data.selectedBetHalf;
        }
        console.log(data); //return;
        console.log(dataSend);

        $.ajax({
            type: "POST",
            url: baseURL + "roulette/betuser",
            data: JSON.stringify(dataSend),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (dataResponse) {
                console.log(dataResponse);
                if (!dataResponse) {
                    swal("Error", "Ocurrió un error al obtener los datos.", "error");
                    return;
                }
                if (dataResponse.status === 'SUCCESS') {
                    saveDataUserToForm(dataResponse.info.user);
                    setHeaderInfoForUser(dataResponse.info.user);
                    if (dataResponse.info.wonPoints > 0) {
                        //you win
                        swal("¡Ganador!", "Usted gana " + dataResponse.info.wonPoints + " puntos, el número que salió fue: " + dataResponse.info.luckyNumber, "success");
                    } else {
                        //
                        swal("¡Perdió!", "Usted pierde, el número que salió fue: " + dataResponse.info.luckyNumber, "error");
                    }
                } else {
                    swal(dataResponse.message);
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Error", "Error al conectarse al backend", "error");
            }
        });

    });

    $('.bet-type-form').on('change', function (e) {
        var typeValueForm = $('.bet-type-form').val();
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
