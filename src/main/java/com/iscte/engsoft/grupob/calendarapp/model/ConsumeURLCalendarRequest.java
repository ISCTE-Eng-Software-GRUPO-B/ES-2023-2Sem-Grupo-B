package com.iscte.engsoft.grupob.calendarapp.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class ConsumeURLCalendarRequest {

    CalendarFormat type;
    String url;



}
