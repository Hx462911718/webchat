package com.hexin.netty.webchat.service;

import com.corundumstudio.socketio.SocketIOClient;

/**
 * @program: rtcservice
 * @author: LiJinXiong
 * @create: 2019-05-14 09:54
 **/

public interface SocketService {

    /**
     * @param id             用户id 、客服id
     * @param socketIOClient socket io client 对象
     */
    void put(String id, SocketIOClient socketIOClient);

    /**
     * @param id              id 用户id 、客服id
     * @param socketSessionId SocketIOClient 中的session id
     */
    void put(String id, String socketSessionId);


    /**
     * @param id 用户id 、客服id
     */
    void remove(String id);


    /**
     * @param id          用户id 、客服id
     * @param socketEvent
     * @param data        发送的消息
     */
    void sendEvent(String id,
                   String socketEvent, Object... data);


    /**
     * 获取正在聊天的个数
     *
     * @return
     */
    Integer getChattingRooms();


    /**
     * 获取socket客户端
     * @param id
     * @return
     */
    SocketIOClient get(String id);


}
