package com.lms.course_service.entities.modules.plan.chapter.lecture.doubt;

import com.lms.course_service.entities.BaseEntity;
import com.lms.course_service.entities.course.attachment.Attachment;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DoubtResponse extends BaseEntity {
    @OneToOne
    private Doubt doubt;

}
