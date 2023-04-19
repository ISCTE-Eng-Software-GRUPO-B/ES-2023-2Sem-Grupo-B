/*
 * Tradução do calendário para portugês e configurações
 * @author Diogo Rodrigues
 */
//Tradução
(function ($) {
    $.fullCalendar.locale("calendarioPortugues", {
        buttonText: {
            month: "Mês",
            week: "Semana",
            day: "Dia",
            list: "Eventos",
            today: "Hoje",
        },
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
        dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
        monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril',
            'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        allDayHtml: "Todo o dia",
        eventLimitText: function (n) {
            return "mais +" + n + "...";
        },
        noEventsMessage: "Sem eventos nesta semana",
        titleFormat: 'D [de] MMMM, YYYY',

    });
})(jQuery);


//Configurações
$(document).ready(function () {
    $('#calendar').fullCalendar({
        allDaySlot: false,

        events: [
            {
                title: 'Evento exemplo',
                start: '2023-04-18T10:08:30',
                end: '2023-04-18T11:23:00',
                editable: true,
                description: "Só para ver se aparece",
                color: 'red',
            },
            {
                title: 'Evento exemplo 2',
                start: '2023-04-18T10:08:30',
                end: '2023-04-18T12:23:00',
                editable: true,
                description: "Só para ver se aparece",
            }
        ],

        // A trabalhar - adiciona um pop-up ao evento
        eventClick: function (event) {
            var popup = window.open('', 'popUpWindow', 'height=200,width=400,left=200,top=200');
            popup.document.write('<p>' + event.description + '</p>');
            popup.document.write('<title>' + "Evento" + '</title>');
            console.log("ola a todos");
        },
        height: 600,

        defaultView: 'agendaWeek',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay, listWeek'
        },

        locale: 'calendarioPortugues',

        //themeSystem: 'bootstrap4',
        columnHeaderFormat: 'dddd DD/MM',

        // Configurações de Tempo
        slotLabelFormat: 'HH:mm',
        slotDuration: '00:30:00',
        minTime: '08:30:00',
        maxTime: '23:00:00',


    });

});
