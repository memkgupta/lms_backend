package com.lms.course_service.repositories.course.module.plan.chapter;

import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
