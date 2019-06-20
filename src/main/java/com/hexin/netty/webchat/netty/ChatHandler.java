package com.hexin.netty.webchat.netty;

/**
 * @author hexin
 * @createDate 2019年06月20日 9:46:00

 * Copyright © 2018Nathan.Lee.Salvatore. All rights reserved.

 *

 * @Title: ChatHandler1.java

 * @Prject: imooc-netty-hello

 * @Package: com.imooc.netty.websocket

 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体

 * @author: ZHONGZHENHUA

 * @date: 2018年11月12日 上午6:00:36

 * @version: V1.0

 */
import java.time.LocalDateTime;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author ZHONGZHENHUA
 *
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

    // 用于记录和管理所有客户端的channel
    private static ChannelGroup clients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // TODO Auto-generated method stub
        // 获取客户端传输过来的消息
        String content = msg.text();
        System.out.println("接收到的数据：" + content);

/*        for(Channel channel: clients) {
            channel.writeAndFlush(msg);
            channel.writeAndFlush(
                new TextWebSocketFrame(
                        "[服务器在]" + LocalDateTime.now()
                        + "接受到消息,  消息为：" + content));
        }*/

        // 下面这个方法，和上面的for循环，一致
        clients.writeAndFlush(
                new TextWebSocketFrame(
                        "[服务器在]" + LocalDateTime.now()
                                + "接受到消息,  消息为：" + content));

    }
    /**
     *    当客户端连接服务端之后（打开连接）
     *     获取客户端的channel，并且放到ChannelGroup中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户单的channel
        //clients.remove(ctx.channel());
        System.out.println(ctx.channel().id());
        System.out.println("客户端断开，channel对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channel对应的短id为：" + ctx.channel().id().asShortText());
    }


}