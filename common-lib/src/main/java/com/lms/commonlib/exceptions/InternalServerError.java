package com.lms.commonlib.exceptions;




public class InternalServerError extends APIException {
    public InternalServerError(String message) {
        super(message);
        status=500;
    }
}