package com.lms.course_service.entities.modules.plan.chapter.lecture;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;

import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.assignment.Assignment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lectures")
@Getter
@Setter
@NoArgsConstructor
public class Lecture extends BaseEntity {

    private String title;
    private String thumbnail;

    private String description;
    private String materialForStudents;
    private String materialForTeachers;
    private String url;

    @Enumerated(EnumType.STRING)
    private LectureType type;

    @Enumerated(EnumType.STRING)
    private LectureStatus status;

    private LocalDateTime scheduledAt;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment dpp;

}



