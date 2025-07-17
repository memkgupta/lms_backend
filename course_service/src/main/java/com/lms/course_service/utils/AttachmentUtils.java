package com.lms.course_service.utils;

import com.lms.course_service.entities.course.attachment.AttachmentEntityRegistry;
import com.lms.course_service.entities.course.attachment.AttachmentEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentUtils {
    private final AttachmentEntityRegistry attachmentEntityRegistry;
    public  Class<?> getEntityFromAttachmentEntityType(AttachmentEntityType type)
    {
        return attachmentEntityRegistry.getEntity(type);
    }
}
