package com.example.MiniProject.Repository;

import DTO.UserFormDTO;
import com.example.MiniProject.Model.Items;
import com.example.MiniProject.Model.User;
import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Utility.LoginSampleException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class WishRepository {

    public User createUser(UserFormDTO userDto) throws LoginSampleException {
        //Creates a database connection in Java by specifying the URL, username, and password.
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {
            //SQL query used to insert specified data into the database.
            String SQL = "INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDto.getFirstName());
            preparedStatement.setString(2, userDto.getLastName());
            preparedStatement.setString(3, userDto.getEmail());
            preparedStatement.setString(4, userDto.getPassword());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            long id = resultSet.getLong(1);
            User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),  userDto.getPassword());
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new LoginSampleException(e.getMessage());
        }
    }

    // Method to verify a user by their email adress
    public User verifyByEmail(String email) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {

            //SQL query used to insert specified data into the database.
            String SQL = "SELECT * FROM user WHERE email =?";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return verifyByEmail(email);
    }

    // Method to create a new wishlist in the database
    public void createWishList(String name) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {

            //SQL query used to insert specified data into the database.
            String SQL = "INSERT INTO wish_list (name) VALUES (?)";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
    }


    public boolean verifyAccount (String email, String password) throws LoginSampleException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {

            String SQL = "SELECT COUNT (*) AS count from user WHERE email=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");
            return count == 1;
        }catch (SQLException e){
            e.printStackTrace();
            throw new LoginSampleException ("Wrong email or password, try again");


        }

    }


    /*
    public boolean verifyAccount(String email, String password) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {
        String SQL = "SELECT COUNT(*) AS count FROM user WHERE email=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt("count");
        return count == 1; // Returnerer true, hvis der findes en bruger med den angivne e-mail og adgangskode, ellers false.
    } catch (SQLException e) {
        // Håndter fejl, f.eks. logning eller kast en egendefineret exception
        e.printStackTrace();
        return false; // eller kast en LoginSampleException
    }
}







    public boolean verifyAccount(String email, String password) {
        User user = wishRepository.verifyByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }


     */

    // Metode til at opdatere en ønskeliste i databasen
    public void updateWishlist(WishLists wishlists) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {
            String SQL = "UPDATE wish_list SET name=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, wishlists.getWishlistName());
            statement.setInt(2, wishlists.getId()); // Brug getId() til at få id'et for den ønskeliste, der skal opdateres
            int resultSet = statement.executeUpdate();
        }
    }

    // Metode til at hente en ønskeliste fra databasen baseret på et givet ID
    public WishLists findById(int id) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {
            String SQL = "SELECT * FROM wish_list WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                WishLists wishlists = new WishLists(""); //hør lige de andre ad
                wishlists.setId(resultSet.getInt("id")); // Bruges setId() til at sætte id'et på ønskelisten
                wishlists.setWishlistName(resultSet.getString("name")); // Bruges til at sætte navnet på ønskelisten
                return wishlists;
            }
        }
        return null; // Returner null hvis ønskelisten ikke findes i databasen
    }



}

        /*
    public void findById(Long id) {
       String query = "SELECT * FROM wish_list WHERE Wishlist_id =?";

        // Implementer logik for at finde en ønskeliste baseret på id og returnere den
        // Returner en Optional<WishList> for at håndtere tilfælde, hvor ønskelisten ikke findes
        //return WishRepository.findById(id);
    }
        */





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















