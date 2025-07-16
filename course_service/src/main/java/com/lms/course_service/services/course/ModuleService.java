package com.lms.course_service.services.course;

import com.lms.commonlib.exceptions.BadRequestException;
import com.lms.commonlib.exceptions.EntityNotFound;
import com.lms.commonlib.utils.EntityUtils;
import com.lms.course_service.dtos.request.ModuleRequestDTO;
import com.lms.course_service.entities.modules.Module;
import com.lms.course_service.repositories.course.module.ModuleRepository;
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
public class ModuleService {
    private final CourseService courseService;
    private final ModuleRepository moduleRepository;
    private final Set<String> ALLOWED_UPDATE_FIELDS = Set.of(
            "title"
    );
    public Module createModule(Module module) {
        moduleRepository.save(module);
        return module;
    }
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }
    public Page<Module> getAllModules(Pageable pageable, Specification<Module> specification) {
        return moduleRepository.findAll(specification,pageable);
    }
    public Module updateModule(ModuleRequestDTO moduleRequestDTO , Long id) {
        Module module = getModuleById(id);
        if(module==null)
        {
            throw new EntityNotFound("Module","id",String.valueOf(id));
        }
        String[] updateFields =  EntityUtils.getNullPropertyNames(moduleRequestDTO);
        Arrays.stream(updateFields)
                .forEach(field->{
                    if(!ALLOWED_UPDATE_FIELDS.contains(field))
                        throw new BadRequestException(field+" cannot be updated");
                });
        BeanUtils.copyProperties(moduleRequestDTO, module, updateFields);
        moduleRepository.save(module);
        return module;
    }

    public boolean deleteModule(Long id)
    {
        Module module = getModuleById(id);
        if(module==null)
            throw new EntityNotFound("Module","id",String.valueOf(id));
        moduleRepository.delete(module);
        return true;
    }
    public boolean addInstructor(Long mid ,Long iid )
    {
        Module module = getModuleById(mid);
        if(module==null)
        {
            throw new EntityNotFound("Module","mid",String.valueOf(mid));
        }
        module.getInstructors_assigned().add(iid);
        moduleRepository.save(module);
        return true;
    }
    public boolean addTA(Long mid ,Long taid )
    {
        Module module = getModuleById(mid);
        if(module==null)
        {
            throw new EntityNotFound("Module","mid",String.valueOf(mid));
        }
        module.getTeaching_assistants_assigned().add(taid);
        moduleRepository.save(module);
        return true;
    }
    public boolean addModerator(Long mid ,Long moid )
    {
        Module module = getModuleById(mid);
        if(module==null)
        {
            throw new EntityNotFound("Module","mid",String.valueOf(mid));
        }
        module.getInstructors_assigned().add(moid);
        moduleRepository.save(module);
        return true;
    }


}
