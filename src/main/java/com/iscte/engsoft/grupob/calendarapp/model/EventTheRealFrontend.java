package com.iscte.engsoft.grupob.calendarapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventTheRealFrontend {
    private String title;

    private String start;

    private String end;

    @Builder.Default
    private boolean editable = true;

    private String description;

    @Builder.Default
    private String color = "blue";

    private EventFrontend originalEvent;
}
