package com.lms.course_service.entities.course.attachment;

import com.lms.course_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "attachments")
@Data
@NoArgsConstructor

public class Attachment extends BaseEntity {

    private String title;
    private String description;

    private String type; // image/pdf/etc.
    private String url;
    @Enumerated(EnumType.STRING)
    private AttachmentEntityType attachmentEntityType;
    private Long attachedTo;
    @Enumerated(EnumType.STRING)
    private AttachementType entityType;
    private boolean availableForStudents;
    @Column(name = "uploaded_by")
    private Long uploadedBy;
}

