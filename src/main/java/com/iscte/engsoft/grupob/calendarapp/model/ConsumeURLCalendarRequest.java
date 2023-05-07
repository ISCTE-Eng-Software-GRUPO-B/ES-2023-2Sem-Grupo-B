package com.iscte.engsoft.grupob.calendarapp.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Classe que representa o request HTTP que identifica o tipo de calendário (CSV, WEBCAL, JSON)
 * e a URL onde a aplicação irá ler o mesmo
 */
@Builder
@Value
@Jacksonized
public class ConsumeURLCalendarRequest {
    CalendarFormat type;
    String url;
}
