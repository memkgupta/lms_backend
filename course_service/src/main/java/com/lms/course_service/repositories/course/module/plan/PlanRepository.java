package com.lms.course_service.repositories.course.module.plan;

import com.lms.course_service.entities.modules.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
