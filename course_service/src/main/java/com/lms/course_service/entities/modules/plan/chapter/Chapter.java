package com.lms.course_service.entities.modules.plan.chapter;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chapters")
@Data
@NoArgsConstructor

public class Chapter extends BaseEntity {

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Column(name = "instructor_assigned")
    private Long instructorAssigned;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "chapter")
    private List<Lecture> lectures;

    private int currentLecture;
}

