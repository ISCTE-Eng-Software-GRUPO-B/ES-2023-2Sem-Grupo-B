package com.iscte.engsoft.grupob.calendarapp.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class DownloadRequest {
    CalendarFormat type;
    String events;
}
