package com.lms.user_service.exceptions;

public class UnAuthorisedAccess extends APIException {
    public UnAuthorisedAccess(String resourceName , String resourceId) {
        super("Unauthorised access to "+resourceName +"with id "+resourceId);
        status=401;
    }
}