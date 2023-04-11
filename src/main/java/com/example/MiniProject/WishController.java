package com.example.MiniProject;

import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Repository.WishRepository;
import com.example.MiniProject.Service.ServiceWish;
import com.example.MiniProject.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class WishController {

    //Håndtere dataadgang.
    private ServiceWish serviceWish;
    private Model model;
    private WishRepository wishRepository;
    private WishLists wishLists;

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
            model.addAttribute("LoginFailed", ""); // tilføjer en fejlbesked til modellen, som vises på login-siden, hvis brugeren ikke kan logge ind.
            return "Log In";
        }
    }

    @RequestMapping(value = "/createWishList", method = RequestMethod.POST)
    public String createWishList(@RequestParam String name, Model model){
        //Creates the desired object based on input from the form
        wishLists.setName(name);

        //Store/save the wish list in the database
        serviceWish.createWishList(name);

        //Confirmation message to the model
        model.addAttribute("message", "Wish list has been created");

        //Redirect the user back to the wish list page/confirmation page
        return "redirect:/wishList";

    }

    @PostMapping("/wishlist/{id}/edit")
    public String updateWishlist(@PathVariable("id") Long wishlistId, @RequestBody WishLists wishlists) {
        // Find ønskelisten i databasen baseret på wishlistId
        WishLists existingWishlists = wishRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wish list not found"));

        // Opdater ønskelistens oplysninger baseret på de nye værdier fra den modtagne wishlist
        existingWishlists.setName(wishlists.getName());

        // Gem den opdaterede ønskeliste i databasen
        wishRepository.save(existingWishlists);

        // Returner en bekræftelsesbesked eller en redirect til ønskelistens detaljeside
        return "Wish lish updated";
    }

    @DeleteMapping("/wishlist/{id}/delete")
    public String deleteWishlist(@PathVariable("id") Long wishlistId) {
        // Find ønskelisten i databasen baseret på wishlistId
        WishLists wishlists = wishRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wish list not found"));

        // Slet ønskelisten fra databasen
        wishRepository.deleteById(wishlistId);

        // Returner en bekræftelsesbesked eller en redirect til en relevant side
        return "Wish list deleted";
    }




}



