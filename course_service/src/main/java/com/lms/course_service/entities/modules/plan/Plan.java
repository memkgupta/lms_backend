package com.lms.course_service.entities.modules.plan;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Plan")
public class Plan extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany
    @JoinColumn(name = "")
    private List<Chapter> chapters;

    private int currentChapter;
    private LocalDate startDate;
    private LocalDate endDate;

}
