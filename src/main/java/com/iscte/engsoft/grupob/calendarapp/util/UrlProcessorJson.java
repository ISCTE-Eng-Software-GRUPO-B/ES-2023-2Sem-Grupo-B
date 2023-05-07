package com.iscte.engsoft.grupob.calendarapp.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;

import java.io.IOException;
import java.util.List;

/**
 * Esta classe estende a classe UrlProcessor para fazer o parsing de conteúdo JSON
 * */
public class UrlProcessorJson extends UrlProcessor{

    public List<EventFrontend> parseUrlContent(String content) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        SimpleModule module =
                new SimpleModule("CustomEventFrontendDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(EventFrontend.class, new CustomEventFrontendDeserializer());
        objectMapper.registerModule(module);

        //Set instance variable
        return objectMapper.readValue(content, new TypeReference<List<EventFrontend>>(){});


    }

}
