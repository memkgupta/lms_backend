package com.lms.organisation_service.exceptions;



public class BadCredentialsException extends APIException {
    public BadCredentialsException() {
        super("Invalid username or password");
        status = 401;
    }
}
