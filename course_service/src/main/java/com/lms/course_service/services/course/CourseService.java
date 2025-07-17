package com.lms.course_service.services.course;

import com.lms.commonlib.EntityService;
import com.lms.commonlib.annotations.HandlesEntity;
import com.lms.commonlib.exceptions.BadRequestException;
import com.lms.commonlib.exceptions.EntityNotFound;
import com.lms.course_service.dtos.request.CourseRequestDTO;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.repositories.course.CourseRepository;
import com.lms.course_service.utils.CourseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.lms.course_service.utils.Utils.getNullPropertyNames;

@Service
@RequiredArgsConstructor
@HandlesEntity(Course.class)
public class CourseService implements EntityService {
    private final CourseRepository courseRepository;
    private final Set<String> ALLOWED_UPDATE_FIELDS =Set.of("title","description","startDate","endDate","price","type","maxEnrollments");

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    public Course getCourseById(Long id)
    {
        return courseRepository.findById(id).orElse(null);
    }
    public Page<Course> getAllCourses(Pageable pageable , Specification<Course> specification)
    {
        return courseRepository.findAll(specification, pageable);
    }
    public Course updateCourse(CourseRequestDTO courseRequestDTO,Long id) {
        Course course = getCourseById(id);
        if(course == null)
        {
            throw new EntityNotFound("Course","id",String.valueOf(id));
        }
            String[] updateFields = getNullPropertyNames(courseRequestDTO);
        Arrays.stream(updateFields).forEach(field -> {
            if(!ALLOWED_UPDATE_FIELDS.contains(field))
            {
                throw new BadRequestException(field+" cannot be updated");
            }
        });
             BeanUtils.copyProperties(courseRequestDTO,course,updateFields);
             return courseRepository.save(course);
    }
    public boolean deleteCourse(Long id )
    {
        Course course = getCourseById(id);
        if(course == null)
        {
            throw new EntityNotFound("Course","id",String.valueOf(id));
        }
        courseRepository.delete(course);
        return true;
    }
    public boolean publishCourse(Long id)
    {
        Course course = getCourseById(id);
        if(course == null)
        {
            throw new EntityNotFound("Course","id",String.valueOf(id));
        }
        if(!CourseUtils.validate(course))
        {
            throw new BadRequestException("Course data is not complete");
        }
        course.setPublished(true);
        courseRepository.save(course);
        return true;
    }
}
