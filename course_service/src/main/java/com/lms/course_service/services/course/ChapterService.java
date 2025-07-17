package com.lms.course_service.services.course;

import com.lms.commonlib.EntityService;
import com.lms.commonlib.annotations.HandlesEntity;
import com.lms.commonlib.exceptions.BadRequestException;
import com.lms.commonlib.exceptions.EntityNotFound;
import com.lms.course_service.dtos.request.ChapterRequestDTO;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import com.lms.course_service.repositories.course.module.plan.chapter.ChapterRepository;
import com.lms.course_service.utils.ChapterUtils;
import com.lms.course_service.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@HandlesEntity(Chapter.class)
public class ChapterService implements EntityService {
    private final ChapterRepository chapterRepository;
    private final CourseService courseService;
    private final PlanService planService;
    private final ModuleService moduleService;
    private final Set<String> ALLOWED_UPDATE_FIELDS = Set.of("title","instructorAssigned");
    public Chapter createChapter(ChapterRequestDTO chapterRequestDTO) {
        Chapter chapter = Chapter.builder()
                .title(chapterRequestDTO.getTitle())
                .startDate(chapterRequestDTO.getStartDate())
                .instructorAssigned(chapterRequestDTO.getInstructorAssigned())
                .module_id(chapterRequestDTO.getModuleId())
                .course(courseService.getCourseById(chapterRequestDTO.getCourseId()))

                .build();
        Module module = moduleService.getModuleById(chapterRequestDTO.getModuleId());
        if(module==null)
            throw new BadRequestException("Module not found");
        List<Lecture>  lectures = ChapterUtils.generatePlaceholderLectures(
                chapter.getCourse().getSchedule(),chapter,module, chapterRequestDTO.getNoOfLectures()
        );
        chapter.setLectures(lectures);
        return chapterRepository.save(chapter);
    }
    public Chapter getChapterById(Long chapterId) {
        return chapterRepository.findById(chapterId).orElse(null);
    }
    public Chapter updateChapter(ChapterRequestDTO chapterRequestDTO,Long chapterId) {
        String[] ignoreFields = Utils.getNullPropertyNames(chapterRequestDTO);
        Utils.checkCorrectFields(chapterRequestDTO,ALLOWED_UPDATE_FIELDS);
        Chapter chapter = getChapterById(chapterId);
        if(chapter==null)
            throw new EntityNotFound("Chapter","id",String.valueOf(chapterId));
        BeanUtils.copyProperties(chapterRequestDTO,chapter,ignoreFields);
        return chapterRepository.save(chapter);
    }
    public void deleteChapter(Long chapterId) {
        Chapter chapter = getChapterById(chapterId);
        if(chapter==null)
            throw new EntityNotFound("Chapter","id",String.valueOf(chapterId));
        chapterRepository.delete(chapter);
    }
    public Page<Chapter> getAllChapters(Pageable pageable, Specification<Chapter> chapterSpecification) {
        return chapterRepository.findAll(chapterSpecification, pageable);
    }
    public void attachLecture(Chapter chapter,Lecture lecture,int position) {
        if(position>=chapter.getLectures().size() || position<0)
        {
            throw new BadRequestException("Invalid position");
        }
        chapter.getLectures().add(position,lecture);
        chapterRepository.save(chapter);
    }
}
