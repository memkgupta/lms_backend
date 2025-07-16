package com.lms.organisation_service.exceptions;



public class InternalServerError extends APIException {
    public InternalServerError(String message) {
        super(message);
        status=500;
    }
}