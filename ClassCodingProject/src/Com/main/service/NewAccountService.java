/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.main.service;

import Com.main.controller.SingoltonConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Sagar Das
 */
public class NewAccountService {
     static java.sql.Date date;
        public static int saveDetails(String name, int age, String address,int balance) throws SQLException, ClassNotFoundException {
            int accno = 0;
            try {
            //scan acc_no entered by user and strore it in accno variable
//            System.out.println("Enter your name");                             //prints the double quoted text on output console screen
//            String name = s.next();                                          //scan name entered by user and store it in name variable
//            System.out.println("Enter your age");                              //prints the double quoted text on output console screen
//            int age = s.nextInt();                                               //scan age entered by user and store it in age variable
//            System.out.println("Enter your address");                          //prints the double quoted text on output console screen
//            String address = s.next();                                       //scan address entered by user and store it in address variable
//            System.out.println("Enter opening balance amount");                //prints the double quoted text on output console screen
//            int opbalam = s.nextInt();

            Statement stmt = null;
           
//            stmt = new MsAccessConnection().connection.createStatement();
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO `tbl_account` (`name`, `age`, `address`)  VALUES('" + name + "','"
                    + age + "','" + address + "')";
            //execute a query
            System.out.println("sql " + sql);
            SingoltonConnection.makePreparedStatement(sql).execute();
//            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the tbl_account table...");
               Calendar calendar = Calendar.getInstance();
                date = new java.sql.Date(calendar.getTime().getTime());
                System.out.println("date: " + date);
                
            String sqlaccno = "Select max(accno) from tbl_account";
            ResultSet rs = SingoltonConnection.makePreparedStatement(sqlaccno).executeQuery();

            while (rs.next()) {
                 accno = rs.getInt(1);
                System.out.println(name + " your acont number is : " + accno + "please save it for further used");

                String sql2 = "INSERT INTO `tbl_transaction` (`accno1`, `date1`, `trantype`, "
                        + "`amount`, `balance1`) "
                        + "VALUES ('" + accno + "', '" + date + "', 'deposit', '" + balance + "', '" + balance + "')";

                SingoltonConnection.makePreparedStatement(sql2).execute();
//                stmt.executeUpdate(sql2);
                System.out.println("Intialization of transaction is also done...");
            }
        } //if SQLException occurs in the above statements then this catch statement catches it and prints the exception
        catch (SQLException se) {
            System.out.println(se);
        }
        System.out.println("Account Successfully creadted");
         return accno;
    } //end of saveDetails function

}
