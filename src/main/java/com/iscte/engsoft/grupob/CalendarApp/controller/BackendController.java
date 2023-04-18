package com.iscte.engsoft.grupob.CalendarApp.controller;

import com.iscte.engsoft.grupob.CalendarApp.model.UrlFile;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Validated
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BackendController {

    private static final Logger LOGGER = Logger.getLogger(BackendController.class.getName());

    @RequestMapping(path = "/test", consumes = MediaType.ALL_VALUE)
    public String test() {
        return "Wonderful day to be alive.";
    }

    /**
     * Returns the json or csv file from the specified URL
     * to run use, e.g. , Postman with an HTTP POST request with the json body
     * http://localhost:8256/get-file-from-url
     * { "type": "JSON", "url": "https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json"}
     * @return          the file in specified url
    */
    @PostMapping(value = "/get-file-from-url")
    public String getFile(@RequestBody UrlFile request) throws IOException {

        LOGGER.log(Level.INFO, "Url: {0}", request.getUrl());
        LOGGER.log(Level.INFO, "UrlType: {0}", request.getType());

        URL url = new URL(request.getUrl());
       try (BufferedReader in =new BufferedReader(new InputStreamReader(url.openStream()))) {

           StringBuilder output = new StringBuilder();
           String inputLine;
           while ((inputLine = in.readLine()) != null) {
               output.append(inputLine);
           }
           return output.toString();
       }
    }

}
