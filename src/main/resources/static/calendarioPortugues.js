/*
* Tradução do calendário para português e configurações
*/

// Tradução
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

// Configurações
$(document).ready(function () {
    $('#calendar').fullCalendar({
        allDaySlot: false,
        events: {
            url: 'http://localhost:8256/calendar/get',
            type: 'GET',
            contentType: 'application/json',
        },
        height: 600,
        defaultView: 'agendaWeek',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay, listWeek'
        },
        locale: 'calendarioPortugues',
        columnHeaderFormat: 'dddd DD/MM',
        slotLabelFormat: 'HH:mm',
        slotDuration: '00:30:00',
        minTime: '08:00:00',
        maxTime: '23:00:00',
        eventClick: function (event) {
            var popup = window.open('', 'popUpWindow', 'height=200,width=400,left=200,top=200');
            var content = '<p>' + event.description + '</p>';
            content += '<button class="remove-event">Remover</button>';
            popup.document.write(content);
            popup.document.write('<title>' + "Evento" + '</title>');
            $(popup.document).find('.remove-event').click(function() {
                $('#calendar').fullCalendar('removeEvents', event._id);
                popup.close();
            });
        },
    });

    // retorna o calendário personalizado
    $('#download').click(function() {
        var allEvents = $('#calendar').fullCalendar('clientEvents');
        const json = JSON.stringify(events);
    });
});
