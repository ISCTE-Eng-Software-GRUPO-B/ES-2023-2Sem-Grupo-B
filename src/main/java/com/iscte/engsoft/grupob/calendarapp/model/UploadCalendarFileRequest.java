package com.iscte.engsoft.grupob.calendarapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@Jacksonized
public class UploadCalendarFileRequest {
    @NonNull
    MultipartFile file;
    @NonNull CalendarFormat type;
}
