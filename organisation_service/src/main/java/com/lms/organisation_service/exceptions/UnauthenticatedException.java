package com.lms.organisation_service.exceptions;

public class UnauthenticatedException extends APIException {
    public UnauthenticatedException(String message) {
        super(message);
        status=401;
    }
}
