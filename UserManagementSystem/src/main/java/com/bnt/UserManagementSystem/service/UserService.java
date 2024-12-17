package com.bnt.UserManagementSystem.service;

import java.util.List;

import com.bnt.UserManagementSystem.model.User;

public interface UserService {

    public User saveUser(User user);

    public String verify(User user);

    public User updateUser(User user);
 
    public List<User> getAllUsers();
 
    public void deleteUser(long id);

    public void assignRoleToUser(Long userId, Long roleId);
 

}
