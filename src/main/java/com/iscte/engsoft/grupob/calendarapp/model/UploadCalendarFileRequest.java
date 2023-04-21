package com.iscte.engsoft.grupob.calendarapp.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Builder
@Value
@Jacksonized
public class UploadCalendarFileRequest {
    @NonNull
    MultipartFile file;

    @NonNull
    CalendarFormat type;

    public String getFileContents() {
        try {
            return new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.atError().log("Error reading file contents");
        }

        return "";
    }
}
