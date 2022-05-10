package com.sjtu.djw.wxcodelogin.mapper;

import com.sjtu.djw.wxcodelogin.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE openId = #{openId}")
    User findUserByOpenId(@Param("openId") String openId);

    @Insert("INSERT INTO user(openId, eventKey) VALUES(#{openId}, #{eventKey})")
    int insert(@Param("eventKey") String eventKey, @Param("openId") String openId);

}
