package com.hexin.netty.webchat.dao;

import com.hexin.netty.webchat.entity.ChatMsg;
import com.hexin.netty.webchat.entity.ChatMsgExample;
import com.hexin.netty.webchat.util.MyMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatMsgMapper extends MyMapper<ChatMsg> {
    int countByExample(ChatMsgExample example);

    int deleteByExample(ChatMsgExample example);

    List<ChatMsg> selectByExample(ChatMsgExample example);

    int updateByExampleSelective(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    int updateByExample(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);
}