package com.hexin.netty.webchat.service;

import com.hexin.netty.webchat.entity.ChatMsg;

/**
 * @author hexin
 * @createDate 2019年06月25日 10:57:00
 */
public interface IChatMsgService {


    Integer saveMsg(ChatMsg chatMsg);
}
