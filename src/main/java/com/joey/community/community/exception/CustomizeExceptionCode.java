package com.joey.community.community.exception;

import org.omg.CORBA.NO_IMPLEMENT;

public enum CustomizeExceptionCode implements ICustomizeException {

    QUESTION_EXCEPTION_MESSAGE(2001,"你要找的问题不存在，请换一个试试？"),
    NO_QUESTION_COMMENT(2002,"请选择你需要回复的主题"),
    NO_LGGIN(2003,"用户未登录，请登录后再试"),
    SYSTEM_ERROR(2004,"系统内部错误,请稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你评论的问题不存在"),
    QUESTION_NOT_FOUND(2007,"你的问题不存在"),
    NO_REPLY_COMMENT(2008,"请输入你需要回复的内容");

    private final Integer code;
    private final String message;

    CustomizeExceptionCode(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
