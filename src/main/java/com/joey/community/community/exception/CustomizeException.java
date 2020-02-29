package com.joey.community.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(CustomizeExceptionCode errorCode) {
        this.message = errorCode.getMessage();
    }
    public String getMessage() {
        return message;
    }

}
