package com.lms.course_service.entities.course.schedule;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

@Embeddable
@Data
public class ScheduleDay {
    @Enumerated(EnumType.STRING)
    private ScheduleDayEnum dayOfWeek;
    @ElementCollection
    private List<ScheduleItem> items;
}
