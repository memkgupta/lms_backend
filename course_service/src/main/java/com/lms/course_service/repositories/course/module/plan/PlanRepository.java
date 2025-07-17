package com.lms.course_service.repositories.course.module.plan;

import com.lms.course_service.entities.modules.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findByCourseId(Long courseId);
    Optional<Plan> findByModuleId(Long moduleId);
}
