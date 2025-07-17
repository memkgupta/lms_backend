package com.lms.course_service.entities.modules.plan.chapter;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.Plan;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chapters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter extends BaseEntity {

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    private Long module_id;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;

    @Column(name = "instructor_assigned")
    private Long instructorAssigned;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate completionDate;
    @OneToMany(mappedBy = "chapter")
    private List<Lecture> lectures;

    private int currentLecture;
}

