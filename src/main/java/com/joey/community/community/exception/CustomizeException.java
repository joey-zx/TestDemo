package com.joey.community.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(CustomizeExceptionCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
