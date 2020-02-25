package com.joey.community.community.mapper;

import com.joey.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values " +
            "(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarURL})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findUserByToken(String token);

    @Select("select * from user where id = #{creator}")
    User findUserById(int creator);

    @Select("select * from user where account_id = #{accountId}")
    User findUserByAccountId(String accountId);

    @Update("update user set name = #{name},avatar_url = #{avatarURL},gmt_modified = #{gmtModified} where id = #{id}")
    void update(User realUser);
}
