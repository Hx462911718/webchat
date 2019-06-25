package com.hexin.netty.webchat.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.hexin.netty.webchat.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @program: rtcservice
 * @author: LiJinXiong
 * @create: 2019-05-14 09:56
 * todo 后期以 事项作为 namespace 市民咨询的事项不同放置到不同的namespace 、前端去控制namespace
 **/

@Service
@Slf4j
public class SocketServiceImpl implements SocketService {


    @Autowired
    private SocketIOServer socketIOServer;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * @param id             用户id 、客服id
     * @param socketIOClient socket io client 对象
     */
    @Override
    public void put(String id, SocketIOClient socketIOClient) {
        put(id, socketIOClient.getSessionId().toString());
    }

    /**
     * @param id              id 用户id 、客服id
     * @param socketSessionId SocketIOClient 中的session id
     */
    @Override
    public void put(String id, String socketSessionId) {
        log.info("socket io 放置redis 参数id为{}，socketSessionId{}", id, socketSessionId);
        redisTemplate.opsForValue().set(id, socketSessionId);
    }

    /**
     * @param id 用户id 、客服id
     */
    @Override
    public void remove(String id) {

        log.info("移除socket io redis 关系参数{}", id);
        redisTemplate.delete(id);
    }

    /**
     * @param id          用户id 、客服id( 接收者id)
     * @param socketEvent
     * @param data        发送的消息
     */
    @Override
    public void sendEvent(String id, String socketEvent, Object... data) {

        log.info("发送数据给客户端、客户端id{}、事件类型{}、发送数据{}", id, socketEvent, data);

        String socketSessionId = redisTemplate.opsForValue().get(id).toString();

        if (StringUtils.isEmpty(socketSessionId)) {
            log.error("redis 保存的连接信息为空");
            return;
        }

        SocketIOClient socketIOClient = socketIOServer.getClient(UUID.fromString(socketSessionId));

        if (null == socketIOClient) {
            log.error("连接已经丢失");
            return;
        }

        socketIOClient.sendEvent(socketEvent, data);
        log.info("成功发送消息给{}", id);
    }

    /**
     * @return 正在聊天的房间数
     */
    @Override
    public Integer getChattingRooms() {
        return socketIOServer.getAllClients().size();
    }

    @Override
    public SocketIOClient get(String id) {
        String socketSessionId = redisTemplate.opsForValue().get(id).toString();

        if (StringUtils.isEmpty(socketSessionId)) {
            log.error("redis 保存的连接信息为空");
            return null;
        }

        SocketIOClient socketIOClient = socketIOServer.getClient(UUID.fromString(socketSessionId));

        if (null == socketIOClient) {
            log.error("连接已经丢失");
            return null;
        }

        return socketIOClient;
    }


}
