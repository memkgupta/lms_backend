package com.lms.commonlib.exceptions;


import com.lms.organisation_service.exceptions.APIException;

public class EntityAlreadyExists extends APIException {
    public EntityAlreadyExists(String entityName , String identifierId, String identifierValue) {
        super(entityName+" already exists with "+identifierId+" "+identifierValue);
        status=409;
    }
}