package com.lms.course_service.repositories.attachment;

import com.lms.course_service.entities.course.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> , JpaSpecificationExecutor<Attachment> {
    List<Attachment> findByEntityTypeAndAttachedTo(String entityType, Long attachedTo);
}
