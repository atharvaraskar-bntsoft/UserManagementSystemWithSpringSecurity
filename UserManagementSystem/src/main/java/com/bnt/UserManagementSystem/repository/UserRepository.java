package com.bnt.UserManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.UserManagementSystem.model.User;

@Repository
public interface UserRepository extends  JpaRepository<User,Long>{
       Optional<User> findByUserName(String userName);  
 
}
