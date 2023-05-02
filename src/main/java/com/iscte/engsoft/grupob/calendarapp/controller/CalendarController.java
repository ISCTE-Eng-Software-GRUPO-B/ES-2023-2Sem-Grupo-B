package com.iscte.engsoft.grupob.calendarapp.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.iscte.engsoft.grupob.calendarapp.model.*;
import com.iscte.engsoft.grupob.calendarapp.util.CSVConverter;
import com.iscte.engsoft.grupob.calendarapp.util.CustomEventFrontendDeserializer;
import com.iscte.engsoft.grupob.calendarapp.util.JSONConverter;
import com.iscte.engsoft.grupob.calendarapp.util.UrlReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class CalendarController {

    @Autowired
    private UrlReader urlReader;

    private List<EventFrontend> listaEventos;

    /**
     * Receives a remote location (url) that contains calendar data and downloads it.
     * @param "type" a data type (e.g.: JSON/CSV)
     * @param "url" the url specifying the calendar in "type" format
     * @return the calendar in json format
     */
    @PostMapping(value = "/consume/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EventFrontend> consumeUrl(@RequestBody ConsumeURLCalendarRequest request) throws IOException {

        log.info(String.format("Url: %s", request.getUrl()));
        log.info(String.format("UrlType: %s", request.getType()));

        String jsonEventsArray = urlReader.readFileFromUrl(request.getUrl());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        SimpleModule module =
                new SimpleModule("CustomEventFrontendDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(EventFrontend.class, new CustomEventFrontendDeserializer());
        objectMapper.registerModule(module);

        //Set instance variable
        this.listaEventos = objectMapper.readValue(jsonEventsArray, new TypeReference<List<EventFrontend>>(){});

        return listaEventos;

    }

    /**
     * @param request a data type (e.g.: JSON/CSV) MultipartFile
     * @return the calendar data in JSON format
     */
    @PostMapping(path = "/consume/file", consumes = MediaType.ALL_VALUE)
    public String consumeFile(@ModelAttribute UploadCalendarFileRequest request) {
        try {
            return new String(request.getFile().getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.atError().log("Error reading file contents");
        }

        return "";
    }

    /**
     * Converts the input file to the opposite type
     * @param request a data type (e.g.: JSON/CSV) MultipartFile
     * @return the calendar data in the opposite format, e.g.: IN -> CSV -> Out -> JSON; IN -> JSON -> Out -> CSV;
     */
    @PostMapping(path = "/convert", consumes = MediaType.ALL_VALUE)
    public String convert(@ModelAttribute UploadCalendarFileRequest request) {
        return request.getType() == CalendarFormat.JSON ?
            CSVConverter.jsonToCSV(request.getFileContents()) :
            JSONConverter.csvToJSON(request.getFileContents());
    }
}
