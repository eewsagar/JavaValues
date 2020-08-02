package Com.main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingoltonConnection implements IConstantManager {

    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DATABASE_DRIVER);
        System.out.println("CONNECTED" );
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            System.out.println("DATABASE_NAME = " + DATABASE_NAME);
        }
        return connection;
    }

    public static PreparedStatement makePreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        return getConnection().prepareStatement(sql);
    }

    public static void deleteOrInactiveRecord(String subqury) {
        try {
            System.out.println("DELETE FROM " + subqury);
            makePreparedStatement("DELETE FROM " + subqury).execute();
            makePreparedStatement("DELETE FROM " + subqury).execute();
        } catch (Exception e) {
        }
    }

    public static Integer getPrimaryKe(String tableName, String pkColumnName) throws SQLException, ClassNotFoundException {
        String sql_pk = "SELECT " + pkColumnName + " FROM " + tableName;
        ResultSet executeQuery = makePreparedStatement(sql_pk).executeQuery();
        System.out.println("sql_pk = " + sql_pk);
        Integer primaryKey = null;
        while (executeQuery.next()) {
            primaryKey = executeQuery.getInt(pkColumnName);
        }
        if (primaryKey == null) {
            return 1;
        } else {
            return primaryKey + 1;
        }
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (Exception e) {
        }
    }
}
