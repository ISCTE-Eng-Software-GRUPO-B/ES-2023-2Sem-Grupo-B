package com.iscte.engsoft.grupob.calendarapp.util;

import lombok.extern.log4j.Log4j2;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Esta classe permite converter a data e a hora para o formato apropriado para
 * a api do calendário em JavaScript
 */
@Log4j2
public class DateConverter {

    private DateConverter() {}

    public static final String DATE_TIME_FORMAT = "yyyyMMddTHHmmssZ";

    public static final String TIME_FORMAT = "HHmmss";

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static LocalDateTime parseToLocalDateTime(String timestamp) {
        return  LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static LocalDate parseToLocalDate(String timestamp) {
        return  LocalDate.parse(timestamp, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static LocalTime parseToLocalTime(String timestamp) {
        return  LocalTime.parse(timestamp, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

}
