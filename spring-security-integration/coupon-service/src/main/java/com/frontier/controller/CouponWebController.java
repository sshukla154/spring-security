package com.frontier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CouponWebController {

    @GetMapping("/")
    public String index() {
    	log.info("CouponWebController.index ::::");
        return "index";
    }
}
