package com.joey.community.community.mapper;
import com.joey.community.community.model.Comment;

public interface CommentExtMapper {

    void incCommentCount(Comment comment);
}