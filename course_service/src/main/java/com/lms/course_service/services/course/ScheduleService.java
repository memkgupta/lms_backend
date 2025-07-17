package com.lms.course_service.services.course;

import com.lms.commonlib.exceptions.BadRequestException;
import com.lms.commonlib.exceptions.EntityNotFound;
import com.lms.course_service.dtos.request.schedule.ScheduleRequestDTO;
import com.lms.course_service.entities.course.Course;
import com.lms.course_service.entities.course.schedule.Schedule;
import com.lms.course_service.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final CourseService courseService;
    private final ModuleService moduleService;
    private final ScheduleRepository scheduleRepository;
    public Schedule createSchedule(ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = new Schedule();
        Course course = courseService.getCourseById(scheduleRequestDTO.getCourseId());
        if(course == null) {
            throw new BadRequestException("Course not found");
        }
        schedule.setCourse(course);
        // todo valid structure check
        schedule.setItems(scheduleRequestDTO.getScheduleDayList());
        return scheduleRepository.save(schedule);
    }
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }
    public Schedule updateSchedule(ScheduleRequestDTO scheduleRequestDTO,Long id) {
        if (scheduleRequestDTO.getScheduleDayList()!=null) {
            Schedule schedule = getScheduleById(id);
            if(schedule==null) {
                throw new BadRequestException("Schedule not found");
            }
            schedule.setItems(scheduleRequestDTO.getScheduleDayList());
            scheduleRepository.save(schedule);
            return schedule;
        }
        throw new BadRequestException("Course can't be updated");
    }
    public void deleteSchedule(Long id) {
        Schedule schedule = getScheduleById(id);
        if(schedule==null) {
            throw new EntityNotFound("Schedule","id",String.valueOf(id));
        }
        scheduleRepository.delete(schedule);
    }

}
