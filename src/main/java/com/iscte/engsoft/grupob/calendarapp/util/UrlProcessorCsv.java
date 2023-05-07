package com.iscte.engsoft.grupob.calendarapp.util;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;

import java.io.IOException;
import java.util.List;

/**
 * Esta classe estende a classe UrlProcessor para fazer o parsing de conteúdo CSV
 * */
public class UrlProcessorCsv extends UrlProcessor{

    public List<EventFrontend> parseUrlContent(String content) throws IOException {

        String jsonContent = JSONConverter.csvToJSON(content);
        UrlProcessorJson processorJson = new UrlProcessorJson();
        List<EventFrontend> list = processorJson.parseUrlContent(jsonContent);

        return list;

    }

}
