package com.lms.course_service.dtos.request.schedule;

import com.lms.course_service.entities.course.schedule.ScheduleDay;
import com.lms.course_service.entities.course.schedule.ScheduleDayEnum;
import com.lms.course_service.entities.course.schedule.ScheduleItem;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ScheduleRequestDTO {
    private Long courseId;
    private List<ScheduleDay> scheduleDayList;

}
