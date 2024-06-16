package com.binhlc.adminweb.controller;

import com.binhlc.adminweb.entity.UserEntity;
import com.binhlc.adminweb.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public String showAllUsers(Model model) {
        List<UserEntity> list = userRepo.findAll();
        model.addAttribute("userList", list);
        return "users";
    }
}
