package com.bnt.UserManagementSystem.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bnt.UserManagementSystem.model.Role;
import com.bnt.UserManagementSystem.model.User;
import com.bnt.UserManagementSystem.repository.RoleRespository;
import com.bnt.UserManagementSystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    private RoleRespository roleRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        logger.info("Saving user: {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));  
        // List<Role>newrole=user.getRoles();
        // user.getRoles().addAll(newrole)  ;
        
        // Role role =new Role();
        // role.getUsers().add(user);

        // for (Role role : user.getRoles()) {
        //     role.getUsers().add(user);
        // }
        
        User savedUser = userRepository.save(user);
        logger.info("Saved user with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    public String verify(User user) {
        org.springframework.security.core.Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            user.setEmail("login successful");
            user.setId(21L);  // Static user ID for testing (replace with dynamic logic)
            return jwtService.genrateToken(user.getUserName());
        } else {
            throw new UsernameNotFoundException("Please enter valid credentials");
        }
    }

    @Override
    public User updateUser(User user) {
        logger.info("Updating user with ID: {}", user.getId());
        User updatedUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + user.getId()));
        updatedUser.setUserName(user.getUserName());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt new password
        updatedUser.setEmail(user.getEmail());
        return userRepository.save(updatedUser);
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();
        logger.info("Fetched {} users", users.size());
        return users;
    }

    @Override
    public void deleteUser(long id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.info("Deleted user with ID: {}", id);
    }

    @Override
    public void assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Role role = roleRepository.findById(roleId);
                //.orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));

        user.getRoles().add(role);
        userRepository.save(user);
    }
}
