package com.iscte.engsoft.grupob.CalendarApp.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class ConsumeURLCalendarRequest {

    CalendarFormat type;
    String url;



}
