package com.lms.course_service.repositories.course.assignment;

import com.lms.course_service.entities.modules.assignment.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
