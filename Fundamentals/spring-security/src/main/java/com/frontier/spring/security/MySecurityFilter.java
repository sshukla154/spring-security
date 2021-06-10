package com.frontier.spring.security;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class MySecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("Before sending the servletRequest to AuthenticationManager :::::: ");

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("After getting the servletResponse from AuthenticationManager :::::: ");

    }

}
