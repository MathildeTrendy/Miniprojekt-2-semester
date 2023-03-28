package com.example.MiniProject.Controller;

import com.example.MiniProject.Repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WishController {

    WishRepository wishRepository;

    public WishController(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }


}
