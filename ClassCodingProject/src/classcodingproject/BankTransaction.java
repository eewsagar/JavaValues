/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classcodingproject;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sagar Das
 */
public class BankTransaction {
    
     private static final String PORT = "3306";   /// make 3310 for exteranl mysql
    private static final String USERNAME = "root";  /// root for both
    private static final String PASSWORD = "";  // root for external set up

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:" + PORT + "/db_aimining";

    static {
        try {
            Class.forName(DRIVER);
            System.out.println(" done ");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println(" connected ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        
    }
    
}
