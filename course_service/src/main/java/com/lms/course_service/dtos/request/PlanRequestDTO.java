package com.lms.course_service.dtos.request;

import com.lms.commonlib.annotations.DateRangeValid;
import com.lms.commonlib.utils.DateRangeContainer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@DateRangeValid
public class PlanRequestDTO implements DateRangeContainer {
    private Long courseId;
    private Long moduleId;

    private int currentChapter;
    private LocalDate startDate;
    private LocalDate endDate;
}
