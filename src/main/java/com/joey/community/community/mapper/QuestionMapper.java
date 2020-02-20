package com.joey.community.community.mapper;

import com.joey.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into publish (title,content,tag,gmt_create,gmt_modified,creator) " +
            "values(#{title},#{content},#{tag},#{gmtCreate},#{gmtModified},#{creator}) ")
    void create(Question question);
}
