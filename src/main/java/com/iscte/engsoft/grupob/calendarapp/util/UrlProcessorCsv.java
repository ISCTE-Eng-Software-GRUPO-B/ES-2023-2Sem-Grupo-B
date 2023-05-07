package com.iscte.engsoft.grupob.calendarapp.util;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlProcessorCsv extends UrlProcessor{

    public List<EventFrontend> parseUrlContent(String content) throws IOException {
        String jsonContent = JSONConverter.csvToJSON(content);
        UrlProcessorJson processorJson = new UrlProcessorJson();

        return processorJson.parseUrlContent(jsonContent);
    }

}
