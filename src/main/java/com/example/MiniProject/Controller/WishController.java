package com.example.MiniProject.Controller;

import Service.ServiceWish;
import com.example.MiniProject.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishFreaks")
public class WishController {

    private ServiceWish serviceWish;

    public WishController(ServiceWish serviceWish) {
        this.serviceWish = serviceWish;
    }

    @GetMapping("/signup")
    public String signupform(Model model){
        model.addAttribute("user", "userSignup");
        return "signUp";
    }
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            serviceWish.createUser(user);
            return ResponseEntity.ok("Account created");
        } catch (ServiceWish.InvalidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public String loginform(Model model){
        model.addAttribute("user", "userLogin");
        return "login";
    }

}



