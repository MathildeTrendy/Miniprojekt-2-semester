package com.example.MiniProject.Repository;

import com.example.MiniProject.Model.Items;
import com.example.MiniProject.Model.User;
import com.example.MiniProject.Model.WishLists;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class WishRepository {

    // JDBC-connection-object - database handling
    private JdbcTemplate jdbcTemplate;

    // Constructor which inject the JdbcTemplate
    public WishRepository(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Establishing a connection to the database using the JDBC API.
    String url = "jdbc:mysql://localhost:6060/miniProjekt"; // URL to the database
    String user = "root"; // Username for the database
    String password = "SabrinaMathilde"; // Password to the database

    //Creates a database connection in Java by specifying the URL, username, and password.
    Connection connection = DriverManager.getConnection(url, user, password);

    public WishRepository() throws SQLException {}

    public void createUser(User user){
        //SQL query used to insert specified data into the database.
        String query = "INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

        //The update query will be executed in the database to therefore be able to insert the new user with the specified values.
        jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getPassword(), user.getPassword());
    }

    // Method to verifty a user by their email adress
    public User getUserByEmail(String email){
        //SQL query used to insert specified data into the database.
        String query = "SELECT * FROM user WHERE email =?";

        //Creates a rowMapper, that is used to map rows from a database query to objects of the User class in Java using the BeanPropertyRowMapper implementation.
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        //Performs a SELECT query on the database using a jdbcTemplate object and maps the results to a list of User objects.
        List<User> users = jdbcTemplate.query(query, rowMapper, email);

        //if there are no users in the database with the specified email, null is returned.
        return users.isEmpty() ? null : users.get(0);
    }

    // Method to create a new wishlist in the database
    public void createWishList(String name) {
        //SQL query used to insert specified data into the database.
        String query = "INSERT INTO wish_list (name) VALUES (?)";

        // Parameter for SQL-query
        Object[] params = {name};

        //Preforms tge SQL-query with the parameter via JdbcTemplate
        jdbcTemplate.update(query, params);
    }

    public static Optional<WishLists> findById(Long id) {
       //String query =

        // Implementer logik for at finde en ønskeliste baseret på id og returnere den
        // Returner en Optional<WishList> for at håndtere tilfælde, hvor ønskelisten ikke findes
        return WishRepository.findById(id);
    }


    }


 /*


    public static WishLists save(WishLists wishList) {
        // Implementer logik for at gemme en ønskeliste eller opdatere en eksisterende
        // Returner den gemte/opdaterede ønskeliste

        return WishRepository.save(wishList);
    }

    public static void deleteById(Long id) {
        // Implementer logik for at slette en ønskeliste baseret på id
        WishRepository.deleteById(id);
    }
*/
















