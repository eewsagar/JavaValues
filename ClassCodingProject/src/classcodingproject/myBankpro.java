/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classcodingproject;

/**
 *
 * @author Sagar Das
 */

//********all required packages*******
import java.math.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;

              //in this program two tables are created tbl_transaction and tbl_account


                       //************************start of main class***************************
public class myBankpro  
             {
            public static void main(String[] args)          //start of main method  
                  {
		Scanner sc = new Scanner(System.in);
		int choice;       //to get choice from user
		do                            //do-while loop start
                      {
			System.out.println("1. Add New Account Holder");
			System.out.println("2. Credit Transaction.");
			System.out.println("3. Withdraw Transaction.");
			System.out.println("4. Print Passbook");
			System.out.println("5. Delete Account");
			System.out.println("6. Exit");
		         System.out.println("Enter Your Choice ");
		       choice = sc.nextInt();
			manageacc op = new manageacc();
			account c = new account();          //object of pojo class customer which used as Account in database
			Integer ammount;
			switch(choice){
				case 1:                //gettinng info from customer for creating a account as he entered Choice 1
				        System.out.println("Enter Name: ");
					c.name = sc.next();			        //get name
					System.out.println("Enter Age: ");
					c.age = sc.nextInt();				//get age
					System.out.println("Enter City: ");
					c.add= sc.next();				//get city
					System.out.println("Enter OpeningBalace: ");
				         c.oBal = sc.nextInt();//get opening ammout
			               op.savedetails( c.name, c.age, c.add, c.oBal);//Method will save new account in account table in DB  
				break;
			       case 2:
					System.out.println("Enter AcctNo: ");
					c.accno = sc.nextInt();	                 	//get acc. no
					System.out.println("Enter Ammount to credit: ");
					ammount  = sc.nextInt();			//get ammount to be credited
					    //Method will call credit transcantion method in operationManager class 
					op.depositTransaction(c.accno , ammount); 
				break;
				case 3:
					System.out.println("Enter AcctNo: ");
					c.accno = sc.nextInt();		                 //get acc. no
					System.out.println("Enter Ammount to Withdraw: ");
					ammount  = sc.nextInt();			//get withdraw ammount
				              //Method will call Withdrwa transcantion method in operationManager class 
					op.withdrawTransaction(c.accno , ammount);
				break;
				case 4:
					System.out.println("Enter AcctNo: ");
					c.accno = sc.nextInt();	//get acc. no
				                    //Call  printPassbook Method in in operationManager class to Print info
			                op.printPassBook(c.accno);
				break;
				case 5: 
					System.out.println("Enter AcctNo: ");
					c.accno = sc.nextInt();	//get acc. no
				                   //Call deleteAccount Method in in operationManager class to flush info
					op.deleteAccount(c.accno);
				break;
				case 6: 
					System.out.println("EXIT");	           //Exit message will be shown on this choice
					break;
				default:
					System.out.println("Oops.....Select correct option.");	//Deafult statement for wrong choice
					
			}
			
		} while(choice!=6);              //end of do-while loop

	}      //end of main method

}               //****************** end of main class *************************
            
            //********class account for tbl_account********
 class account {

	String name;
	Integer age;
        Integer accno;
	String add;
	Integer oBal;
	Integer tBal;
	
public Integer getAccno()
{
return accno;
}
public String getName() 
{
return name;
}
public void setName(String name) 
{
this.name = name;
}
public Integer getAge() 
{
return age;
}
public void setAge(Integer age)
{
this.age = age;
}
public String getAdd() 
{
return add;
}
public void setAdd(String add)
{
add = add;
}
public Integer getOBal()
{
return oBal;
}
public void setOBal(Integer oBal)
{
this.oBal = oBal;
}
}              //******************end of account class****************


            //********************class for transaction****************** 
 class Transaction 
{
        java.sql.Date transactionDate;
	String transactionType;
	Integer ammount;
	Integer balance;

public java.sql.Date getTransactionDate() 
{
return transactionDate;
}
public void  setTransactionDate(java.sql.Date transactionDate) 
{
this.transactionDate = transactionDate;
}
public String getTransactionType() 
{
return transactionType;
}
public void setTransactionType(String transactionType) 
{
this.transactionType = transactionType;
}
public Integer getAmmount() 
{
return ammount;
}
public void setAmmount(Integer ammount) 
{
this.ammount = ammount;
}
public Integer getBalance() 
{
return balance;
}
public void setBalance(Integer balance) 
{
this.balance = balance;
}
}                                 //******************end of transaction class*******************


                     //************************* start of manage account activities class*******************
