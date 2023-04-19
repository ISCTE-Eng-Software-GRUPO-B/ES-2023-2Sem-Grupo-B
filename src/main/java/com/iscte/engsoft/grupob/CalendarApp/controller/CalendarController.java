package com.iscte.engsoft.grupob.CalendarApp.controller;

import com.iscte.engsoft.grupob.CalendarApp.model.ConsumeURLCalendarRequest;
import com.iscte.engsoft.grupob.CalendarApp.utils.UrlReader;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Validated
@RequestMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CalendarController {

    private static final Logger LOGGER = Logger.getLogger(CalendarController.class.getName());

    @RequestMapping(path = "/test", consumes = MediaType.ALL_VALUE)
    public String test() {
        return "Wonderful day to be alive.";
    }

    /**
     * Receives a remote location (url) that contains calendar data and downloads it.
     * http://localhost:8256/calendar/consume/url
     * { "type": "JSON", "url": "https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json"}
     * @return          the calendar in json format
    */
    @PostMapping(value = "/consume/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getFile(@RequestBody ConsumeURLCalendarRequest request) throws IOException {

        LOGGER.log(Level.INFO, "Url: {0}", request.getUrl());
        LOGGER.log(Level.INFO, "UrlType: {0}", request.getType());

        return UrlReader.readFileFromUrl(request.getUrl(), request.getType());

    }

}
