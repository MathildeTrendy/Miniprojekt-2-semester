package com.example.MiniProject.Controller;

import Service.ServiceWish;
import com.example.MiniProject.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class WishController {

    private ServiceWish serviceWish;
    private Model model;

    public WishController(ServiceWish serviceWish, Model model) {
        this.serviceWish = serviceWish;
        this.model = model;
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

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String logIn(@RequestParam String email, @RequestParam String password, Model model) {
        if (serviceWish.verifyAccount(email, password)) {
            return "redirect:/wishList";
        } else {
            model.addAttribute("LoginFailed", "");
            return "Log In";
        }
    }


}



