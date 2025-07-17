package com.lms.course_service.entities.course.schedule;

import com.lms.commonlib.utils.TimeRangeContainer;
import com.lms.course_service.entities.modules.Module;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
public class ScheduleItem implements TimeRangeContainer {
    private Long moduleId;
    private LocalTime start;
    private LocalTime end;
}
