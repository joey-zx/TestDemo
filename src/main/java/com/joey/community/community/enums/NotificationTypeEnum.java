package com.joey.community.community.enums;

public enum NotificationTypeEnum {
    NOTIFYCOMMENT(1,"通知回复评论"),
    NOTIFYQUESTION(2,"通知回复问题");

    private int type;
    private String description;

    NotificationTypeEnum(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
