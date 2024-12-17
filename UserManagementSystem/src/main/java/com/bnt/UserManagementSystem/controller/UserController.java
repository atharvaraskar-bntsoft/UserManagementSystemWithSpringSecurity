package com.bnt.UserManagementSystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.UserManagementSystem.model.User;
import com.bnt.UserManagementSystem.service.UserService;

@RestController
@RequestMapping("/usermanagementapi/users")
public class UserController {

    @Autowired
    UserService usersService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

   // @PostMapping("/register")
   @PostMapping(value = "/register", consumes = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<User> createUserRegister(@RequestBody User user){
        logger.info("Creating new user with data: {}", user);
        User createdUser = usersService.saveUser(user);
        logger.info("Created user with ID: {}", createdUser.getId());
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> createUserLogin(@RequestBody User user){
        logger.info("logging new user with data: {}", user);
        String Token = usersService.verify(user);
        logger.info("Created user ");
        return new ResponseEntity<>(Token, HttpStatus.CREATED);
    }

    @GetMapping("/admi")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>>getAllUsers(){
        logger.info("Fetching all users");
        List<User> listOfAllUsers = usersService.getAllUsers();
        logger.info("Fetched {} users", listOfAllUsers.size());
        return new ResponseEntity<List<User>>(listOfAllUsers, HttpStatus.OK);
    }

    @PutMapping("/admi")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        logger.info("Updating user with ID: {}", user.getId());
        User updatedUser = usersService.updateUser(user);
        logger.info("Updated user: {}", updatedUser);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

      
     @DeleteMapping("admi/{id}")
     @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        logger.info("Deleting user with ID: {}", id);
        usersService.deleteUser(id);
        logger.info("Deleted user with ID: {}", id);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Optional<Users>> getUserById(@PathVariable("id") long id){
    //     logger.info("Fetching user with ID: {}", id);
    //     Optional<Users> optionalUser = usersService.getUserById(id);
    //     logger.info("Fetched user: {}", optionalUser.get());
    //     return new ResponseEntity<Optional<Users>>(optionalUser, HttpStatus.OK);
    // }


}
