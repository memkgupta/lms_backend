package com.lms.course_service.repositories.course.module.plan.chapter.lecture;

import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
