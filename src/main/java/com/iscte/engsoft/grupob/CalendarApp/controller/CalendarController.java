package com.iscte.engsoft.grupob.CalendarApp.controller;

import com.iscte.engsoft.grupob.CalendarApp.model.ConsumeURLCalendarRequest;
import com.iscte.engsoft.grupob.CalendarApp.model.UploadCalendarFileRequest;
import com.iscte.engsoft.grupob.CalendarApp.utils.UrlReader;
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

@RestController
@Validated
@RequestMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class CalendarController {

    @Autowired
    private UrlReader urlReader;

    /**
     * Receives a remote location (url) that contains calendar data and downloads it.
     * http://localhost:8256/calendar/consume/url
     * { "type": "JSON", "url": "https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json"}
     * @return          the calendar in json format
     */
    @PostMapping(value = "/consume/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String consumeUrl(@RequestBody ConsumeURLCalendarRequest request) throws IOException {

        log.info(String.format("Url: %s", request.getUrl()));
        log.info(String.format("UrlType: %s", request.getType()));

        return  urlReader.readFileFromUrl(request.getUrl());

    }

    /**
     * @param request a data type (e.g.: JSON/CSV) MultipartFile
     * @return the calendar data in JSON format
     */
    @PostMapping(path = "/consume/file", consumes = MediaType.ALL_VALUE)
    public String consumeFile(@ModelAttribute UploadCalendarFileRequest request) {
        try {
            String fileContents = new String(request.getFile().getBytes(), StandardCharsets.UTF_8);

            // TODO: if CSV, parse, then try to marshal into a Calendar object
            // TODO: if JSON try to marshal into a calendar object
            // TODO: Save the contents into memory and return the same contents
            // TODO: Above should be done in a different ticket

            return fileContents;
        } catch (IOException e) {
            log.atError().log("Error reading file contents");
        }

        return "";
    }
}
