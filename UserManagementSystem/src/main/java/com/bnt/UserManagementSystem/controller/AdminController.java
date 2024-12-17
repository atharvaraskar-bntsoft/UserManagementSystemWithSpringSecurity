package com.bnt.UserManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.UserManagementSystem.service.UserService;

@RestController
@RequestMapping("/usermanagementapi/admin")
@PreAuthorize("hasRole('ADMIN')") 
public class AdminController {

    @Autowired
    UserService usersService;

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> assignRoleToUser(
            @PathVariable Long userId, 
            @PathVariable Long roleId) {
            usersService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned successfully");
    }

}
