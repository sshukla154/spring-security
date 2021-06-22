package com.frontier.security.config;

import com.frontier.security.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/*
 *
 * USER <===Request/Response===> AuthenticationFilter <===Request/Response===> AuthenticationManager <===Request/Response===> AuthenticationProvider (internally uses UserDetailsService and Password)
 *
 *
 * */

@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Overridden UserDetailsService
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    //Over-riding Password-Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("WebSecurityConfig.passwordEncoder :: http ::::");
        return new BCryptPasswordEncoder();
    }

    /*In below API we are explicitly disabling the CSRF*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("WebSecurityConfig.config :: http ::::");

        http.formLogin();

        /*Using antMatcher()*/
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/couponapi/coupons").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/couponapi/coupons/").hasAnyRole("ADMIN")
//                .anyRequest().denyAll()
//                .and().csrf().disable();

        /*Using mvcMatcher() : Advanced and latest*/
//        http.authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/couponapi/coupons").hasAnyRole("USER", "ADMIN")
//                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons/").hasAnyRole("ADMIN")
//                .anyRequest().denyAll()
//                .and().csrf().disable();

        /*Using mvcMatcher() with STAR pattern */
        /* Having ** can damage our URL, so prefer to use regular expression*/
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/couponapi/coupons/", "/", "/index").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons/").hasAnyRole("ADMIN")
//                .anyRequest().denyAll()
                .and().csrf().disable();

        /*Using mvcMatcher() with regular expression*/
//        http.authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{couponCode:^[A-Z]*$}").hasAnyRole("USER", "ADMIN")
//                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons/").hasAnyRole("ADMIN")
//                .anyRequest().denyAll()
//                .and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        log.info("WebSecurityConfig.config :: authenticationManagerBuilder ::::");
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl);
    }


}
