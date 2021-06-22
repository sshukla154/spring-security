package com.frontier.repository;

import com.frontier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
