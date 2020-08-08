package com.service;



/**
 *
 * @author Sagar Das
 */
import java.io.File;
import java.io.PrintWriter;
import java.sql.*;         //this package is imported for sql connection
import java.util.*;        //this package is imported to use scanner


public class AddData //declaration of jdbcex class 
{                          //curly bracket start
    
    public  void saveDetails(String name) throws SQLException, ClassNotFoundException {
        try {
            
        	
             final String JDBC_DRIVER = "com.mysql.jdbc.Driver";         
             final String DB_URL = "jdbc:mysql://localhost:3306/db_examples"; 
            //Database Credentials
             final String USER = "root";                                  
             final String PASS = "";
             Connection connection =null;;
           

                    Class.forName(JDBC_DRIVER); // you have to define driver class name
                    System.out.println(" done ");
                    if (connection == null || connection.isClosed()) {
                        connection=DriverManager.getConnection(DB_URL, USER, PASS);
//                        connection = DriverManager.getConnection(DB_URL, USER, PASS); // get the connection
                        System.out.println(" connected ");
                    }

            Statement stmt = null;
            stmt = connection.createStatement();
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO `tbl_contact` (`fullName`, `isActive`)  VALUES('" + name + "','Y')";
            //execute a query
            System.out.println("sql " + sql);
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the tbl_account table...");

            
            
        } //if SQLException occurs in the above statements then this catch statement catches it and prints the exception
        catch (SQLException se) {
            System.out.println(se);
        }
        System.out.println("Account Successfully creadted");
    


}//end of class
}

