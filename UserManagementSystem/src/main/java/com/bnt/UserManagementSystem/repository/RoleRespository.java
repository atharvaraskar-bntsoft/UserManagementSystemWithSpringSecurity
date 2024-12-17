package com.bnt.UserManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.UserManagementSystem.model.Role;
import com.bnt.UserManagementSystem.model.User;

@Repository
public interface RoleRespository extends JpaRepository<Role,Integer> {
              Optional<Role> findByName(String name);

            Role findById(Long roleId);  
}
