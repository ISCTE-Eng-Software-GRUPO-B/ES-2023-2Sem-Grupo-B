package com.iscte.engsoft.grupob.calendarapp.util;

import java.io.IOException;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Log4j2
class UtilsTest {

    @Test
    void readFileFromUrlTest()  throws IOException {

        UrlReader reader = new UrlReader();
        String resultJson = reader.readFileFromUrl("https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json");

        log.info(String.format("resultJson: %s", resultJson));

        assertNotEquals(-1, resultJson.indexOf("aliceblue"));

    }
}
