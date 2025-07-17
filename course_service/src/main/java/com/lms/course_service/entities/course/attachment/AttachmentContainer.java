package com.lms.course_service.entities.course.attachment;

public @interface AttachmentContainer {
    public AttachmentEntityType name() default AttachmentEntityType.LECTURE;
}
