package com.lms.course_service.dtos.request;


import com.lms.commonlib.annotations.DateRangeValid;
import com.lms.commonlib.utils.DateRangeContainer;
import com.lms.course_service.entities.modules.plan.chapter.CompletionStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@DateRangeValid
@Data
public class ChapterRequestDTO implements DateRangeContainer {
    private String title;
    // IDs instead of entities for simpler requests
    private Long courseId;
    private Long moduleId;
    private Long planId;
    private CompletionStatus completionStatus;
    private Long instructorAssigned;
    private LocalDate startDate;
    private LocalDate endDate;
    private int currentLecture;
    private int noOfLectures;
}
