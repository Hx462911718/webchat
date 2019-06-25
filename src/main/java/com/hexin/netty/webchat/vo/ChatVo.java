package com.hexin.netty.webchat.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author hexin
 * @createDate 2019年06月25日 14:22:00
 */
@Data
public class ChatVo {

    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "accept_user_id")
    private String acceptUserId;

    private String msg;
}
