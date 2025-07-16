package com.lms.organisation_service.exceptions;

import org.springframework.stereotype.Component;
@Component
public class APIExceptionFactory {

    public APIException throwException(ExceptionTypeEnum type , String entity , String identifier,String value)
    {
        return switch (type){
            case ENTITY_NOT_FOUND -> new EntityNotFound(entity,identifier,value);

            case INTERNAL_SERVER_ERROR -> new InternalServerError(value);
            case BAD_REQUEST -> new BadRequestException(value);
            case BAD_CREDENTIALS -> new BadCredentialsException();
            case UNAUTHORIZED -> new UnAuthorisedAccess(entity,value);
            case ENTITY_ALREADY_EXISTS  -> new EntityAlreadyExists(entity,identifier,value);
        };
    }
}
