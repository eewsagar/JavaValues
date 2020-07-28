//package com.main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

public class MsAccessConnection {

    //// line top be modified
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

    public static PreparedStatement preStateMent(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (Exception e) {
            System.out.println("PreparedStatement preStateMent(String query) line number ");
        }
        return null;
    }

    public static String getDate() {
        String[] split = new Date().toString().split(" ");
        String date = "" + split[2] + "-" + split[1] + "-" + split[5];
        System.out.println("date = " + date);
        return date;
    }

    public static void main(String[] args) {
    }

    public static Integer chkUserName(String columnname, String tblName, String value) {
        String sql_query = "SELECT * FROM `" + tblName + "` WHERE `" + columnname + "` = '" + value + "'";
        System.out.println("sql_query = " + sql_query);
        try {
            ResultSet executeQuery = preStateMent(sql_query).executeQuery();
            while (executeQuery.next()) {
                return 1;
            }
            executeQuery.close();;
            return 0;
        } catch (Exception e) {
        }
        return 0;
    }
}
