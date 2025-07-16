package com.lms.course_service.repositories.attachment;

import com.lms.course_service.entities.course.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
