package com.lms.course_service.repositories.announcement;

import com.lms.course_service.entities.course.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
}
