package com.lms.course_service.entities.modules.plan.chapter.lecture;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;

import com.lms.course_service.entities.course.attachment.AttachmentContainer;
import com.lms.course_service.entities.course.attachment.AttachmentEntityType;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.assignment.Assignment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;
@AttachmentContainer(name = AttachmentEntityType.LECTURE)
@Entity
@Table(name = "lectures")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
    private LocalDateTime completionTime;
    private LocalDateTime startTime;
    private LocalDateTime scheduledAt;
    private Long duration;
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



