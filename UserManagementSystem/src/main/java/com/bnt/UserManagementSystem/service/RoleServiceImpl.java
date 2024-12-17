package com.bnt.UserManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.UserManagementSystem.controller.UserController;
import com.bnt.UserManagementSystem.model.Role;
import com.bnt.UserManagementSystem.model.User;
import com.bnt.UserManagementSystem.repository.RoleRespository;
import com.bnt.UserManagementSystem.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService{

   
    @Autowired
    private RoleRespository roleRepository;

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
   
    @Override
    public Role saveRole(Role role) {
        logger.info("Saving role: {}",role);
        Role savedRole = roleRepository.save(role);
        logger.info("Saved user with ID: {}", savedRole.getId());
        return savedRole;
    }

    @Override
    public List<Role> getAllRoles() {
        logger.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        logger.info("Fetched {} users", roles.size());
        return roles;
    }

    

   
}
