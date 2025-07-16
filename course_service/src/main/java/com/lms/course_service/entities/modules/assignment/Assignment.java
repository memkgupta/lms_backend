package com.lms.course_service.entities.modules.assignment;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.course.attachment.Attachment;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
public class Assignment extends BaseEntity {

    private String title;
    private Integer score;
    private LocalDate deadline;
    private LocalDateTime scheduledAt;
    private Boolean graded;

    @Column(name = "uploaded_by")
    private Long uploadedBy;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;


}
