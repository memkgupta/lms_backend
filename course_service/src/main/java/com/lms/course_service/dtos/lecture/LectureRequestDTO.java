package com.lms.course_service.dtos.lecture;
import com.lms.course_service.entities.modules.plan.chapter.lecture.LectureStatus;
import com.lms.course_service.entities.modules.plan.chapter.lecture.LectureType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LectureRequestDTO {
    private String title;
    private Long moduleId;
    private Long chapterId;
    private LocalDateTime scheduledAt;
    private LectureType type;
    private LectureStatus status;
    private int position;
}
