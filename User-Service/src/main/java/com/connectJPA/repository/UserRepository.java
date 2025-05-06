package com.connectJPA.repository;

import com.connectJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email, String phone);
    User findByUsername(String username);


}
