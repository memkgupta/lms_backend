package com.lms.course_service.dtos.request.attachment;

import com.lms.course_service.entities.course.attachment.AttachementType;
import com.lms.course_service.entities.course.attachment.AttachmentEntityType;
import lombok.Data;

@Data
public class AttachmentRequestDTO {
    private String title;
    private String description;
    private String type; // e.g. image, pdf, etc.
    private String url;
    private AttachmentEntityType attachmentEntityType;
    private Long attachedTo;
    private AttachementType entityType;
    private boolean availableForStudents;
    private Long uploadedBy;
}