package com.lms.course_service.utils;

import com.lms.course_service.entities.modules.plan.Plan;

public class PlanUtils {
    public static boolean validate(Plan plan) {
        if(plan.getChapters().isEmpty())
            return false;
        if(
                plan.getChapters().stream()
                        .anyMatch(c->!ChapterUtils.validate(c))



        )
        {
            return false;
        }
        return true;
    }
}
