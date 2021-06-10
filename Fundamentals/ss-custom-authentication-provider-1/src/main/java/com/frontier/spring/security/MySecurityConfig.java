package com.frontier.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        /*This will all access to both the authenticated api
         * i.e. /hello and /bye*/
        //http.authorizeRequests().anyRequest().authenticated();

        /*This will only allow access to /hello api when it is authenticated
         * i.e. anybody can access /bye api but to access /hello user
         * should be authenticated*/
//        http.authorizeRequests()
//                .antMatchers("/hello")
//                .authenticated();

        /* Only authenticated user is allowed to access /hello api rest other api access is restricted
         * i.e. even authenticated user cannot access /bye api
         */
//        http.authorizeRequests()
//                .antMatchers("/hello")
//                .authenticated()
//                .anyRequest()
//                .denyAll();

        /* Only authenticated user is allowed to access /hello api rest other api access
         * can be accessed by non-authenticated users.
         * i.e. even non authenticated user can access /bye api
         */
        http.authorizeRequests()
                .antMatchers("/hello")
                .authenticated()
                .anyRequest()
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
