/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RegisterController {

    // Câu truy vấn SQL để kiểm tra người dùng tồn tại
    private final String checkUser = "Select * from UserLogin where email = ?";
    // Câu truy vấn SQL để thêm người dùng mới
    private final String Insert_User = "Insert into UserLogin (userName, passWord, email) values (?,?,?)";

    public void register(String name, String email, String passWord) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        
        String url = "jdbc:sqlserver://LAPTOP-QAOB3NRI\\SQLEXPRESS:1433;databaseName=ChatApplication;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "123456789";
        
        try {
            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(url, username, password);
            
            // Kiểm tra người dùng đã tồn tại hay chưa
            p = connection.prepareStatement(checkUser);
            p.setString(1, email);
            rs = p.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "User already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Mã hóa mật khẩu
            SecurityKeyPairGenerator skpg = new SecurityKeyPairGenerator();
            PublicKey publicKey = skpg.getPublicKey("D:\\AdvancedJAVA\\ChatApplication\\Key\\PublicKey\\publicKey.rsa");
            String encryptedPass = skpg.encrypt(passWord, publicKey);
            
            // Thêm người dùng mới vào cơ sở dữ liệu
            p = connection.prepareStatement(Insert_User);
            p.setString(1, name);
            p.setString(2, encryptedPass);
            p.setString(3, email);
            p.execute();
            
            JOptionPane.showMessageDialog(null, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            // Đóng các tài nguyên
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

