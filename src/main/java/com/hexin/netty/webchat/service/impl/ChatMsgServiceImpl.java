package com.hexin.netty.webchat.service.impl;

import com.hexin.netty.webchat.dao.ChatMsgMapper;
import com.hexin.netty.webchat.entity.ChatMsg;
import com.hexin.netty.webchat.service.IChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hexin
 * @createDate 2019年06月25日 10:59:00
 */
@Service
public class ChatMsgServiceImpl implements IChatMsgService {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Override
    public Integer saveMsg(ChatMsg chatMsg) {

       Integer code = chatMsgMapper.insert(chatMsg) ;
       return  code;
    }
}