class manageacc 
{
	Integer totalBalance, ammount;
	Integer accountNo;
	String getBalance = "select total_balance from db_bank.tbl_account where account_number="+accountNo;                 // will load total_balance from tbl_account
	String creditTransactionQuery = "INSERT INTO `db_bank`.`tbl_transaction` (`accountnumber`, `transaction Date`,`ammount`, `transaction Type`, `balance`) VALUES (?, ?, ?, ?, ?)";    //will insert record in table tbl_transaction
	String updateBalance= "UPDATE `db_bank`.`tbl_account` SET `total_Balance`="+totalBalance+"? WHERE `account number`="+accountNo;	    //will update  the total balance after each transaction
	           static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";          //declaration of static variable JDBC_DRIVER and value initialized to it
                   static final String DB_URL = "jdbc:mysql://localhost:3306/db_bank"; //declaration of static variable DB_URL,databse path initialized to it
                                       //Database Credentials
                         static final String USER = "root";                                  //declaration of static variable USER and value initialized to it
                          static final String PASS = "";        
	                        //********************method fro getting JDBC connection*********************
	public void createConnection() 
                      {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
                         System.out.println("Connecting to database..."); 
			Connection con=null;
                        con=DriverManager.getConnection(  DB_URL,USER,PASS);      //here db_bank is database name, root is username and password  
			con.close();  
		    }catch(Exception e) {   }
	              }      
		  
		                 //***************method to Save new record in tbl_account table in database********
	public void savedetails( String name, Integer age, String add, Integer oBal)
                       {
		try {
			Class.forName("com.mysql.jdbc.Driver");                                                          // load and establish conncetion to JDBC driver
			Connection con=DriverManager.getConnection(DB_URL,USER,PASS);  
                        String query = "INSERT INTO `db_bank`.`tbl_account` (`accountnumber`, `name`, `age`, `address`, `total_Balance`) VALUES (NULL, ?, ?, ?,?)";    //query to insert record in tbl_account
                        PreparedStatement preparedStmt = con.prepareStatement(query);
		               //load values from constructor to table columns
		      preparedStmt.setString (2, name);
		      preparedStmt.setInt   (3, age);
		      preparedStmt.setString(4, add);
		      preparedStmt.setInt (5, oBal);
		      preparedStmt.execute();                        // execute the preparedstatement
		      System.out.println("The user has been added successfully.!");
                                                                    //as account created its default first transaction to credit opening ammount in account
		      String query2 = "INSERT INTO `db_bank`.`tbl_transaction` (`accountnumber`, `transaction date`,`ammount`, `transaction type`, `balance`) VALUES (NULL,?, ?, ?, ?)";
			PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				//load values from constructor to table columns
				preparedStmt2.setDate(2, this.getCurrentDate());
				preparedStmt2.setInt (3, oBal);
				preparedStmt2.setString   (4, "creadit transaction");
				preparedStmt2.setInt (5, oBal);
                                preparedStmt2.execute();          // execute the preparedstatement
			     System.out.println("Credit transaction");
			     con.close();
		    }catch(Exception e) {
			System.out.println(e); //handle the Exception found
                       }
                     }
	
	                        //******************method To deposit transaction in in account ****************
	public void depositTransaction(Integer accno, Integer ammount) 
                       {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");             // load and establish conncetion to JDBC driver
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/db_bank","root"," ");  
                         Statement stmt=con.createStatement();                 //load balance ammount before actual transaction 
			String getBalance = "select total_balance from db_bank.tbl_account where accountnumber="+accno;   //query to get total_balance from tbl_account
			ResultSet rs = stmt.executeQuery( getBalance);
			Integer tBal=null;
			while(rs.next())
				tBal= rs.getInt("total_balance");
		         tBal= tBal+ammount;
			PreparedStatement preparedStmt2 = con.prepareStatement(creditTransactionQuery);
			preparedStmt2.setInt (1, accno);
			preparedStmt2.setDate(2, this.getCurrentDate());
			preparedStmt2.setInt (3, ammount);
			preparedStmt2.setString   (4, "cash");
			preparedStmt2.setInt (5, tBal);
			preparedStmt2.execute();
			System.out.println("Credit transaction");
			String updateBalance= "UPDATE `db_bank`.`tbl_account` SET `total_Balance`="+tBal+" WHERE `accountnumber`="+accno;	
			PreparedStatement preparedStmt = con.prepareStatement(updateBalance);
			System.out.println("balance updated");
			preparedStmt.execute();
		      }catch(Exception e) {
			System.out.println(e);	//raise the exception if  any
                                          }
                       }
	                     //******************method to withdraw transaction in account****************
	public void withdrawTransaction(Integer accno, Integer ammount)
                             {
		try {

			                      // load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/db_bank","root"," ");  
			                    //load balance ammount before actual transaction 
			Statement stmt=con.createStatement();
			                   //query to get total_balance from tbl_account
			String getBalance = "select total_balance from db_bank.tbl_account where accountnumber="+accno;
			ResultSet rs = stmt.executeQuery( getBalance);
			Integer tBal=null;
			while(rs.next())
		         tBal= rs.getInt("total_balance");
			                 //check balance is less than ammount entered by user
			int i = tBal.compareTo(ammount);
			if(i>=0) {
				        //If less then withdraw operation executed here
				tBal= tBal-ammount;
				        // will create record of withdraw in transaction table
				PreparedStatement preparedStmt2 = con.prepareStatement(creditTransactionQuery);
				preparedStmt2.setInt (1, accno);
				preparedStmt2.setDate(2, this.getCurrentDate());
				preparedStmt2.setInt (3, ammount);
				preparedStmt2.setString   (4, "cash");
				preparedStmt2.setInt(5, tBal);
				preparedStmt2.execute();
				System.out.println("Credit transaction");
				          //Update balance in account after withdraw
				String updateBalance= "UPDATE `db_bank`.`tbl_account` SET `total_Balance`="+tBal+" WHERE `accountnumber`="+accno;	
				PreparedStatement preparedStmt = con.prepareStatement(updateBalance);
				          //print total balance
				System.out.println("balance updated");
				preparedStmt.execute();
			}else {
				          //if ammount is more the balance in account then give Error of low balance
				System.out.println("LOW AMMOUNT");
                               }
			}catch(Exception e) {
			System.out.println(e); //raise the exception if  any
		                             }
	                          }
	                              //*******************method to print details of account holder*********************
	public void printPassBook(Integer accno)
                         {
	         try {
			                   // load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/db_bank","root"," ");  
			                   //select rows from both  tbl_account and tbl_transaction tables
			Statement stmt=con.createStatement();
			String getAllTransactions = "select transaction date, ammount, transaction type, balance FROM db_bank.tbl_transaction where  accountnumber="+accno;
			String getAcctDetails = "select * from db_bank.tbl_account where accountnumber="+accno;
			ResultSet rs = stmt.executeQuery( getAcctDetails);
			String name="nivi", add="";
			Integer age=0;
			Integer oBal=null, tBal=null;
			while(rs.next()) {
				//load table data into variables
				name = rs.getString(2);
				add= rs.getString(4);
				age = rs.getInt(3);
				oBal = rs.getInt(5);
				tBal = rs.getInt(6);
			}
			//create file with User's name
			String fileName = name+".txt";
			File fileObj = new File(fileName);
		      if (fileObj.createNewFile()) {
		        System.out.println("File created: " + fileObj.getName());
		      }
		      //create FileWriter object to write data in file
		      FileWriter myWriter = new FileWriter(fileName);
		      //String Buffer to load info to file
		      StringBuffer str = new StringBuffer();
		       //Add acount info 
		      str.append("Account Number: ").append(accno).append("\n Name: ").append(name).append("\n Age").append(age).append("Address: ").append(add).append("\n\n\n DATE\t").append("\tTransaction").append("\tAmmount").append("\tBalance");
		      myWriter.write(str.toString());
		       rs = stmt.executeQuery( getAllTransactions);
		      while(rs.next()) {
		    	  StringBuffer str1 = new StringBuffer();
		    	            //add transaction info
		    	  str1.append(rs.getDate("transaction date")).append("\t").append(rs.getInt("ammount")).append("\t")
		    	  .append(rs.getString("transaction type")).append("\t").append(rs.getInt("balance"));
		    	   myWriter.write(str1.toString());
                            }
		                                       //Shows msg in Screen that PassBook is printed infile
		      System.out.println("PassBook Printed Successfully...!!");
			} catch(Exception e) { //raise the exception if  any
			                     }
                         }
	                      //****************method to flush account****************
	public void deleteAccount(Integer accno) 
                          {
		try {
			                      // load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/db_bank","root"," ");  
			                    //delete record in  tbl_transaction of given account number
			Statement stmt=con.createStatement();
			String deleteTrans = " DELETE FROM db_bank.tbl_transaction where accountnumber="+accno;
			PreparedStatement preparedStmt2 = con.prepareStatement(deleteTrans);
			System.out.println("deleted transactions");
                                            //delete record in  tbl_account of given account number
			String deleteAcct= "delete from db_bank.tbl_account where accountnumber`="+accno;	
			PreparedStatement preparedStmt = con.prepareStatement(deleteAcct);
			System.out.println("Account deleted");
			preparedStmt.execute();
			}catch(Exception e) {
			System.out.println(e);
                 }
	          }
                       //*****************method to return current date*****************
	private static java.sql.Date getCurrentDate() {
		//to load currunt time in date 
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
}                       //*********************** end of manage account activties class ************************
                            

