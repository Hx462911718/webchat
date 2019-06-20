package com.hexin.netty.webchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hexin
 */
@SpringBootApplication
//扫描所需要的包的路径
@ComponentScan(basePackages = {"com.hexin.netty.webchat"})
public class WebchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebchatApplication.class, args);
	}

}
