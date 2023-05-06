package com.iscte.engsoft.grupob.calendarapp.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Log4j2
class UtilsTest {

    @Test
    void readFileFromUrlTest()  throws IOException {

        UrlReader reader = new UrlReader();
        String resultJson = reader.readFileFromUrl("https://raw.githubusercontent.com/ISCTE-Eng-Software-GRUPO-B/ES-2023-2Sem-Sexta-Feira-LIGEPL-GrupoB/main/src/test/resources/test.json");

        //log.info(String.format("resultJson: %s", resultJson));

        String expected = "\"Unidade Curricular\" : \"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",";
        byte[] byteArray= expected.getBytes();
        CharsetDecoder decoder = Charset.forName(String.valueOf(StandardCharsets.UTF_8)).newDecoder();
        CharBuffer charBuffer = decoder.decode( ByteBuffer.wrap( byteArray ) );
        String expectedUTF_8 = charBuffer.toString();


        assertNotEquals(-1, resultJson.indexOf(expectedUTF_8));

    }
}
