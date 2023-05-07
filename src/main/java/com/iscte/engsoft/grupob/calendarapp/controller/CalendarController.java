package com.iscte.engsoft.grupob.calendarapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iscte.engsoft.grupob.calendarapp.mapper.EventTheRealFrontendMapper;
import com.iscte.engsoft.grupob.calendarapp.model.*;
import com.iscte.engsoft.grupob.calendarapp.util.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Validated
@RequestMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class CalendarController {

    @Autowired
    private UrlReader urlReader;

    private List<EventFrontend> listaEventos = Collections.emptyList();

    /**
     * @return the calendar already persisted data in JSON format
     */
    @GetMapping(path = "/get", consumes = MediaType.ALL_VALUE)
    public List<EventTheRealFrontend> get() {
        return getFrontEndEvents();
    }

    /**
     * Recebe uma url com contém um calendário com eventos e que pode estar em
     * diferentes formatos (WEBCAL, JSON ou CSV) e faz o donwload desse calendário
     * para uma lista de eventos em memória
     * @param request com dois campos internos (type e url)
     *          request.type: enumerado com valores possíveis JSON, WEBCAL ou CSV
     *          request.url: a url que está na origem do calendário a fazer o donwload
     * @return lista de eventos obtidos.
     */
    @PostMapping(value = "/consume/url", consumes = MediaType.ALL_VALUE)
    public RedirectView consumeUrl(@ModelAttribute ConsumeURLCalendarRequest request) throws IOException {
        String url = request.getUrl();

        if (request.getType() == CalendarFormat.WEBCAL){
            url = url.replace("webcal://", "https://");
        }

        String content = urlReader.readFileFromUrl(url);

        UrlProcessor urlProcessor = switch (request.getType()) {
            case JSON -> new UrlProcessorJson();
            case WEBCAL -> new UrlProcessorWebcal();
            case CSV -> new UrlProcessorCsv();
            default -> new UrlProcessorJson();
        };
        this.listaEventos = urlProcessor.parseUrlContent(content);

        return new RedirectView("/index.html");
    }


    /**
     * @param request a data type (e.g.: JSON/CSV) MultipartFile
     * @return the calendar data in JSON format
     */
    @PostMapping(path = "/consume/file", consumes = MediaType.ALL_VALUE)
    public RedirectView consumeFile(@ModelAttribute UploadCalendarFileRequest request) {
        try {
            UrlProcessor urlProcessor = switch (request.getType()) {
                case JSON -> new UrlProcessorJson();
                case CSV -> new UrlProcessorCsv();
                default -> new UrlProcessorJson();
            };

            this.listaEventos = urlProcessor.parseUrlContent(new String(request.getFile().getBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.atError().log("Error reading file contents");
        }

        return new RedirectView("/index.html");
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


    @PostMapping(path = "/upload/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void uploadJson(@ModelAttribute UploadJsonRequest request) {
        try {
            UrlProcessorJson processor = new UrlProcessorJson();
            this.listaEventos = processor.parseUrlContent(request.getJsonContent());
        } catch (IOException e) {
            log.atError().log("Error reading file contents");
        }
    }

    @PostMapping(path = "/download", consumes = MediaType.ALL_VALUE)
    public String download(@ModelAttribute DownloadRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            listaEventos = Arrays.stream(objectMapper.readValue(request.getEvents(), EventFrontend[].class)).toList();
        } catch (IOException e) {
            log.atError().log("Error reading frontend events");
        }

        return request.getEvents();
    }

    private List<EventTheRealFrontend> getFrontEndEvents() {
        return listaEventos.stream().map(EventTheRealFrontendMapper::fromAnotherEvent).collect(Collectors.toList());
    }



}
