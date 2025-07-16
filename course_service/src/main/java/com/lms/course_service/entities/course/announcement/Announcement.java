package com.lms.course_service.entities.course.announcement;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.course.attachment.Attachment;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Announcement extends BaseEntity {
    private String title;
    @ManyToOne
    private Course course;

    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
