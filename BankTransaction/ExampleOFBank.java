/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sagar Das
 */
import java.io.File;
import java.io.PrintWriter;
import java.sql.*;         //this package is imported for sql connection
import java.util.*;        //this package is imported to use scanner
//this package is imported to perform file operations

public class ExampleOFBank //declaration of jdbcex class 
{                          //curly bracket start
    //JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";          //declaration of static variable JDBC_DRIVER and value initialized to it
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_bank"; //declaration of static variable DB_URL,databse path initialized to it
    //Database Credentials
    static final String USER = "root";                                  //declaration of static variable USER and value initialized to it
    static final String PASS = "";
    static Connection connection;
    static Scanner s;
    static java.sql.Date date;

    static {
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

    public static void main(String[] args) //declaration of main method,execution starts from here
    {
        //In this program two tables are created tbl_transaction and tbl_account
        //tbl_transaction(acc_no,transaction_date,ammount,transaction_type,balance)
        //tbl_account(acc_no,name,age,address,opening_balance_ammount)
        try {
             String s1=null;
            do
            {
            System.out.println("**************************BANK MANAGEMENT SYSTEM******************************");
            System.out.println("1.create account.");
            System.out.println("2.Transaction.");
            System.out.println("3.printData");
            System.out.println("4.Delete Account");
            //initializing scanner to accept values
            System.out.println("Enter your choice");
            int choice = s.nextInt();
            
            switch (choice) {
                case 1:                                       //scan open_bal_amount entered by user and store it in opbalam variable
                    saveDetails();                       //calling saveDetails()function to insert the values in tbl_account
                    break;
                case 2:
                    transaction();                  //calling transaction() function to insert values and to perform transactions in tbl_transaction      
                    break;
                case 3:
                    printData();
                    break;
                case 4:
                    deleteAccount();
                    break;
                default:
                    //prints the double quoted text on output console screen
                    System.out.println("Goodbye!");
                    break;
            }
            System.out.println("Do you want to Cont...(Y/N)?");
             s1 = s.next();
        }while(s1.equals("y"));
            
        }catch (Exception e) {
            System.out.println(e);
        }finally{
            
        System.exit(0);
        }

    }   //end of the main() function

//saveDetails() function defination starts here
    public static void saveDetails() throws SQLException {
        try {
            //scan acc_no entered by user and strore it in accno variable
            System.out.println("Enter your name");                             //prints the double quoted text on output console screen
            String name = s.next();                                          //scan name entered by user and store it in name variable
            System.out.println("Enter your age");                              //prints the double quoted text on output console screen
            int age = s.nextInt();                                               //scan age entered by user and store it in age variable
            System.out.println("Enter your address");                          //prints the double quoted text on output console screen
            String address = s.next();                                       //scan address entered by user and store it in address variable
            System.out.println("Enter opening balance amount");                //prints the double quoted text on output console screen
            int opbalam = s.nextInt();

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
                int accno = rs.getInt(1);
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
    } //end of saveDetails function

//transactio function defination starts here
    public static void transaction() throws Exception {
        try {
            System.out.println("Enter account no");                            //prints the double quoted text on output console screen
            int accno2 = s.nextInt(), balance1 = 0;
            System.out.println("date : " + date);
            System.out.println("Enter amount");                                //prints the double quoted text on output console screen
            int amount = s.nextInt();                                            //scan amount entered by user and store it in amount variable
            System.out.println("Enter transaction type");                      //prints the double quoted text on output console screen
            String trantype = s.next();
            Statement stmt1 = null;
            String sqlbal = "SELECT balance1 FROM `tbl_transaction` WHERE accno1 ='" + accno2 + "' "
                    + "and transactionid in (SELECT max(transactionid) FROM "
                    + "`tbl_transaction` WHERE accno1 ='" + accno2 + "')";
            ResultSet rs = connection.createStatement().executeQuery(sqlbal);

            while (rs.next()) {
                balance1 = rs.getInt("balance1");
            }
            if (trantype.equals("deposit")) {
                balance1 = balance1 + amount;
                stmt1 = connection.createStatement();
                System.out.println("Inserting records into the table...");
//                String sql2 = "INSERT INTO `tbl_transaction` VALUES('" + accno2 + "','" + date + "','" + trantype + "','" + amount + "','" + balance1 + "')";
                String sql2 = "INSERT INTO `tbl_transaction` (`accno1`, `date1`, `trantype`, "
                        + "`amount`, `balance1`) "
                        + "VALUES ('" + accno2 + "', '" + date + "', 'deposit',"
                        + " '" + amount + "', '" + balance1 + "')";

                stmt1.executeUpdate(sql2);
                System.out.println("Inserted records into the table...");

            } else if (trantype.equals("withdrawal")) {
                if (balance1 > amount) {
                    balance1 = balance1 - amount;
                    stmt1 = connection.createStatement();
                    System.out.println("Inserting records into the table...");

                    String sql2 = "INSERT INTO `tbl_transaction` (`accno1`, `date1`, `trantype`, "
                            + "`amount`, `balance1`) "
                            + "VALUES ('" + accno2 + "', '" + date + "', 'withdrawal',"
                            + " '" + amount + "', '" + balance1 + "')";
                    stmt1.executeUpdate(sql2);
                    System.out.println("Inserted records into the table...");
                } else {
                    System.out.println("Not sufficient balance ... Please Check your balance first");
                }
            }

        } //handles error for JDBC
        catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("Transaction Successfully done!");
    } //end of the transaction function

//printData() method definition starts here 
    public static void printData() throws SQLException {
        try {
            System.out.println("Please enter the account number for which you wann print passbook : \n");
            int accnumber = s.nextInt();
            String sql5 = "select a.accno,a.name,a.age,a.address,t.date1,"
                    + "t.trantype,t.amount,t.balance1 from tbl_account a, "
                    + "tbl_transaction t where a.accno=t.accno1 and t.accno1='"+accnumber+"'";
            System.out.println("sql5 : " + sql5);
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
                    String filename = name + ".txt";
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
    public static void deleteAccount() {
        try {
            Statement stmt4 = null;
            stmt4 = connection.createStatement();
            Scanner sn = new Scanner(System.in);
            System.out.println("Do you really want to delete account? if yes enter account_no");
            int no = sn.nextInt();

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
            String filename = name + ".txt";
            System.out.println("filename " + filename);
            File f = new File(filename);           //file to be delete  
            if (f.delete()) //returns Boolean value  
            {
                System.out.println(f.getName() + " deleted");   //getting and printing the file name  
            } else {
                System.out.println("failed");
            }
            System.out.println("Account is beign deleted ...!");

        } //handles jdbc errors
        catch (SQLException se) {
            se.printStackTrace();
        }

    }
//end of deleteAccount() function 
}//end of class

