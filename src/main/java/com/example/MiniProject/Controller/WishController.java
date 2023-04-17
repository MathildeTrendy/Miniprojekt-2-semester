package com.example.MiniProject.Controller;


import com.example.MiniProject.DTO.UserFormDTO;
import com.example.MiniProject.DTO.WishlistFormDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        HttpSession session, Model model) {
        try {
            User user = wishRepository.verifyUser(email, password);
            if (user == null) {
                // User not found in the database, add an error message to the model
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
            session.setAttribute("user", user);
            return "redirect:/myprofile";
        } catch (LoginSampleException e) {
            // Handle the exception
            model.addAttribute("error", "An error occurred while logging in. Please try again later.");
            return "login";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String showCreateUser(Model model) {
        UserFormDTO user = new UserFormDTO();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signup")
    public String createUser(HttpServletRequest request, @ModelAttribute UserFormDTO userFormDTO) throws LoginSampleException {
        if (userFormDTO.getEmail() == null) {
            return "signupfail";
        }

        UserFormDTO newUserFormDTO = new UserFormDTO(userFormDTO.getFirstName(), userFormDTO.getLastName(), userFormDTO.getEmail(), userFormDTO.getPassword());
        User user = wishRepository.createUser(newUserFormDTO);

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

    @GetMapping("/myprofile")
    public String welcomeProfile(Model model) {
        model.addAttribute("welcome", "Welcome");
        return "myprofile";
    }

    @PostMapping("/myprofile/createlist")
    public String createWishlist(@RequestParam("wishlistName") String wishlistName, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        System.out.println("Received wishListName:" + wishlistName);
        try {
            if (user != null) {
                String userEmail = user.getEmail();
                int id = wishRepository.createWishList(wishlistName, user.getEmail());
                redirectAttributes.addFlashAttribute("successMessage", "Wishlist created successfully!");
                return "createlist";
            } else {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            wishLists.setWishListName(wishLists.getWishlistName());

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



