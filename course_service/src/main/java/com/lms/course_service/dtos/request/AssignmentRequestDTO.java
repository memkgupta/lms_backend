package com.lms.course_service.dtos.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AssignmentRequestDTO {
    private String title;
    private Integer score;
    private LocalDate deadline;
    private LocalDateTime scheduledAt;
    private Boolean graded;
    private Long uploadedBy;
    private Long courseId;
    private Long chapterId;
    private Long lectureId;
}
