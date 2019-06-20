package com.hexin.netty.webchat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author hexin
 * @createDate 2019年06月17日 11:16:00
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {

            //定义一对线程组
            //主线程组，用户接受客服端连接，但是不做任何处理
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //从线程组，让手下去完成任务
            EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.
                    group(bossGroup, workGroup).
                    channel(NioServerSocketChannel.class). //设置双向通道
                    childHandler(null); //子处理器

            //启动server，设置端口8088，t同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            //监听关闭channel
            channelFuture.channel().close().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
