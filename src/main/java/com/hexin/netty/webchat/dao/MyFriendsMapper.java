package com.hexin.netty.webchat.dao;

import com.hexin.netty.webchat.entity.MyFriends;
import com.hexin.netty.webchat.entity.MyFriendsExample;
import com.hexin.netty.webchat.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyFriendsMapper extends MyMapper<MyFriends> {
    int countByExample(MyFriendsExample example);

    int deleteByExample(MyFriendsExample example);

    List<MyFriends> selectByExample(MyFriendsExample example);

    int updateByExampleSelective(@Param("record") MyFriends record, @Param("example") MyFriendsExample example);

    int updateByExample(@Param("record") MyFriends record, @Param("example") MyFriendsExample example);
}