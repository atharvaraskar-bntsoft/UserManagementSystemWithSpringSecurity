package com.bnt.UserManagementSystem.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.UserManagementSystem.model.Role;
import com.bnt.UserManagementSystem.model.User;
import com.bnt.UserManagementSystem.service.RoleService;
import com.bnt.UserManagementSystem.service.UserService;

@RestController
@RequestMapping("/usermanagementapi/roles")
public class RoleController {

 
     @Autowired
    private     RoleService roleService;

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @PostMapping
    public ResponseEntity<Role> createRole( @RequestBody Role role) {
      logger.info("Creating new role with data: {}",role);
        Role createdRole = roleService.saveRole(role);
        logger.info("Created role with ID: {}", createdRole.getId());
        return new ResponseEntity<Role>(createdRole, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Role>>getAllRoles(){
        logger.info("Fetching all roles");
        List<Role> listOfAllRoles = roleService.getAllRoles();
        logger.info("Fetched {} users", listOfAllRoles.size());
        return new ResponseEntity<List<Role>>(listOfAllRoles, HttpStatus.OK);
    }

}
