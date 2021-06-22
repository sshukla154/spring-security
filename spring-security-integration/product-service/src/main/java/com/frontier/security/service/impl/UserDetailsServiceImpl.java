package com.frontier.security.service.impl;

import com.frontier.model.User;
import com.frontier.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byEmail = userRepo.findByEmail(username);
        if(byEmail==null){
            throw new UsernameNotFoundException("User not found for email " + username);
        }
        return new org.springframework.security.core.userdetails.User(byEmail.getEmail(), byEmail.getPassword(), byEmail.getRoles());
    }

}
