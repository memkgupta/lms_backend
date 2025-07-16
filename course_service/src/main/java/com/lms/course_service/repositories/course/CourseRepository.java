package com.lms.course_service.repositories.course;

import com.lms.course_service.entities.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<Course, Long> , JpaSpecificationExecutor<Course> {
}
