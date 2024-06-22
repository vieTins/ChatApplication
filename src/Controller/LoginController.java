/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FPTSHOP
 */
public class LoginController {

    private final String getUser = "select userName , passWord from UserLogin where email=?";

    public boolean loginUser(String name, String passWord, String email) throws SQLException, Exception {
        Connection connection = null;
        Statement statement = null;
        String url = "jdbc:sqlserver://LAPTOP-QAOB3NRI\\SQLEXPRESS:1433;databaseName=ChatApplication;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "123456789";
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement p = connection.prepareStatement(getUser);
        p.setString(1, email);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            try {
                String nameUser = rs.getString("userName");
                String passWordUser = rs.getString("passWord");
//                giải mã mật khẩu 
                SecurityKeyPairGenerator skpg = new SecurityKeyPairGenerator();
                PrivateKey privateKey = skpg.getPrivateKey("D:\\AdvancedJAVA\\ChatApplication\\Key\\PrivateKey\\privateKey.rsa");
                String decrpytPass = skpg.decrypt(passWordUser, privateKey);
                if (passWord.equalsIgnoreCase(decrpytPass) && name.equalsIgnoreCase(nameUser)) {
                    return true ;
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            return false ;
    }
}
