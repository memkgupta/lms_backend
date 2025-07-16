package com.lms.course_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ModuleRequestDTO {
    @NotBlank
    private String title;
    @Positive
    private Long courseId;
    @NotEmpty
    private List<Long> instructorIds;
    private List<Long> teachingAssistantIds;
    private List<Long> moderatorIds;
}
