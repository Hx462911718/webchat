package com.hexin.netty.webchat.socketio;

import com.alibaba.druid.util.StringUtils;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.hexin.netty.webchat.constant.SocketCodeConstant;
import com.hexin.netty.webchat.entity.ChatMsg;
import com.hexin.netty.webchat.entity.FriendsRequest;
import com.hexin.netty.webchat.service.IChatMsgService;
import com.hexin.netty.webchat.service.IUserService;
import com.hexin.netty.webchat.service.SocketService;
import com.hexin.netty.webchat.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.plugin.util.UIUtil;


/**
 * @author YLY
 *
 */
@Component
public class MessageEventHandler {
    @Autowired
    private SocketService socketService ;

    @Autowired
    private IChatMsgService chatMsgService;

    @Autowired
    private IUserService userService;

    public static SocketIOServer socketIOServer;




    public MessageEventHandler(SocketIOServer server){
        socketIOServer = server;
    }

    @OnConnect
    public void onConnect(SocketIOClient client){
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        client.set("mac",mac);
        socketService.put(mac,client);
        System.out.println("客户端："+ client.getSessionId() + "已连接,mac="+mac);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client){


        System.out.println("客户端："+client.getSessionId() + "断开连接");
    }



    @OnEvent(value = SocketCodeConstant.CHAT_MESSAGE)
    public void chatMsg(SocketIOClient client, AckRequest request,MessageInfo data){

        System.out.println("客户:" + client.getSessionId());
        System.out.println("发来消息:" + data);
        try {

            //插入数据库
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setId(UUIDUtil.getUUID());
            chatMsg.setAcceptUserId(data.getReceiver());
            chatMsg.setSendUserId(data.getSender());
            chatMsg.setSignFlag(1);
            chatMsg.setMsg(data.getContent());
            chatMsg.setCreateTime(data.getSendTime());
            Integer code = chatMsgService.saveMsg(chatMsg);
            if (code > 0) {
                // 1标记为发送成功
                request.sendAckData("1");
                socketService.get(data.getSender()).sendEvent(SocketCodeConstant.MESSAGE_EVENT, data);
                socketService.get(data.getReceiver()).sendEvent(SocketCodeConstant.MESSAGE_EVENT, data);
            } else {
                request.sendAckData("0");
            }
        }catch (Exception e){
            e.printStackTrace();
            // 0标记为发送失败
            request.sendAckData("0");
        }
    }





    @OnEvent(value = SocketCodeConstant.NOTICE_MESSAGE)
    public void noticeMessage(SocketIOClient client, AckRequest request,MessageInfo data){


        try {

            FriendsRequest friendsRequest = new FriendsRequest();
            friendsRequest.setAcceptUserId(data.getReceiver());
            friendsRequest.setSendUserId(data.getSender());
            friendsRequest.setRequestDateTime(data.getSendTime());
            int code = userService.friendsRequest(friendsRequest);
            if (code > 0) {
                // 1标记为发送成功
                request.sendAckData("1");
                socketService.get(data.getReceiver()).sendEvent(SocketCodeConstant.FRIENDS_REQUEST, data);
            } else {
                request.sendAckData("0","添加好友失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            // 0标记为发送失败
            request.sendAckData("0");
        }
    }




}
