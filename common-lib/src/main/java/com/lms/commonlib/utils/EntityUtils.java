package com.lms.commonlib.utils;

import com.lms.commonlib.EntityService;
import com.lms.commonlib.annotations.HandlesEntity;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
@Component
public class EntityUtils {
    @Autowired
   static List<EntityService> services;
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)

                .toArray(String[]::new);
    }
    public static String[] getNullOrNotPermittedPropertyNames(Object source, Set<String> fields) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name->src.getPropertyValue(name)==null)
                .filter(name->!fields.contains(name))
                .toArray(String[]::new);
    }

    public static EntityService getServiceForEntity(Class<?> entityClass)
    {
        for(EntityService service : services)
        {
            HandlesEntity annotation = entityClass.getAnnotation(HandlesEntity.class);
            if(annotation!=null && annotation.value().equals(entityClass))
            {
                return service;
            }
        }
        return null;
    }
}
