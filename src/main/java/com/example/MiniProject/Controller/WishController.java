package com.example.MiniProject.Controller;

import Service.ServiceWish;
import com.example.MiniProject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishFreaks")
public class WishController {

    private ServiceWish serviceWish;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            serviceWish.createUser(user);
            return ResponseEntity.ok("Account created");
        } catch (ServiceWish.InvalidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}



