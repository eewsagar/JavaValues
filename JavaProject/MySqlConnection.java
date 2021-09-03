/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatraninigproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javatraninigproject.IConstantManager.*;

/**
 *
 * @author gunnnu
 */
public class MySqlConnection {

    public static Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean flag = false;

    public static Connection getConnection() throws SQLException {
        try {
            // 1
            Class.forName("com.mysql.jdbc.Driver");
            //2
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_studentdatas", "root", "");
            if (con != null || !con.isClosed()) {
                System.out.println(" Database : " + DATABASE_NAME);
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            //5
            con.close();
        }
        return con;
    }

    //3
    public static PreparedStatement preStateMent(String sql) throws SQLException, ClassNotFoundException {
        return getConnection().prepareStatement(sql);
    }

    public static void main(String[] args) throws SQLException {
        getConnection();
    }
}
