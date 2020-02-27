package com.java.Project.firstProject.controller;

import com.java.Project.firstProject.entity.User;
import com.java.Project.firstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUsr(@Valid @RequestBody User usr) {
        return this.userService.createUser(usr);
    }

    @GetMapping("/users")
    public List<User> getUsr() {
        return userService.getUserData();
    }

    @PutMapping("/users/{id}")
    public Optional<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User usr) {
        return userService.updateUserData(userId, usr);
    }

    @DeleteMapping("/users/{id}")
    public Optional<?> deleteUser(@PathVariable Long id) {
        return userService.deleteByUsrId(id);
    }
}

