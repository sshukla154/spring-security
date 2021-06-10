package com.frontier.spring.security.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello(){
        log.info("HelloController.getHello :::");
        return "Spring Security!!!";
    }

    @GetMapping("/bye")
    public String getBye(){
        log.info("HelloController.getBye :::");
        return "Get Lost!!!";
    }

}
