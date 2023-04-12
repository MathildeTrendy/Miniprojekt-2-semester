package com.example.MiniProject.Controller;

import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Repository.WishRepository;
import com.example.MiniProject.Service.ServiceWish;
import com.example.MiniProject.Model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@Controller
public class WishController {

    //Håndtere dataadgang.
    private ServiceWish serviceWish;

    public WishController(ServiceWish serviceWish) {
        this.serviceWish = serviceWish;
    }

    @GetMapping("/signup")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logIn(@RequestParam String email, @RequestParam String password, Model model) {
        if (serviceWish.verifyAccount(email, password)) {
            return "WishListPage";
        } else {
            model.addAttribute("LoginFailed", ""); // tilføjer en fejlbesked til modellen, som vises på login-siden, hvis brugeren ikke kan logge ind.
            return "login";
        }
    }

    @RequestMapping(value = "/createWishList", method = RequestMethod.POST)
    public String createWishList(@RequestParam String name, Model model){
        //Creates the desired object based on input from the form
        WishLists wishLists = new WishLists(name);
        wishLists.setWishlistName(name);

        //Store/save the wish list in the database
        serviceWish.createWishList(name);

        //Confirmation message to the model
        model.addAttribute("message", "Wish list has been created");

        //Redirect the user back to the wish list page/confirmation page
        return "redirect:/wishList";

        //laver dem nede i metoden og over
        //alt i toppen er en deler
    }

    @PostMapping("/editWishlist/{id}")
    public ResponseEntity<String> editWishlist(@PathVariable("id") int id, @RequestBody WishLists wishLists) {
        try {
            // Opret en instans af WishRepository
            WishRepository wishRepository = new WishRepository();

            // Find ønskelisten i databasen baseret på id
            wishRepository.findById(id);

            // Opdater ønskelistens oplysninger baseret på de nye værdier fra den modtagne wishlist
            wishLists.setWishlistName(wishLists.getWishlistName());

            // Gem den opdaterede ønskeliste i databasen
            wishRepository.updateWishlist(wishLists);

            // Returner en bekræftelsesbesked
            return ResponseEntity.ok("Wishlist updated successfully");
        } catch (SQLException ex) {
            // Håndter eventuelle fejl og returner en fejlbesked
            return ResponseEntity.badRequest().body("Failed to update wishlist: " + ex.getMessage());
        }
    }

/*
    @DeleteMapping("/wishlist/{id}/delete")
    public String deleteWishlist(@PathVariable("id") Long wishlistId) {
        // Find ønskelisten i databasen baseret på wishlistId
        WishLists wishlists = wishRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wish list not found"));

        // Slet ønskelisten fra databasen
        wishRepository.deleteById(wishlistId);

        // Returner en bekræftelsesbesked eller en redirect til en relevant side
        return "Wish list deleted";
    }



 */


}



