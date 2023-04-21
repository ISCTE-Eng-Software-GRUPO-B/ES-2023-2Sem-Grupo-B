package com.iscte.engsoft.grupob.calendarapp.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Log4j2
@Component
public class UrlReader {

    public String readFileFromUrl(String url) throws IOException {

        URL urlObj = new URL(url);
        try (BufferedReader in =new BufferedReader(new InputStreamReader(urlObj.openStream()))) {

            StringBuilder output = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }

            return output.toString();
        } catch (IOException e) {
            log.error("Error while trying to consume remote calendar");
        }

        return "";
    }
}
