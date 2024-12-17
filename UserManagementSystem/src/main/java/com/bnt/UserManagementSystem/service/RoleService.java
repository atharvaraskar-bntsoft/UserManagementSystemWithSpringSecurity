package com.bnt.UserManagementSystem.service;

import java.util.List;

import com.bnt.UserManagementSystem.model.Role;

public interface RoleService {

    Role saveRole(Role role);

    List<Role> getAllRoles();

}
