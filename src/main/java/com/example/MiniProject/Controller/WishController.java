package com.example.MiniProject.Controller;

import DTO.UserFormDTO;
import DTO.WishlistFormDTO;
import com.example.MiniProject.Model.WishLists;
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

    @GetMapping({"/", ""})
    public String index(){

        return "frontPage";
    }

    @GetMapping("/signup")
    public String showCreateUser(Model model) {
        UserFormDTO user = new UserFormDTO();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signup/save")
    public String createUser(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("email") String email, @RequestParam("password") String password) {
        if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            UserFormDTO userFormDTO = new UserFormDTO(firstname, lastname, email, password);
            return "signupsucces";
        } else {
            return "redirect:/signupfail";
        }
    }
    @GetMapping("signupsucces")
    public String signUpSucces(){
        return "redirect:/signUpSucces";
    }
    @PostMapping(value = "/login")
    public String logIn(@RequestParam("email") String email, @RequestParam ("password")String password, Model model, HttpSession userSession) throws LoginSampleException, SQLException {
        userSession.setAttribute("email", email);
        userSession.getAttribute("email");
        if (email.length() > 0) {
            wishRepository.verifyAccount(email, password);
            return "redirect:/WishListPage";
        } else
        { model.addAttribute("LoginFailed", ""); // tilføjer en fejlbesked til modellen, som vises på login-siden, hvis brugeren ikke kan logge ind.
            return "redirect:/login";
        }
    }
    @GetMapping("/login")
    public String Login(@RequestParam ("email") String email, @RequestParam ("password")String password) {
        return "login";
    }

    @GetMapping("/createWishlist")
    public String createWishlist(@RequestParam ("listName") String listName) {
        return "createWishlist";
    }
//

    @PostMapping(value = "/createWishlist")
    public String createWishlist(@RequestParam ("email") String email, HttpSession userSession, Model model, @RequestParam ("listName")WishlistFormDTO listName){
        userSession.setAttribute("email", email);
        userSession.getAttribute("email");
        if (email.length() > 0 ) {
            wishRepository.createWishList(listName);
            return "redirect:/WishListPage";
        } else
            { model.addAttribute("Failed to create list", "");
                return "redirect:/createWishlist";
            }

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



