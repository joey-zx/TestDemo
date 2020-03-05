package com.joey.community.community.dto;

import com.joey.community.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private Integer comentator;
    private String content;
    private User user;
}
