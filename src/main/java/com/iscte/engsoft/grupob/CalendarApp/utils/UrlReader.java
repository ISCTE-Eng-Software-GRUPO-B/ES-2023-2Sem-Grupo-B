package com.iscte.engsoft.grupob.CalendarApp.utils;

import com.iscte.engsoft.grupob.CalendarApp.model.CalendarFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader {

    private UrlReader() {

    }

    public static String readFileFromUrl(String url, CalendarFormat calFormat) throws IOException {

        String calObj;
        URL urlObj = new URL(url);
        try (BufferedReader in =new BufferedReader(new InputStreamReader(urlObj.openStream()))) {

            StringBuilder output = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            calObj = output.toString();
        }

        //TODO
        /*
         * Caso se trate de um JSON, tentar desserializar para um model Calendar
         * de forma a podermos garantir que os campos necessários estão presentes
         * e têm o formato expectável. (Ver Jackson ObjectMapper, lembra-me de te
         * enviar um exemplo amanhã).
         * Caso se trate de um CSV, tentar fazer o parse (via classe utilitária que
         *  o @lourencosoaress implementou).
         * */


        return calObj;

    }
}
