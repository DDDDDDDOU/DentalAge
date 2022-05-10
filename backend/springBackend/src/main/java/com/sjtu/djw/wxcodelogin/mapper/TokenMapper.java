package com.sjtu.djw.wxcodelogin.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TokenMapper {
    @Insert("INSERT INTO user_token(user_id, token, eventKey) VALUES(#{userId}, #{token}, #{eventKey})")
    void insert(@Param("userId") Integer userId, @Param("token") String token, @Param("eventKey") String eventKey);

    @Select("SELECT token FROM user_token WHERE user_id = #{userId}")
    String findTokenByUserId(@Param("userId") Integer userId);

    @Select("SELECT token FROM user_token WHERE eventKey = #{eventKey}")
    String findTokenByEventKey(@Param("eventKey") String eventKey);

    @Update("Update user_token SET token = #{token} , eventKey = #{eventKey}  WHERE user_id = #{userId}")
    void updateTokenByUserId(@Param("token") String token, @Param("userId") Integer userId, @Param("eventKey") String eventKey);


    @Delete("DELETE FROM user_token WHERE eventKey = #{eventKey}")
    void deleteTokenByEventKey(@Param("eventKey") String eventKey);
}
