package com.lms.commonlib.exceptions;



public class EntityNotFound extends APIException {
    public EntityNotFound(String entity,String field, String value) {
        super(entity+" with" + field+" "+value+ " not found");
        status=404;
    }
}