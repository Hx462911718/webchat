package com.hexin.netty.webchat.dao;

import com.hexin.netty.webchat.entity.Users;
import com.hexin.netty.webchat.entity.UsersExample;
import com.hexin.netty.webchat.util.MyMapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UsersMapper extends MyMapper<Users> {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    List<Users> selectByExample(UsersExample example);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    List<Map> queryUserByNickname(@Param("u") Users users);
}