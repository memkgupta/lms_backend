package com.lms.commonlib.exceptions;




public class BadRequestException extends APIException {
    public BadRequestException(String message) {
        super(message);
        status=400;
    }
}