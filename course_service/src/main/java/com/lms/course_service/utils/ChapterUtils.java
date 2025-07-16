package com.lms.course_service.utils;

import com.lms.course_service.entities.modules.plan.chapter.Chapter;

public class ChapterUtils {
    public static boolean validate(Chapter chapter) {
        if(chapter.getInstructorAssigned()==null)
            return false;
        if(chapter.getLectures().isEmpty())
            return false;
        return true;
    }
}
