package com.example.MiniProject.Repository;

import com.example.MiniProject.DTO.ItemFormDTO;
import com.example.MiniProject.DTO.UserFormDTO;
import com.example.MiniProject.DTO.WishlistFormDTO;
import com.example.MiniProject.Model.User;
import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Utility.LoginSampleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class WishRepository {

    @Value("$spring.datasource.url")
    private String databaseUserUrl;

    @Value("$spring.datasource.username")
    private String databaseUserUsername;

    @Value("$spring.datasource.password")
    private String databaseUserPassword;

    public User createUser(UserFormDTO userDTO) throws LoginSampleException {
        //Creates a database connection in Java by specifying the URL, username, and password.
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {
            //SQL query used to insert specified data into the database.

            String SQL = "INSERT INTO miniprojekt.user(firstname, lastname, email, password) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDTO.getFirstName());
            preparedStatement.setString(2, userDTO.getLastName());
            preparedStatement.setString(3, userDTO.getEmail());
            preparedStatement.setString(4, userDTO.getPassword());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            long id = resultSet.getLong(1);

            User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getPassword());

            user.setId(id);

            return user;

        } catch (SQLException e) {

            throw new LoginSampleException(e.getMessage());
        }
    }

    public User verifyUser(String email, String password) throws LoginSampleException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde")) {
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        user.setFirstName(resultSet.getString("firstname"));
                        user.setLastName(resultSet.getString("lastname"));
                        return user;
                    } else {
                        throw new LoginSampleException("Could not validate user");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error verifying user", e);
        }
    }

    // Method to create a new wishlist in the database
    public int createWishList(String wishListName, String userEmail) throws SQLException {
        int id = -1;
        String sql = "INSERT INTO wishlists(wishListName, userEmail) VALUES(?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniProjekt", "root", "SabrinaMathilde");
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, wishListName);
            preparedStatement.setString(2, userEmail);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating wish list: " + e.getMessage());
            throw e;
        }
        return id;
    }




    // Metode til at opdatere en ønskeliste i databasen
    public void updateWishlist(WishLists wishlists) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseUserUrl, databaseUserUsername, databaseUserPassword)) {
            String SQL = "UPDATE wish_list SET name=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, wishlists.getWishListName());
            statement.setInt(2, wishlists.getId()); // Brug getId() til at få id'et for den ønskeliste, der skal opdateres
            int resultSet = statement.executeUpdate();
        }
    }

    // Metode til at hente en ønskeliste fra databasen baseret på et givet ID

    /*
    public WishLists findById(int id) throws SQLException {
        WishLists wishLists = null;
        try (Connection connection = DriverManager.getConnection(databaseUserUrl, databaseUserUsername, databaseUserPassword)) {
            String SQL = "SELECT * FROM wish_list WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String wishListName = resultSet.getString("wishListName");
                String userEmail = resultSet.getString("userEmail");
                wishLists = new WishLists(id,wishListName,userEmail);
                System.out.println(id);
            }
        }
        return wishLists;
    }

/*
     */
    public int editWishlist(int id, WishLists wishLists) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseUserUrl, databaseUserUsername, databaseUserPassword)) {

            //SQL query used to insert specified data into the database.
            String SQL = "UPDATE wish_list set" + " Name = \"" + wishLists.getWishListName() + "\" WHERE wishlist_id = \"" + id + "\"";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.execute();

            return preparedStatement.getUpdateCount();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int deleteWishlist(int id, WishLists wishLists) {
        try (Connection connection = DriverManager.getConnection(databaseUserUrl, databaseUserUsername, databaseUserPassword)) {

            String SQL = "DELETE FROM wish_list" + "Name = \"" + wishLists.getWishListName() + "\" WHERE wishlist_id = \"" + id + "\"";
            ;

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.execute();

            return preparedStatement.getUpdateCount();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createWish(ItemFormDTO itemFormDTO) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseUserUrl, databaseUserUsername, databaseUserPassword)) {

            String SQL = "INSERT INTO items " + "(name_of_item, description_of_item, price, quantity, url)" +
                    "VALUES (\"" + itemFormDTO.getItemName() + "\",\"" + itemFormDTO.getItemDescription() + "\",\"" +
                    itemFormDTO.getItemPrice() + "\",\"" + itemFormDTO.getItemQuantity() + "\",\"" + itemFormDTO.getItemUrl() + "\",\"" + "\")";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            int generatedKey = 0;

            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }

            return generatedKey;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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















