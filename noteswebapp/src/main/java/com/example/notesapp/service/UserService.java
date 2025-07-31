package com.example.notesapp.service;


import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.example.notesapp.DBConnection;
import com.example.notesapp.DTO.ResponseDTO;
import com.example.notesapp.util.HashPassword;

@Service
public class UserService {

    public ResponseDTO register(String userName,String password) {
        try {
            Connection con = DBConnection.connectMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM userdb WHERE username = ? LIMIT 1");
            ps.setString(1, userName);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                return new ResponseDTO(false, "Username already Exist");
            } else {
                String salt = HashPassword.generateSalt();
                String hash = HashPassword.hashPassword(password, salt);
                PreparedStatement insert = con.prepareStatement("INSERT INTO userdb VALUES (?,?,?)");
                insert.setString(1, userName);
                insert.setString(2, hash);
                insert.setString(3, salt);
                insert.executeUpdate();
                return new ResponseDTO(true, "User registered successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(false, "Failed to Register " + e.getMessage());
        }
    }

    public ResponseDTO login(String userName, String password) {
    try {
        Connection con = DBConnection.connectMySQL();

        PreparedStatement usernameStmt = con.prepareStatement("SELECT salt, password FROM userdb WHERE username = ? LIMIT 1");
        usernameStmt.setString(1, userName);
        ResultSet resultSet = usernameStmt.executeQuery();

        if (resultSet.next()) {
            String salt = resultSet.getString("salt");
            String storedPassword = resultSet.getString("password");

            String hashedInputPassword = HashPassword.hashPassword(password, salt);

            if (hashedInputPassword.equals(storedPassword)) {
                return new ResponseDTO(true, "Successfully logged in");
            } else {
                return new ResponseDTO(false, "Incorrect password");
            }
        } else {
            return new ResponseDTO(false, "Username doesn't exist");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseDTO(false, "Failed to log in: " + e.getMessage());
    }
}

}
