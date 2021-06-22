package com.frontier.repository;

import com.frontier.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

}
