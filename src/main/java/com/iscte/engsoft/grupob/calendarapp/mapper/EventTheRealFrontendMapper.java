package com.iscte.engsoft.grupob.calendarapp.mapper;

import java.time.LocalDate;
import java.time.LocalTime;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;
import com.iscte.engsoft.grupob.calendarapp.model.EventTheRealFrontend;

public class EventTheRealFrontendMapper {
    public static EventTheRealFrontend fromAnotherEvent(EventFrontend event) {
        return EventTheRealFrontend
            .builder()
            .title(event.getUc())
            .start(convertDateTimeToString(event.getDataAula(), event.getHoraInicio()))
            .end(convertDateTimeToString(event.getDataAula(), event.getHoraFim()))
            .description(event.getCurso())
            .originalEvent(event)
            .build();
    }

    private static String convertDateTimeToString(LocalDate date, LocalTime time) {
        return date.getYear() + "-" + getTwoDigits(date.getMonthValue()) + "-" + getTwoDigits(date.getDayOfMonth()) + "T" +
            time.getHour() + ":" + getTwoDigits(time.getMinute()) + ":" + getTwoDigits(time.getSecond());
    }

    private static String getTwoDigits(int maybeAsingleDigitNumber) {
        return maybeAsingleDigitNumber > 9 ? String.valueOf(maybeAsingleDigitNumber) : "0" + maybeAsingleDigitNumber;
    }
}
