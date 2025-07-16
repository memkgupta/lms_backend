package com.lms.course_service.dtos.request;

import com.lms.commonlib.annotations.DateRangeValid;
import com.lms.commonlib.utils.DateRangeContainer;
import com.lms.course_service.entities.course.CourseTypeEnum;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@DateRangeValid
public class CourseRequestDTO implements DateRangeContainer {

    private String title;

    private Long orgId;

    private Long adminId;

    private Long scheduleId;

    private LocalDate startDate;
    private LocalDate endDate;
    @Positive
    private BigDecimal price;

    private CourseTypeEnum type;
    @Positive
    private Integer maxEnrollments;

    private Boolean offerValid;

}