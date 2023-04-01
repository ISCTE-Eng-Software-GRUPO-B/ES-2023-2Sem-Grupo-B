package com.iscte.engsoft.grupob.CalendarApp.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BackendController {

    @RequestMapping(path = "/test", consumes = MediaType.ALL_VALUE)
    public String test() {
        return "Wonderful day to be alive.";
    }
}
