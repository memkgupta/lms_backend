package com.lms.commonlib.exceptions;

public class UserNotFound extends APIException {
    public UserNotFound(String field,String value) {
        super("User with " + field+" "+value+ " not found");
        status=404;
    }
}