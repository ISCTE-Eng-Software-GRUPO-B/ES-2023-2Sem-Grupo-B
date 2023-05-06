package com.iscte.engsoft.grupob.calendarapp.util;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;

import java.io.IOException;
import java.util.List;

public abstract class UrlProcessor {

    public abstract List<EventFrontend> parseUrlContent(String content) throws IOException;


}
