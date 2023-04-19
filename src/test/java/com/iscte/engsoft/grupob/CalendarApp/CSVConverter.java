package com.iscte.engsoft.grupob.CalendarApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CSVConverter {
    String content = "[{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"07/12/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"30/11/2022\",\"Sala atribuida a aula\":\"C3.01\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"23/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"16/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"09/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MétodosdePesquisaemCiênciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"02/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"26/10/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"}]";

    @Test
    void input_should_be_valid_json_format(){ Assertions.assertTrue(com.iscte.engsoft.grupob.CalendarApp.util.CSVConverter.isValidJSON(content)); }

    @Test
    void should_convert_from_json_to_csv(){ Assertions.assertNotNull(com.iscte.engsoft.grupob.CalendarApp.util.CSVConverter.jsonToCSV(content)); }
}
