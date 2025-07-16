package com.lms.organisation_service.exceptions;



public class EntityAlreadyExists extends APIException {
    public EntityAlreadyExists(String entityName , String identifierId, String identifierValue) {
        super(entityName+" already exists with "+identifierId+" "+identifierValue);
        status=409;
    }
}