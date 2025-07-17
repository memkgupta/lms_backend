package com.lms.course_service.repositories;

import com.lms.course_service.entities.course.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> , JpaSpecificationExecutor<Schedule> {
}
