package com.hexin.netty.webchat;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author hexin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hexin.netty.webchat.dao")
@ComponentScan(basePackages = {"com.hexin.netty.webchat"})
public class WebchatApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebchatApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebchatApplication.class, args);
	}


	@Bean
	public SocketIOServer socketIOServer(@Value("${socketio.host}")String host, @Value("${socketio.port}")int port){
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")){ //本地测试使用localhost
			System.out.println("this is windows");
			config.setHostname(host);
		}else {
			config.setHostname(host);
		}
		config.setPort(port);
		config.setPingInterval(30000);
		final  SocketIOServer server = new SocketIOServer(config);
		return  server;
	}


	/**
	 * tomcat启动时，扫描socket服务器并注册
	 * @param socketIOServer
	 * @return
	 */
	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer){
		return new SpringAnnotationScanner(socketIOServer);
	}


}
