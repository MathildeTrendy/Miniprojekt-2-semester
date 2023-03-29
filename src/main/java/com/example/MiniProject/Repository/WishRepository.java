package com.example.MiniProject.Repository;

import com.example.MiniProject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(User user){
        String query = "INSERT INTO user(first_name, last_name, emial, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getPassword(), user.getPassword());
    }




}
