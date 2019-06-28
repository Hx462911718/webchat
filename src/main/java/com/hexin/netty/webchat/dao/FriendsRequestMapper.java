package com.hexin.netty.webchat.dao;

import com.hexin.netty.webchat.entity.FriendsRequest;
import com.hexin.netty.webchat.entity.FriendsRequestExample;
import com.hexin.netty.webchat.util.MyMapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FriendsRequestMapper extends MyMapper<FriendsRequest> {
    int countByExample(FriendsRequestExample example);

    int deleteByExample(FriendsRequestExample example);

    List<FriendsRequest> selectByExample(FriendsRequestExample example);

    int updateByExampleSelective(@Param("record") FriendsRequest record, @Param("example") FriendsRequestExample example);

    int updateByExample(@Param("record") FriendsRequest record, @Param("example") FriendsRequestExample example);

    @Select("select * from friends_request fr where fr.accept_user_id=#{f.acceptUserId}  and fr.send_user_id =#{f.sendUserId}")
    List<Map> queryExitsRequest(@Param("f") FriendsRequest friendsRequest);
}