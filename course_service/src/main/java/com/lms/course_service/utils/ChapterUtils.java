package com.lms.course_service.utils;

import com.lms.commonlib.utils.DateTimeUtils;
import com.lms.course_service.entities.course.schedule.Schedule;
import com.lms.course_service.entities.course.schedule.ScheduleDayEnum;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.entities.modules.plan.chapter.lecture.Lecture;
import com.lms.course_service.entities.modules.plan.chapter.lecture.LectureStatus;
import com.lms.course_service.entities.modules.plan.chapter.lecture.LectureType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChapterUtils {
    public static boolean validate(Chapter chapter) {
        if(chapter.getInstructorAssigned()==null)
            return false;
        if(chapter.getLectures().isEmpty())
            return false;
        return true;
    }
    public static List<Lecture> generatePlaceholderLectures(Schedule schedule, Chapter chapter, Module module, int noOfLectures) {
        if (schedule == null || chapter == null || module == null || noOfLectures <= 0) {
            return Collections.emptyList();
        }

        List<DayOfWeek> availableDays = extractModuleDays(schedule, module);

        if (availableDays.isEmpty()) return Collections.emptyList();

        List<Lecture> lectures = new ArrayList<>();

        DayOfWeek chapterStartDay = chapter.getStartDate().getDayOfWeek();
        int dayIndex = availableDays.indexOf(chapterStartDay);
        if (dayIndex == -1) {
            dayIndex = 0; // fallback if chapterStartDay not found in schedule
        }

        LocalDate currentDate = chapter.getStartDate();

        for (int i = 0; i < noOfLectures; i++) {
            DayOfWeek currentDay = availableDays.get(dayIndex);
            ScheduleDayEnum scheduleKey = ScheduleDayEnum.valueOf(currentDay.name());

            lectures.add(
                    Lecture.builder()
                            .chapter(chapter)
                            .scheduledAt(currentDate.atTime(schedule.getItems()
                                            .stream()
                                            .filter(scheduleDay -> scheduleDay.getDayOfWeek().equals(scheduleKey))
                                            .findFirst()
                                            .get()
                                            .getItems().stream()
                                            .filter(scheduleItem ->
                                                    scheduleItem.getModuleId().equals(module.getId()))
                                            .findFirst().get()
                                            .getStart()))
                            .type(LectureType.RECORDED) // default type
                            .status(LectureStatus.UPCOMING)
                            .module(module)
                            .build()
            );

            // Prepare next iteration
            dayIndex = (dayIndex + 1) % availableDays.size();
            currentDate = DateTimeUtils.getNextDayFrom(currentDate, availableDays.get(dayIndex));
        }

        return lectures;
    }

    public static List<DayOfWeek> extractModuleDays(Schedule schedule, Module module) {
        return schedule.getItems().stream()
                .filter(entry -> entry.getItems().stream().anyMatch(item->item.getModuleId().equals(module.getId())))
                .map(entry -> DayOfWeek.valueOf(entry.getDayOfWeek().name()))
                .sorted()
                .collect(Collectors.toList());
    }
}
