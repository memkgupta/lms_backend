package com.lms.course_service.services.course;

import com.lms.commonlib.EntityService;
import com.lms.commonlib.annotations.HandlesEntity;
import com.lms.commonlib.exceptions.BadRequestException;
import com.lms.course_service.dtos.lecture.LectureRequestDTO;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import com.lms.course_service.repositories.course.module.plan.chapter.lecture.LectureRepository;
import com.lms.course_service.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@HandlesEntity(Lecture.class)
public class LectureService implements EntityService {

    private final LectureRepository lectureRepository;
    private final ChapterService chapterService;
    private final ModuleService moduleService;

    private final Set<String> ALLOWED_UPDATE_FIELDS = Set.of("title", "scheduledAt", "type", "status");

    public Lecture addLecture(LectureRequestDTO dto) {
        Chapter chapter = chapterService.getChapterById(dto.getChapterId());
        Module module = moduleService.getModuleById(dto.getModuleId());

        if (chapter == null || module == null)
            throw new BadRequestException("Chapter or Module not found");

        Lecture lecture = Lecture.builder()
                .title(dto.getTitle())
                .chapter(chapter)
                .module(module)
                .scheduledAt(dto.getScheduledAt())
                .type(dto.getType())
                .status(dto.getStatus())
                .build();
    chapterService.attachLecture(chapter,lecture,dto.getPosition());
        return lecture;
    }

    public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id)
                .orElse(null);
    }

    public Lecture updateLecture(LectureRequestDTO dto, Long id) {
        Lecture lecture = getLectureById(id);
        String[] ignoreFields = Utils.getNullPropertyNames(dto);
        Utils.checkCorrectFields(dto, ALLOWED_UPDATE_FIELDS);
        BeanUtils.copyProperties(dto, lecture, ignoreFields);
        return lectureRepository.save(lecture);
    }

    public void deleteLecture(Long id) {
        Lecture lecture = getLectureById(id);
        lectureRepository.delete(lecture);
    }

    public Page<Lecture> getAllLectures(Pageable pageable, Specification<Lecture> spec) {
        return lectureRepository.findAll(spec, pageable);
    }
}