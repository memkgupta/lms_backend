package com.lms.user_service.exceptions;

import lombok.Data;

@Data
public abstract class APIException extends RuntimeException {
    int status;
    public APIException(String message) {
        super(message);
    }
}