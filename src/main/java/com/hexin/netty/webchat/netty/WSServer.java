package com.hexin.netty.webchat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hexin
 * @createDate 2019年06月17日 11:16:00
 */
@Component
@Slf4j
public class WSServer {
    //单例模式
    private static class SingletionWSServer {

        static final WSServer INSTANCE = new WSServer();

    }

    public static WSServer getInstance() {

        return SingletionWSServer.INSTANCE;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WSServer() {
        //定义一对线程组
        //主线程组，用户接受客服端连接，但是不做任何处理
        mainGroup = new NioEventLoopGroup();
        //从线程组，让手下去完成任务
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        //设置双向通道
        server.group(mainGroup, subGroup).channel(NioServerSocketChannel.class).childHandler(new WSServerInitialzer());
    }

    public void start(){
        this.future = server.bind(8088);
        log.info("启动成功");
    }

}
