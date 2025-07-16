package com.lms.course_service.utils;

import com.lms.course_service.entities.course.Course;

public class CourseUtils {
    public static boolean validate(Course course) {
        if(course.getAdminId()==null
        )
            return false;
        if(course.getModules().isEmpty())
            return false;
        if( course.getModules().stream().anyMatch(m->!ModuleUtils.validate(m)))
            return false;
        return true;
    }
}
