package com.iscte.engsoft.grupob.calendarapp.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Esta classe permite a leitura de uma url independentemente do formato
 * (desde que seja formato de texto).
* */
@Log4j2
@Component
public class UrlReader {

    /**
     * Lê o conteúdo de uma URL
     * @param url string com a url a ler
     * @return string com o conteúdo lido da url
     */
    public String readFileFromUrl(String url) throws IOException {

        URL urlObj = new URL(url);
        try (BufferedReader in =new BufferedReader(new InputStreamReader(urlObj.openStream()))) {

            StringBuilder output = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine+"\r\n");
            }

            return output.toString();
        } catch (IOException e) {
            log.error("Error while trying to consume remote calendar");
        }

        return "";
    }
}
