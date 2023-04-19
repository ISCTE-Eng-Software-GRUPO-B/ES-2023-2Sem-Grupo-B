package com.iscte.engsoft.grupob.CalendarApp.controller;

import java.io.IOException;

import com.iscte.engsoft.grupob.CalendarApp.model.FileType;
import com.iscte.engsoft.grupob.CalendarApp.model.UploadCalendarFileRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalendarControllerTest {
    @Autowired
    CalendarController controller;

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

    private UploadCalendarFileRequest createUploadFileRequest() throws IOException {
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        when(mockedFile.getBytes()).thenReturn("A not empty String".getBytes());
        return UploadCalendarFileRequest
            .builder()
            .type(FileType.JSON)
            .file(mockedFile)
            .build();
    }

    private UploadCalendarFileRequest createUploadFileRequestWithUnreadableFile() throws IOException {
        MultipartFile mockedFile = Mockito.mock(MultipartFile.class);
        when(mockedFile.getBytes()).thenThrow(new IOException());
        return UploadCalendarFileRequest
            .builder()
            .type(FileType.JSON)
            .file(mockedFile)
            .build();
    }
}