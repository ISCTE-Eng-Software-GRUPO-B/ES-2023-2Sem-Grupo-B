package com.iscte.engsoft.grupob.calendarapp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.iscte.engsoft.grupob.calendarapp.model.*;
import com.iscte.engsoft.grupob.calendarapp.util.UrlReader;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Log4j2
@SpringBootTest
class CalendarControllerTest {

    private static final String validJSON = "[{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"07/12/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"30/11/2022\",\"Sala atribuida a aula\":\"C3.01\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"23/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"16/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"09/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MétodosdePesquisaemCiênciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"02/11/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"},{\"Curso\":\"MCP,MCTRL,MES,MEA,MPP,MS\",\"Unidade Curricular\":\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\"Turno\":\"00553TP02\",\"Turma\":\"MESA1\",\"Inscritos no turno\":\"35\",\"Dia da semana\":\"Qua\",\"Hora inicio da aula\":\"18:00:00\",\"Hora fim da aula\":\"20:00:00\",\"Data da aula\":\"26/10/2022\",\"Sala atribuida a aula\":\"C3.01\",\"Lotacao da sala\":\"54\"}]";

    private static final String validCSV = "Curso,\"Unidade Curricular\",Turno,Turma,\"Inscritos no turno\",\"Dia da semana\",\"Hora inicio da aula\",\"Hora fim da aula\",\"Data da aula\",\"Sala atribuida a aula\",\"Lotacao da sala\"\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,07/12/2022,C3.01,54\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,30/11/2022,C3.01,\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,23/11/2022,C3.01,54\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,16/11/2022,C3.01,54\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,09/11/2022,C3.01,54\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MétodosdePesquisaemCiênciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,02/11/2022,C3.01,54\n" +
        "\"MCP,MCTRL,MES,MEA,MPP,MS\",\"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",00553TP02,MESA1,35,Qua,18:00:00,20:00:00,26/10/2022,C3.01,54";

    @Mock
    private UrlReader urlReader;

    @InjectMocks
    private CalendarController controller;

    @Test
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    void uploadFileSuccess() throws IOException {
        assertFalse(controller.consumeFile(createUploadFileRequest()).isEmpty());
    }

    @Test
    void uploadFileFailsWhileReadingFile() throws IOException {
        assertTrue(controller.consumeFile(createUploadFileRequestWithUnreadableFile()).isEmpty());
    }


    @Test
    void consumeUrlSuccess() throws IOException {

        String output = "[ {\n" +
                "  \"Curso\" : \"MCP,MCTRL,MES,MEA,MPP,MS\",\n" +
                "  \"Unidade Curricular\" : \"MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais\",\n" +
                "  \"Turno\" : \"00553TP02\",\n" +
                "  \"Turma\" : \"MESA1\",\n" +
                "  \"Inscritos no turno\" : \"35\",\n" +
                "  \"Dia da semana\" : \"Qua\",\n" +
                "  \"Hora inicio da aula\" : \"18:00:00\",\n" +
                "  \"Hora fim da aula\" : \"20:00:00\",\n" +
                "  \"Data da aula\" : \"07/12/2022\",\n" +
                "  \"Sala atribuida a aula\" : \"C3.01\",\n" +
                "  \"Lotacao da sala\" : \"54\"\n" +
                "} ]";

        List<EventFrontend> lista = new ArrayList<>();
        EventFrontend event = new EventFrontend();
        event.setCurso("MCP,MCTRL,MES,MEA,MPP,MS");
        event.setUc("MÃƒÂ©todosdePesquisaemCiÃƒÂªnciasSociais");
        event.setTurno("00553TP02");
        event.setTurma("MESA1");
        event.setInscritos(35);
        event.setDiaDaSemana(DiaDaSemana.QUA);
        event.setHoraInicio(LocalTime.parse("18:00:00"));
        event.setHoraFim(LocalTime.parse("20:00:00"));
        event.setDataAula(LocalDate.parse("07/12/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        event.setSalaAtribuida("C3.01");
        event.setLotacao(54);
        lista.add(event);

        ConsumeURLCalendarRequest request =
            ConsumeURLCalendarRequest.builder()
                                     .url("https://raw.githubusercontent.com/ISCTE-Eng-Software-GRUPO-B/ES-2023-2Sem-Sexta-Feira-LIGEPL-GrupoB/main/src/test/resources/test.json")
                                     .type(CalendarFormat.JSON).build();


        Mockito.when(urlReader.readFileFromUrl(
            "https://raw.githubusercontent.com/ISCTE-Eng-Software-GRUPO-B/ES-2023-2Sem-Sexta-Feira-LIGEPL-GrupoB/main/src/test/resources/test.json")
        ).thenReturn(output);

        List<EventFrontend> listaEventos = controller.consumeUrl(request);
        log.info(String.format("List Size: %s", String.valueOf(listaEventos.size())));

        assertEquals(lista, listaEventos);
    }

    @Test
    void convertFileFails() throws IOException {
        assertTrue(controller.convert(createUploadFileRequestWithUnreadableFile()).isEmpty());
    }

    @Test
    void convertFileSuccess() throws IOException {
        assertFalse(controller.convert(createUploadFileRequestWithValidJSON()).isEmpty());
        assertFalse(controller.convert(createUploadFileRequestWithValidCSV()).isEmpty());
    }

    private UploadCalendarFileRequest createUploadFileRequest() throws IOException {
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        when(mockedFile.getBytes()).thenReturn("A not empty String".getBytes());
        return UploadCalendarFileRequest
            .builder()
            .type(CalendarFormat.JSON)
            .file(mockedFile)
            .build();
    }

    private UploadCalendarFileRequest createUploadFileRequestWithValidJSON() throws IOException {
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        when(mockedFile.getBytes()).thenReturn(validJSON.getBytes());
        return UploadCalendarFileRequest
            .builder()
            .type(CalendarFormat.JSON)
            .file(mockedFile)
            .build();
    }

    private UploadCalendarFileRequest createUploadFileRequestWithValidCSV() throws IOException {
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        when(mockedFile.getBytes()).thenReturn(validCSV.getBytes());
        return UploadCalendarFileRequest
            .builder()
            .type(CalendarFormat.CSV)
            .file(mockedFile)
            .build();
    }

    private UploadCalendarFileRequest createUploadFileRequestWithUnreadableFile() throws IOException {
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        when(mockedFile.getBytes()).thenThrow(new IOException());
        return UploadCalendarFileRequest
            .builder()
            .type(CalendarFormat.JSON)
            .file(mockedFile)
            .build();
    }
}