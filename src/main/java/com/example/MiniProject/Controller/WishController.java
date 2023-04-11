package com.example.MiniProject.Controller;

import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Repository.WishRepository;
import com.example.MiniProject.Service.ServiceWish;
import com.example.MiniProject.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        wishLists.setWishlistName(name);

        //Store/save the wish list in the database
        serviceWish.createWishList(name);

        //Confirmation message to the model
        model.addAttribute("message", "Wish list has been created");

        //Redirect the user back to the wish list page/confirmation page
        return "redirect:/wishList";

    }

   @PostMapping("/edit")
    public String editWishlist(@RequestParam("idWislist")Long id, @RequestParam("WishlistName") String WishlistName){

        //Find ønskelisten i databse baseret på id

       //Opdater ønskelistens oplysninger baseret på de nye værdier fra den modtagne wishlist

       //Gem den opdaterende ønskeliste i databasen

       //Returner en bekræftelsesbesked eller en redirect til ønskelistens detaljeside
        return "redirect:/wishList" + id;
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



