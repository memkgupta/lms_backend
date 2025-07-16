package com.lms.course_service.entities.modules.plan.chapter.lecture.doubt;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doubt extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    private String title;
    private String description;
    private String enrollment_id;
    @Enumerated(EnumType.STRING)
    private DoubtStatus status;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "parent_doubt")
    private Doubt parent;

}
