package com.example.MiniProject.Repository;
import com.example.MiniProject.Utility.ManagerDb;
import com.example.MiniProject.Utility.LoginSampleException;
import com.example.MiniProject.Model.User;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@Repository("wishlistDb")
public class DBRepository implements IRepository {
    @Override
    public User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = ManagerDb.getConnection();
            String SQL = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                User user = new User(firstName, lastName, email, password);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }
}
    
