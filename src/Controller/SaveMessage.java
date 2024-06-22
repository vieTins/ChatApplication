/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author FPTSHOP
 */
public class SaveMessage {
    private final String save = "Insert into SaveMessage (email , userName , content) values (?,?,?)" ;
    public void saveMessage (String email , String userName , String content) {
        Connection connection = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        String url = "jdbc:sqlserver://LAPTOP-QAOB3NRI\\SQLEXPRESS:1433;databaseName=ChatApplication;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "123456789";
        try {
            connection = DriverManager.getConnection(url, username, password);
            p = connection.prepareStatement(save);
            p.setString(1, email);
            p.setString(2, userName);
            p.setString(3, content);
            p.execute() ;
            System.out.println("Save Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
