package com.lms.course_service.services.course;

import com.lms.commonlib.EntityService;
import com.lms.commonlib.annotations.HandlesEntity;
import com.lms.commonlib.exceptions.BadRequestException;
import com.lms.commonlib.exceptions.EntityNotFound;
import com.lms.course_service.dtos.request.PlanRequestDTO;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.entities.modules.plan.Plan;
import com.lms.course_service.entities.modules.plan.chapter.Chapter;
import com.lms.course_service.repositories.course.module.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lms.commonlib.utils.EntityUtils;

import java.util.Arrays;
import java.util.Set;

@Service
@RequiredArgsConstructor
@HandlesEntity(Plan.class)
public class PlanService implements EntityService {
    private final ModuleService moduleService;
    private final PlanRepository planRepository;
    private final Set<String> ALLOWED_UPDATE_FIELDS = Set.of("startDate", "endDate");
    public Plan createPlan(Plan plan,Long moduleId) {
        Module module = moduleService.getModuleById(moduleId);
        plan.setModule(module);
        moduleService.attachPlan(plan,module);
        return plan;
    }
    public Plan getPlanById(Long id)
    {
        return planRepository.findById(id).orElse(null);
    }
    public Plan getPlanByModuleId(Long moduleId)
    {
        return planRepository.findByModuleId(moduleId).orElse(null);
    }
    public Plan updatePlan(PlanRequestDTO planRequestDTO, Long planId)
    {
        Plan plan = getPlanById(planId);
        if(plan == null)
        {
            throw new EntityNotFound("Plan","id",String.valueOf(planId));
        }
        String[] ignoreFields = EntityUtils.getNullPropertyNames(planRequestDTO);
        Arrays.stream(ignoreFields).forEach(field->{
            if(!ALLOWED_UPDATE_FIELDS.contains(field))
                throw new BadRequestException(field+" cannot be updated");
        });
        BeanUtils.copyProperties(planRequestDTO, plan);
        return planRepository.save(plan);
    }
    public boolean deletePlan(Long id)
    {
        Plan plan = getPlanById(id);
        if(plan == null)
            throw new EntityNotFound("Plan","id",String.valueOf(id));
         planRepository.delete(plan);
return true;
    }
    public void attachChapter(Chapter chapter , Plan plan , int position)
    {
        if(position<0 || position>=plan.getChapters().size())
        {
            throw new BadRequestException("Invalid position");
        }
        plan.getChapters().add(position,chapter);
        planRepository.save(plan);
    }

}
