package com.joey.community.community.dto;

import com.joey.community.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private int id;
    private long gmtCreate;
    private int status;
    private User user;
    private String outerTitle;
    private String type;
}
