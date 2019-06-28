package com.hexin.netty.webchat.constant;

public interface SocketCodeConstant {


    /**
     * 聊天消息 client -> server
     */
    String CHAT_MESSAGE = "chatMessage";

    /**
     * 通知消息 client -> server
     */
    String NOTICE_MESSAGE = "noticeMessage";


    /**
     * 聊天消息 server -> client
     */
    String MESSAGE_EVENT = "messageEvent";


    /**
     * 队列信息变化
     */
    String QUEUE_CHANGE = "queueChange";


    /**
     * 用户接入聊天
     */
    String ACCESS_CHATIN  = "accessChatIn";


    /**
     * 用户接入视频
     */
    String ACCESS_VIDEOIN = "accessVideoIn";


    /***
     * 结束会话事件
     */
    String FINISH_SESSION = "finishSession";

    /***
     * 呼叫转移事件
     */
    String TRANS_SESSION = "transSession";

    /***
     * 取消排队
     */
    String CANCEL_QUEUE = "cancelQueue";

    /***
     * 好友请求
     */
    String FRIENDS_REQUEST = "friendsRequest";



}
