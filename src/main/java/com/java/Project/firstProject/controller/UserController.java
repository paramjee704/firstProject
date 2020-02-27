package com.java.Project.firstProject.controller;

import com.java.Project.firstProject.entity.User;
import com.java.Project.firstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public String showUsrData(Model model) {
        model.addAttribute("users", userService.getUserData());
        return "home";
    }

    @GetMapping("/add")
    public String showAddForm(User user) {
        return "add_user";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result) {
        userService.createUser(user);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<User> user = userService.findByID(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "update_user";
        } else {
            model.addAttribute("user", "This" + id + " doesn't exist");
            return "home";
        }
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, Model model) {
        userService.updateUserData(id, user);
        model.addAttribute("users", userService.getUserData());
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws Exception {
        Optional<User> user = userService.findByID(id);
        if (user.isPresent()) {
            userService.deleteByUsrId(id);
            return "redirect:/list";
        } else {
            throw new Exception("invalid " + id);
        }
    }


}

