package com.lms.course_service.entities.course.schedule;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class Schedule extends BaseEntity {
    @OneToOne(mappedBy = "schedule")
    private Course course;
    @ElementCollection
    private List<ScheduleDay> items;

}
