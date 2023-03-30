package com.example.MiniProject.Service;

import ch.qos.logback.core.model.Model;
import com.example.MiniProject.Model.User;
import com.example.MiniProject.Repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceWish {

    private WishRepository wishRepository;
    private Model model;

    public ServiceWish(WishRepository wishRepository, Model model) {
        this.wishRepository = wishRepository;
        this.model = model;
    }


    public void createUser(User user) throws InvalidInputException {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidInputException("All fields must be filled");
        }

        wishRepository.createUser(user);
    }
    //Metode der verificere en bruger/konto
    public boolean verifyAccount(String email, String password) {
            User user = wishRepository.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }

    }

    public class InvalidInputException extends Exception {
        public InvalidInputException(String errorMessage) {
            super(errorMessage);
        }
    }




}





