package com.lms.user_service.exceptions;

public class BadRequestException extends APIException {
    public BadRequestException(String message) {
        super(message);
        status=400;
    }
}