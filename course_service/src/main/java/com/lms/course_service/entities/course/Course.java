package com.lms.course_service.entities.course;

import com.lms.commonlib.annotations.DateRangeValid;
import com.lms.commonlib.utils.DateRangeContainer;
import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.schedule.Schedule;
import com.lms.course_service.entities.modules.Module;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course extends BaseEntity {

    private String title;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "admin_id")
    private Long adminId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private boolean isPublished;
    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CourseTypeEnum type;

    private Integer maxEnrollments;



    @OneToMany(mappedBy = "course",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Module> modules;
}

