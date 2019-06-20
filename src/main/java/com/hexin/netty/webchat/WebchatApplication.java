package com.hexin.netty.webchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author hexin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hexin.netty.webchat.dao")
//扫描所需要的包的路径
@ComponentScan(basePackages = {"com.hexin.netty.webchat"})
public class WebchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebchatApplication.class, args);
	}

}
