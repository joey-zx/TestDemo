package com.joey.community.community.mapper;

import com.joey.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into publish (title,content,tag,gmt_create,gmt_modified,creator) " +
            "values(#{title},#{content},#{tag},#{gmtCreate},#{gmtModified},#{creator}) ")
    void create(Question question);

    @Select("Select * from publish limit #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from publish")
    Integer count();

    @Select("Select * from publish where creator = #{userId} limit #{offset},#{size}")
    List<Question> listById(Integer userId, Integer offset, Integer size);

    @Select("select count(1) from publish where creator = #{userId}")
    Integer countById(Integer userId);

    @Select("Select * from publish where id = #{id}")
    Question findById(Integer id);

    @Update("update publish set title=#{title},content=#{content},tag=#{tag},gmt_modified=#{gmtModified} where id = #{id}")
    void update(Question question);
}
