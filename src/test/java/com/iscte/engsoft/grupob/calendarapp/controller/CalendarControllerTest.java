package com.iscte.engsoft.grupob.calendarapp.controller;

import java.io.IOException;

import com.iscte.engsoft.grupob.calendarapp.model.CalendarFormat;
import com.iscte.engsoft.grupob.calendarapp.model.ConsumeURLCalendarRequest;
import com.iscte.engsoft.grupob.calendarapp.model.UploadCalendarFileRequest;
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
        ConsumeURLCalendarRequest request =
            ConsumeURLCalendarRequest.builder()
                                     .url("https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json")
                                     .type(CalendarFormat.JSON).build();

        Mockito.when(urlReader.readFileFromUrl(
            "https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json")
        ).thenReturn("JsonResult");

        String resultJson = controller.consumeUrl(request);
        log.info(String.format("resultJson: %s", resultJson));

        assertEquals("JsonResult", resultJson);

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