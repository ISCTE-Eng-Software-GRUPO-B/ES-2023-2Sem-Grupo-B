package com.iscte.engsoft.grupob.calendarapp;

import com.iscte.engsoft.grupob.calendarapp.util.CSVConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CSVConverterTest {
    String content = "[{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"07/12/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"30/11/2022\",\"Sala atribuida a aula\":\"C3.01\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"23/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"16/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"09/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MétodosdePesquisaemCiênciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"02/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"26/10/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"}]";
    String wrongContent = "[{\"Cuuuurso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"07/12/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"}]";
    @Test
    void inputShouldBeValidJsonFormat(){

        Assertions.assertTrue(CSVConverter.isValidJSON(content));
    }
    @Test
    void outputShouldBeAnEmptyString(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> CSVConverter.jsonToCSV(wrongContent));
    }
   @Test
    void shouldConvertFromJsonToCsv(){
        Assertions.assertEquals("Curso,\"Unidade Curricular\",Turno,Turma,\"Inscritos no turno\",\"Dia da semana\",\"Hora inicio da aula\",\"Hora fim da aula\",\"Data da aula\",\"Sala atribuida a aula\",\"Lotacao da sala\"\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,07/12/2022,C3.01,54\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,30/11/2022,C3.01,\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,23/11/2022,C3.01,54\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,16/11/2022,C3.01,54\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,09/11/2022,C3.01,54\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MétodosdePesquisaemCiênciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,02/11/2022,C3.01,54\n" +
                "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,26/10/2022,C3.01,54\n" , CSVConverter.jsonToCSV(content));
    }
}
