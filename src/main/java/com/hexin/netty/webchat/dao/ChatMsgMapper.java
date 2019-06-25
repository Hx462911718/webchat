package com.hexin.netty.webchat.dao;

import com.hexin.netty.webchat.entity.ChatMsg;
import com.hexin.netty.webchat.entity.ChatMsgExample;
import com.hexin.netty.webchat.util.MyMapper;
import java.util.List;
import java.util.Map;

import com.hexin.netty.webchat.vo.ChatVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ChatMsgMapper extends MyMapper<ChatMsg> {
    int countByExample(ChatMsgExample example);

    int deleteByExample(ChatMsgExample example);

    List<ChatMsg> selectByExample(ChatMsgExample example);

    int updateByExampleSelective(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    int updateByExample(@Param("record") ChatMsg record, @Param("example") ChatMsgExample example);

    @Select("select *  from chat_msg ch where (ch.send_user_id = #{c.sendUserId} and ch.accept_user_id = #{c.acceptUserId}) or (ch.send_user_id = #{c.acceptUserId} and ch.accept_user_id = #{c.sendUserId}) order by ch.create_time asc")
    List<Map> queryChatMsg(@Param("c") ChatVo chatVo);
}