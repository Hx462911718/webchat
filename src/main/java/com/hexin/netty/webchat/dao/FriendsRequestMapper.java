package com.hexin.netty.webchat.dao;

import com.hexin.netty.webchat.entity.FriendsRequest;
import com.hexin.netty.webchat.entity.FriendsRequestExample;
import com.hexin.netty.webchat.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendsRequestMapper extends MyMapper<FriendsRequest> {
    int countByExample(FriendsRequestExample example);

    int deleteByExample(FriendsRequestExample example);

    List<FriendsRequest> selectByExample(FriendsRequestExample example);

    int updateByExampleSelective(@Param("record") FriendsRequest record, @Param("example") FriendsRequestExample example);

    int updateByExample(@Param("record") FriendsRequest record, @Param("example") FriendsRequestExample example);
}