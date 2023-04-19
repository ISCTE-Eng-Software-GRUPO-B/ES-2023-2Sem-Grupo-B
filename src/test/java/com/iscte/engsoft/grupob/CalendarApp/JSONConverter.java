package com.iscte.engsoft.grupob.CalendarApp;

import lombok.experimental.NonFinal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSONConverter {

    String content =  "Curso,\"Unidade Curricular\",Turno,Turma,\"Inscritos no turno\",\"Dia da semana\",\"Hora inicio da aula\",\"Hora fim da aula\",\"Data da aula\",\"Sala atribuida a aula\",\"Lotacao da sala\"\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,07/12/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,30/11/2022,C3.01,\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,23/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,16/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,09/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MétodosdePesquisaemCiênciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,02/11/2022,C3.01,54\n" +
            "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,26/10/2022,C3.01,54";

    com.iscte.engsoft.grupob.CalendarApp.util.JSONConverter converter = new com.iscte.engsoft.grupob.CalendarApp.util.JSONConverter();

    @Test
    void input_should_be_valid_csv_format(){ Assertions.assertTrue(converter.isValidCSV(content)); }

    @Test
    void should_convert_from_csv_to_json(){ Assertions.assertNotNull(converter.csvToJSON(content)); }
}
