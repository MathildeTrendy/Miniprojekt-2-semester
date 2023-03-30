package com.example.MiniProject.Repository;

import com.example.MiniProject.Model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {

    private JdbcTemplate jdbcTemplate;

    public void createUser(User user){
        String query = "INSERT INTO user(first_name, last_name, emial, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getPassword(), user.getPassword());
    }

    //Metoden tager en e-mail som parameter og returnerer enten den fundne bruger eller null, hvis brugeren ikke findes i databasen.
    public User getUserByEmail(String email){
        String query = "SELECT * FROM user WHERE email =?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class); //mapper resultatsæt kolonnerne til Java-objektets egenskaber baseret på navnene på egenskaberne.
        List<User> users = jdbcTemplate.query(query, rowMapper, email);
        return users.isEmpty() ? null : users.get(0); //hvis der ikke er nogen brugere i databasen med den angivne email, så returneres null.
    }




}
