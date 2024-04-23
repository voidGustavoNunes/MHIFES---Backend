package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.rfid.mhifes.model.Users;


public interface UserRepository extends JpaRepository<Users, String>{
    

    UserDetails findByLogin(String login);
}
