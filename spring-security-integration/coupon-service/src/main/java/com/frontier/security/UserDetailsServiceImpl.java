package com.frontier.security;

import com.frontier.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.frontier.model.User userByEmail = userRepo.findByEmail(email);
        if(userByEmail==null){
            throw new UsernameNotFoundException("User not found for email " + email);
        }
        return new User(userByEmail.getEmail(), userByEmail.getPassword(), userByEmail.getRoles());
    }
}
