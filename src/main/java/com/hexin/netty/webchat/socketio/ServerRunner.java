package com.hexin.netty.webchat.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value = 1)
public class ServerRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ServerRunner.class);

    private final SocketIOServer server;

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... strings) throws Exception {
        server.start();
        logger.info("socketio.io.启动成功！0");
    }
}
