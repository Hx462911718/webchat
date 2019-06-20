package com.hexin.netty.webchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hexin
 * @createDate 2019年06月19日 17:06:00
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say")
    public String sayHello(){
        return "say --hi";
    }
}
