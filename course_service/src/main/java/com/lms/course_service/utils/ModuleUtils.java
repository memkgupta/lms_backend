package com.lms.course_service.utils;

import com.lms.course_service.entities.modules.Module;

public class ModuleUtils {
    public static boolean validate(Module module) {
        if(module.getInstructors_assigned().isEmpty())
            return false;
        if(module.getPlan()==null)
            return false;
        if(!PlanUtils.validate(module.getPlan()))
            return false;
        return true;
    }
}
