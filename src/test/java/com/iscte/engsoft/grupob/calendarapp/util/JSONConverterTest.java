package com.iscte.engsoft.grupob.calendarapp.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Log4j2
class JSONConverterTest {

    String content = "Curso,\"Unidade Curricular\",Turno,Turma,\"Inscritos no turno\",\"Dia da semana\",\"Hora inicio da aula\",\"Hora fim da aula\",\"Data da aula\",\"Sala atribuida a aula\",\"Lotacao da sala\"\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,07/12/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,30/11/2022,C3.01,\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,23/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,16/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,09/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MétodosdePesquisaemCiênciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,02/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,26/10/2022,C3.01,54";

    String wrongContent = "Cu,\"Unidade Curricular\",Turno,Turma,\"Inscritos no turno\",\"Dia da semana\",\"Hora inicio da aula\",\"Hora fim da aula\",\"Data da aula\",\"Sala atribuida a aula\",\"Lotacao da sala\"\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,07/12/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,30/11/2022,C3.01,\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,23/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,16/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,09/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MétodosdePesquisaemCiênciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,02/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,26/10/2022,C3.01,54";

    @Test
    void inputShouldBeValidCsvFormat() {
        Assertions.assertTrue(JSONConverter.isValidCSV(content));
    }
    @Test
    void outputShouldBeAnEmptyString() {
        Assertions.assertEquals("", JSONConverter.csvToJSON(wrongContent));
    }
    @Test
    void shouldConvertFromCsvToJson() {
        try {
            File file = ResourceUtils.getFile("classpath:test.json");

            byte[] byteArray= Files.readAllBytes(file.toPath());
            CharsetDecoder decoder = Charset.forName(String.valueOf(StandardCharsets.UTF_8)).newDecoder();
            CharBuffer charBuffer = decoder.decode( ByteBuffer.wrap( byteArray ) );
            String content1 = charBuffer.toString();

            String content2 = JSONConverter.csvToJSON(content);

            log.info(String.format("File from disk: %s", content1));
            log.info(String.format("File from csvToJSON: %s", content2));

            Assertions.assertEquals(content1, content2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
