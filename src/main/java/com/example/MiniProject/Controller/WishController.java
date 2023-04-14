package com.example.MiniProject.Controller;

import DTO.UserFormDTO;
import DTO.WishlistFormDTO;
import com.example.MiniProject.Model.User;
import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Repository.DbRepository;
import com.example.MiniProject.Repository.WishRepository;

import com.example.MiniProject.Utility.LoginSampleException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class WishController {

    //Håndtere dataadgang.
    WishRepository wishRepository;

    public WishController(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @GetMapping("/")
    public String index() {
        return "frontPage";
    }
    @PostMapping({"/",""})
    public String index(HttpServletRequest request, @ModelAttribute UserFormDTO userformDTO, Model model) {
        // Trying to login
        try {
            User user = wishRepository.verifyUser(userformDTO.getEmail(), userformDTO.getPassword());

            request.getSession().setAttribute("email", user.getEmail());
            return "redirect:/myprofile";

            // if login fails, redirect to index with error message
        } catch (LoginSampleException e) {
            model.addAttribute("errorMessage", "An error occurred");
            return "frontPage";
        }
    }

    @GetMapping("/signup")
    public String showCreateUser(Model model) {
        UserFormDTO user = new UserFormDTO();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signup")
    public String createUser(HttpServletRequest request, @ModelAttribute UserFormDTO userFormDTO) throws LoginSampleException {
        User user = wishRepository.createUser(userFormDTO);
        if (user != null) {
            request.getSession().setAttribute("email", user.getEmail());
            return "redirect:/signupsucces";
        } else {
            return "signupfail";
        }
    }

    @GetMapping("signupsucces")
    public String signUpSucces() {
        return "signupsucces";
    }


   /* @GetMapping("/wishlistOverview")
    public String showAllWishlists(@RequestParam ("listName") String listName) {
        return "createWishlist";
    }
**/

    @GetMapping("/myprofile")
    public String welcomeProfile(Model model) {
        model.addAttribute("welcome", "Welcome");
        return "myprofile";
    }



    @PostMapping(value = "/myprofile{listName}")
    public String createWishlist(@RequestParam("email") String email, HttpSession userSession, Model model, @RequestParam("listName") WishlistFormDTO listName) {
        userSession.setAttribute("email", email);
        userSession.getAttribute("email");
        if (email.length() > 0) {
            wishRepository.createWishList(listName);
        }
        return "redirect:/myprofile";
    }


    @PostMapping("/editWishlist/{id}")
    public String editWishlist(@RequestParam("id") int id, @RequestBody WishLists wishLists) throws SQLException {
        wishRepository.editWishlist(id, wishLists);
        return "redirect:/myprofile";
    }

    @PostMapping("/deleteWishlist/{id}")
    public int deleteWishlist(@PathVariable("id") int id, @RequestBody WishLists wishLists) {
        return wishRepository.deleteWishlist(id, wishLists);
    }


/*
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



