package com.lms.course_service.entities.modules;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.modules.plan.Plan;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "modules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module extends BaseEntity {

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id" )
    private Course course;



    @ElementCollection
    private List<Long> instructors_assigned;
    @ElementCollection
    private List<Long> teaching_assistants_assigned;
    @ElementCollection
    private List<Long> moderators_assigned;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
