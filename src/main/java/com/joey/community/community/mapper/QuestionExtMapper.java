package com.joey.community.community.mapper;

import com.joey.community.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {

    void incView(Question question);

    void incCommentCount(Question question);

    List<Question> selectRelatedByTag(Question question);
}
