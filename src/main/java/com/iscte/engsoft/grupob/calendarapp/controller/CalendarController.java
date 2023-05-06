package com.iscte.engsoft.grupob.calendarapp.controller;

import com.iscte.engsoft.grupob.calendarapp.model.*;
import com.iscte.engsoft.grupob.calendarapp.util.*;
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
     * Recebe uma url com contém um calendário com eventos e que pode estar em
     * diferentes formatos (WEBCAL, JSON ou CSV) e faz o donwload desse calendário
     * para uma lista de eventos em memória
     * @param request com dois campos internos (type e url)
     *          request.type: enumerado com valores possíveis JSON, WEBCAL ou CSV
     *          request.url: a url que está na origem do calendário a fazer o donwload
     * @return lista de eventos obtidos.
     */
    @PostMapping(value = "/consume/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<EventFrontend> consumeUrl(@RequestBody ConsumeURLCalendarRequest request) throws IOException {

        log.info(String.format("Url: %s", request.getUrl()));
        log.info(String.format("UrlType: %s", request.getType()));

        String url = request.getUrl();

        //for webcal we need to change the protocol from webcal:// to https:// (for fenix) //TODO: improve
        if (request.getType()==CalendarFormat.WEBCAL){
            url = url.replace("webcal://", "https://");
        }

        String content = urlReader.readFileFromUrl(url);

        CalendarFormat type =  request.getType();
        UrlProcessor urlProcessor = null; //usa poliformismo para o parse
        switch (type) {
            case JSON:
                urlProcessor = new UrlProcessorJson();
                break;
            case WEBCAL:
                urlProcessor = new UrlProcessorWebcal();
                break;
            case CSV:
                urlProcessor = new UrlProcessorCsv();
                break;
            default:
                urlProcessor = new UrlProcessorJson();
        }
        this.listaEventos = urlProcessor.parseUrlContent(content);
        return this.listaEventos;

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
