package com.lms.course_service.services.course;

import com.lms.course_service.dtos.request.AssignmentRequestDTO;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.modules.assignment.Assignment;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import com.lms.course_service.repositories.course.CourseRepository;
import com.lms.course_service.repositories.course.assignment.AssignmentRepository;
import com.lms.course_service.repositories.course.module.plan.chapter.ChapterRepository;
import com.lms.course_service.repositories.course.module.plan.chapter.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// will update it later
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final LectureRepository lectureRepository;

    public Assignment createAssignment(AssignmentRequestDTO dto) {
        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setScore(dto.getScore());
        assignment.setDeadline(dto.getDeadline());
        assignment.setScheduledAt(dto.getScheduledAt());
        assignment.setGraded(dto.getGraded());
        assignment.setUploadedBy(dto.getUploadedBy());

        if (dto.getCourseId() != null) {
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            assignment.setCourse(course);
        }

        if (dto.getChapterId() != null) {
            Chapter chapter = chapterRepository.findById(dto.getChapterId())
                    .orElseThrow(() -> new RuntimeException("Chapter not found"));
            assignment.setChapter(chapter);
        }

        if (dto.getLectureId() != null) {
            Lecture lecture = lectureRepository.findById(dto.getLectureId())
                    .orElseThrow(() -> new RuntimeException("Lecture not found"));
            assignment.setLecture(lecture);
        }

        return assignmentRepository.save(assignment);
    }
}
