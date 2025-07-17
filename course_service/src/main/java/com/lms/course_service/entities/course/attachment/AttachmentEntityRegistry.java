package com.lms.course_service.entities.course.attachment;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class AttachmentEntityRegistry {
    private final Map<AttachmentEntityType, Class<?>> registry = new HashMap<>();
    @PostConstruct
    public void init() {
        Reflections reflections = new Reflections("com.lms.course_service.entities");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
        classes.forEach(cls -> {
            registry.put(AttachmentEntityType.valueOf(cls.getAnnotation(Entity.class).name()), cls);
        });
    }

    public Class<?> getEntity(AttachmentEntityType entityType) {
        return registry.getOrDefault(entityType,null);
    }
}
