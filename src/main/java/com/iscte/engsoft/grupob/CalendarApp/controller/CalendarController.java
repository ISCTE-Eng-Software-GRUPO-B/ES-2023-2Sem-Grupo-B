package com.iscte.engsoft.grupob.CalendarApp.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.iscte.engsoft.grupob.CalendarApp.model.UploadCalendarFileRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/calendar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
@Log4j2
public class CalendarController {

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
