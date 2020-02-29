package com.joey.community.community.exception;

public enum CustomizeExceptionCode implements ICustomizeException {

    QUESTION_EXCEPTION_MESSAGE("你要找的问题不存在，请换一个试试？");

    private final String message;

    CustomizeExceptionCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
