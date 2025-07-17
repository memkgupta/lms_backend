package com.lms.course_service.utils;

import com.lms.commonlib.exceptions.BadRequestException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.Set;

public class Utils {
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)

                .toArray(String[]::new);
    }
    public static void checkCorrectFields(Object source , Set<String> allowed)
    {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> name!=null)
                .forEach(name -> {
                    if(!allowed.contains(name))
                        throw new BadRequestException(name+" can not be updated");
                });
    }
    public static String[] getNullOrNotPermittedPropertyNames(Object source, Set<String> fields) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name->src.getPropertyValue(name)==null)
                .filter(name->!fields.contains(name))
                .toArray(String[]::new);
    }
}
