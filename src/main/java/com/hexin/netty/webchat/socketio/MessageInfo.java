package com.hexin.netty.webchat.socketio;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天记录
 * </p>
 *
 * @author ljx
 * @since 2019-05-05
 */
@Data
@Accessors(chain = true)
public class MessageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 消息发送者
     */
    private String sender;
    /**
     * 消息接收者
     */
    private String receiver;
    /**
     * 内容类型
     video 视频类型
     message 文本类型
     */
    private String contentType;
    /**
     * 文本内容、当类型为video的时候、这个字段为null
     */
    private String content;
    /**
     * 多媒体内容、保存视频文件的路径
     */
    private String mediaPath;
    /**
     * 消息发送时间
     */
    private Date sendTime;
    /**
     * 客户接入表示
     Y：成功进入房间
     N：尚未进入房间
     */
    private String readFlag;
    /**
     * 发送标识
     U：用户发送到客服
     C：客服发送到用户
     */
    private String msgFlag;
    /**
     * 会话Id
     */
    private String sessionId;
    /**
     * 消息读取时间
     */
    private Date readTime;

    /**
     * 发送临时sockeId
     */
    private String sendSocketId;

    /**
     * 接收临时sockeId
     */
    private String receiverSocketId;

    /**
     * 事项编码
     */
    private List<String> eventCodes;


    /**
     * payload 数据
     */
    private Map<String,Object> payload;

    /**
     * 操作类型
     */
    private String action;
}
