/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.bankingOperation;

/**
 *
 * @author sagarverma
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.PrintWriter;
import java.sql.*;         //this package is imported for sql connection
import java.util.*;        //this package is imported to use scanner
//this package is imported to perform file operations

public class BankOperation //declaration of jdbcex class 
{                          //curly bracket start
    //JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";          //declaration of static variable JDBC_DRIVER and value initialized to it
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_banking"; //declaration of static variable DB_URL,databse path initialized to it
    //Database Credentials
    static final String USER = "root";                                  //declaration of static variable USER and value initialized to it
    static final String PASS = "";
    static Connection connection;
    static Scanner s;
    static java.sql.Date date;

    static {      // static block
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println(" done ");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println(" connected ");
                s = new Scanner(System.in);
                Calendar calendar = Calendar.getInstance();
                date = new java.sql.Date(calendar.getTime().getTime());
                System.out.println("date: " + date);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//declaration of static variale PASS and blankvalue assigned to it

//saveDetails() function defination starts here
    public static int saveDetails(String name, int age, String address, int opbalam) throws SQLException {
        int accno = 0;
        try {
            Statement stmt = null;
            stmt = connection.createStatement();
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO `tbl_account` (`name`, `age`, `address`)  VALUES('" + name + "','"
                    + age + "','" + address + "')";
            //execute a query
            System.out.println("sql " + sql);
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the tbl_account table...");

            String sqlaccno = "Select max(accno) from tbl_account";
            ResultSet rs = connection.createStatement().executeQuery(sqlaccno);
            while (rs.next()) {
                accno = rs.getInt(1);
                System.out.println(name + " your acont number is : " + accno + "please save it for further used");

                String sql2 = "INSERT INTO `tbl_transaction` (`accno1`, `date1`, `trantype`, "
                        + "`amount`, `balance1`) "
                        + "VALUES ('" + accno + "', '" + date + "', 'deposit', '" + opbalam + "', '" + opbalam + "')";

                stmt.executeUpdate(sql2);
                System.out.println("Intialization of transaction is also done...");
            }
        } //if SQLException occurs in the above statements then this catch statement catches it and prints the exception
        catch (SQLException se) {
            System.out.println(se);
        }
        System.out.println("Account Successfully creadted");
        return accno;
    } //end of saveDetails function

//transactio function defination starts here
    public static String transaction(String accno2, int amount, String trantype) throws Exception {
        String message = "";
        try {
            System.out.println("hello");
            Statement stmt1 = null;
            String sqlbal = "SELECT balance1 FROM `tbl_transaction` WHERE accno1 ='" + accno2 + "' "
                    + "and transactionid in (SELECT max(transactionid) FROM "
                    + "`tbl_transaction` WHERE accno1 ='" + accno2 + "')";
            ResultSet rs = connection.createStatement().executeQuery(sqlbal);
            int balance1 = 0;
            while (rs.next()) {
                balance1 = rs.getInt("balance1");
            }
            System.out.println("balanace : " + balance1);
            if (trantype.equalsIgnoreCase("deposit")) {
                balance1 = balance1 + amount;
                stmt1 = connection.createStatement();
                System.out.println("Inserting records into the table...");
//                String sql2 = "INSERT INTO `tbl_transaction` VALUES('" + accno2 + "','" + date + "','" + trantype + "','" + amount + "','" + balance1 + "')";
                String sql2 = "INSERT INTO `tbl_transaction` (`accno1`, `date1`, `trantype`, "
                        + "`amount`, `balance1`) "
                        + "VALUES ('" + accno2 + "', '" + date + "', 'deposit',"
                        + " '" + amount + "', '" + balance1 + "')";

                stmt1.executeUpdate(sql2);
                message = "Inserted records into the table...";

            } else if (trantype.equalsIgnoreCase("withdrawal")) {
                if (balance1 > amount) {
                    balance1 = balance1 - amount;
                    stmt1 = connection.createStatement();
                    System.out.println("Inserting records into the table...");

                    String sql2 = "INSERT INTO `tbl_transaction` (`accno1`, `date1`, `trantype`, "
                            + "`amount`, `balance1`) "
                            + "VALUES ('" + accno2 + "', '" + date + "', 'withdrawal',"
                            + " '" + amount + "', '" + balance1 + "')";
                    stmt1.executeUpdate(sql2);
                    message = "Inserted records into the table...";
                } else {
                    message = "Not sufficient balance ... Please Check your balance first";
                }
            }

        } //handles error for JDBC
        catch (SQLException se) {
            se.printStackTrace();
        }
        return message;
    } //end of the transaction function

    public static void printData(int accnumber) throws SQLException {
        try {
            String sql5 = "select a.accno,a.name,a.age,a.address,t.date1,"
                    + "t.trantype,t.amount,t.balance1 from tbl_account a, "
                    + "tbl_transaction t where a.accno=t.accno1 and t.accno1='" + accnumber + "'";
            
            try {
                String name = "";
                PrintWriter outputfile = null;

                StringBuffer sbf1 = new StringBuffer();
                sbf1.append("\n*******************\n"
                        + "Date \t\t" + "Amount\t"
                        + "\tTransactionType \t" + "Balance\n");
                ResultSet rs = connection.createStatement().executeQuery(sql5);//here resultset is used fetch data from database
                while (rs.next()) {

                    //create StringBuffer object
                    StringBuffer sbf = new StringBuffer();
                    name = rs.getString("a.name");
                    sbf.append("\nName:" + rs.getString("a.name"));
                    sbf.append("\nAccount: " + rs.getInt("a.accno"));
                    sbf.append("\nAddress: " + rs.getString("a.address"));
                    sbf.append("\nBalance: " + rs.getInt("t.balance1"));
                    sbf.append("\nTransaction:");
                    System.out.println("print data : " + sbf);
                    sbf1.append("\n" + rs.getString("t.date1"));
                    sbf1.append("\t" + rs.getString("t.amount"));
                    sbf1.append("\t\t" + rs.getString("t.trantype"));
                    sbf1.append("\t\t\t" + rs.getInt("t.balance1"));

                    System.out.println("print data : " + sbf);
                    String filename = name+"_"+accnumber + ".txt";
                    outputfile = new PrintWriter(filename);

                    outputfile.append(sbf.toString());

                    System.out.println("Successfully wrote to the file.");
                }

                outputfile.append(sbf1.toString());
                outputfile.close();
            }//handles file operation errors
            catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
    } //end of printdata() function defination

//deleteAccount() function defination starts here
    public static String deleteAccount(int no) {
        String message="";
        try {
            Statement stmt4 = null;
            stmt4 = connection.createStatement();

            String sqlacc = "SELECT name FROM `tbl_account` WHERE accno ='" + no + "'";
            ResultSet rs = connection.createStatement().executeQuery(sqlacc);
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }

            String sql4 = "DELETE FROM `tbl_transaction` WHERE accno1='" + no + "'";
            //execute query
            stmt4.executeUpdate(sql4);
            String sql5 = "DELETE FROM `tbl_account` WHERE accno='" + no + "'";
            //execute query
            stmt4.executeUpdate(sql5);
            message =  "Account "+name+" "+no +" is deleted !!!!";
            String filename = name+"_"+no + ".txt";
            System.out.println("filename " + filename);
            File f = new File(filename);           //file to be delete  
            if(f.exists()){
            if (f.delete()) //returns Boolean value  
            {
                message = message +" "+f.getName()+"_"+  no + " file also deleted";   //getting and printing the file name  
            } else {
                message = "failed";
            }
            }else{
            message = message +" "+f.getName()+"_"+  no + " no file present."; 
            }
            

        } //handles jdbc errors
        catch (SQLException se) {
            se.printStackTrace();
        }
        return message;

    }

//printData() method definition starts here 
    public static String SearchData(int accnumber) throws SQLException {
        String name = "";
        try {
            String sql5 = "select * from tbl_account where accno ='" + accnumber + "'";
            try {

                ResultSet rs = connection.createStatement().executeQuery(sql5);//here resultset is used fetch data from database
                while (rs.next()) {

                    name = rs.getString("name");

                }
            }//handles file operation errors
            catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
        return name;
    } //end of printdata() function defination

    public static ResultSet TransactionData(int accnumber) throws SQLException {
        ResultSet rs = null;

        String sql5 = "select * from tbl_transaction where accno1 ='" + accnumber + "'";
        try {

            rs = connection.createStatement().executeQuery(sql5);//here resultset is used fetch data from database

        }//handles file operation errors
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return rs;
    } //end of printdata() function defination

//end of deleteAccount() function 
}//end of class

