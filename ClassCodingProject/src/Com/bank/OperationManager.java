/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.bank;

/**
 *
 * @author Sagar Das
 */



import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OperationManager {
	BigDecimal totalBalance, ammount;
	Integer accountNo;
	
	// will load total_balance from tbl_account
	String getBalance = "select total_balance from db_bank.tbl_account where account_number="+accountNo;
	
	//will insert record in table tbl_transaction
	String creditTransactionQuery = "INSERT INTO `db_bank`.`tbl_transaction` (`account_number`, `transactionDate`,`ammount`, `transaction_Type`, `balance`) VALUES (?, ?, ?, ?, ?)";
	
	//will update  the total balance after each transaction
	String updateBalance= "UPDATE `db_bank`.`tbl_account` SET `total_Balance`="+totalBalance+"? WHERE `account_number`="+accountNo;	
	
	//method fro getting JDBC connection
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");   
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/db_bank","root","");  
			//here db_bank is database name, root is username and password  
			
				con.close();  
		}catch(Exception e) {
			
		}
	}
		  
		//method to Save new record in tbl_account table in database
	public void saveCustomer(Integer acctNo, String name, Integer age, String address, BigDecimal openingBalance) {
		//
		
		try {
			// load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bank","root","");  
			
			//query to insert record in tbl_account
			String query = "INSERT INTO `db_bank`.`tbl_account` (`account_number`, `name`, `age`, `address`, `opening_balance_ammount`,`total_Balance`) VALUES (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		      
			  //load values from constructor to table columns
			  preparedStmt.setInt (1, acctNo);
		      preparedStmt.setString (2, name);
		      preparedStmt.setInt   (3, age);
		      preparedStmt.setString(4, address);
		      preparedStmt.setBigDecimal (5, openingBalance);
		      preparedStmt.setBigDecimal (6, openingBalance);

		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      //On successful record insertion
		      System.out.println("The user: \""+ name + " \"with Account No: \""+acctNo+"\" has been added successfully.!");
			
		      //as account created its default first transaction to credit opening ammount in account
		      String query2 = "INSERT INTO `db_bank`.`tbl_transaction` (`account_number`, `transactionDate`,`ammount`, `transaction_Type`, `balance`) VALUES (?,?, ?, ?, ?)";
				PreparedStatement preparedStmt2 = con.prepareStatement(query2);
				
				//load values from constructor to table columns
				preparedStmt2.setInt (1, acctNo);
				preparedStmt2.setDate(2, this.getCurrentDate());
				preparedStmt2.setBigDecimal (3, openingBalance);
				preparedStmt2.setString   (4, "CR");
				preparedStmt2.setBigDecimal (5, openingBalance);

			      // execute the preparedstatement
				preparedStmt2.execute();
			     System.out.println("Credit transaction");
			     con.close();
		      
		}catch(Exception e) {
			System.out.println(e); //handle the Exception found
		}
		
	}
	
	//To deposit transaction in in account 
	public void creditTransaction(Integer acctNo, BigDecimal ammount) {
		
		try {
			
			// load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bank","root","");  
			
			//load balance ammount before actual transaction 
			Statement stmt=con.createStatement();
			
			//query to get total_balance from tbl_account
			String getBalance = "select total_balance from db_bank.tbl_account where account_number="+acctNo;
			ResultSet rs = stmt.executeQuery( getBalance);
			BigDecimal totalBalance=null;
			while(rs.next())
				totalBalance= rs.getBigDecimal("total_balance");
			
			//will add amount deposited now in total_balance of customer
			totalBalance= totalBalance.add(ammount);
			
			// will add row in Transaction table of given account number
			PreparedStatement preparedStmt2 = con.prepareStatement(creditTransactionQuery);
			preparedStmt2.setInt (1, acctNo);
			preparedStmt2.setDate(2, this.getCurrentDate());
			preparedStmt2.setBigDecimal (3, ammount);
			preparedStmt2.setString   (4, "CR");
			preparedStmt2.setBigDecimal (5, totalBalance);
			preparedStmt2.execute();
			System.out.println("Credit transaction");
			
			//will update total amount after credit operation
			String updateBalance= "UPDATE `db_bank`.`tbl_account` SET `total_Balance`="+totalBalance+" WHERE `account_number`="+acctNo;	
			PreparedStatement preparedStmt = con.prepareStatement(updateBalance);
			System.out.println("balance updated");
			preparedStmt.execute();
		      
		}catch(Exception e) {
			System.out.println(e);	//raise the exception if  any
		}
		
	}
	
	public void withdrawTransaction(Integer acctNo, BigDecimal ammount) {
		try {

			// load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bank","root","");  
			
			//load balance ammount before actual transaction 
			Statement stmt=con.createStatement();
			
			//query to get total_balance from tbl_account
			String getBalance = "select total_balance from db_bank.tbl_account where account_number="+acctNo;
			ResultSet rs = stmt.executeQuery( getBalance);
			
			BigDecimal totalBalance=null;
			while(rs.next())
				totalBalance= rs.getBigDecimal("total_balance");
			
			//check balance is less than ammount entered by user
			int i = totalBalance.compareTo(ammount);
			if(i>=0) {
				//If less then withdraw operation executed here
				totalBalance= totalBalance.subtract(ammount);
				
				// will create record of withdraw in transaction table
				PreparedStatement preparedStmt2 = con.prepareStatement(creditTransactionQuery);
				preparedStmt2.setInt (1, acctNo);
				preparedStmt2.setDate(2, this.getCurrentDate());
				preparedStmt2.setBigDecimal (3, ammount);
				preparedStmt2.setString   (4, "DB");
				preparedStmt2.setBigDecimal (5, totalBalance);
				preparedStmt2.execute();
				System.out.println("Credit transaction");
				
				//Update balance in account after withdraw
				String updateBalance= "UPDATE `db_bank`.`tbl_account` SET `total_Balance`="+totalBalance+" WHERE `account_number`="+acctNo;	
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
	
	public void printPassBook(Integer acctNo) {
		
		try {
			// load and establish conncetion to JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bank","root","");  
			
			//select rows from both  tbl_account and tbl_transaction tables
			Statement stmt=con.createStatement();
			String getAllTransactions = "select transactionDate, ammount, transaction_Type, balance FROM db_bank.tbl_transaction where  account_number="+acctNo;
			String getAcctDetails = "select * from db_bank.tbl_account where account_number="+acctNo;
			
			ResultSet rs = stmt.executeQuery( getAcctDetails);
			
			String name="demo", address="";
			Integer age=0;
			BigDecimal openingBalance=null, totalBalance=null;
			while(rs.next()) {
				
				//load table data into variables
				name = rs.getString(2);
				address = rs.getString(4);
				age = rs.getInt(3);
				openingBalance = rs.getBigDecimal(5);
				totalBalance = rs.getBigDecimal(6);
			}
			
			//create file with User's name
			String fileName = name+".txt";
//			File fileObj = new File(fileName);
//		      if (fileObj.createNewFile()) {
//		        System.out.println("File created: " + fileObj.getName());
//		      }
		      
		      //create FileWriter object to write data in file
		      FileWriter myWriter = new FileWriter(fileName);
		      //String Buffer to load info to file
		      StringBuffer str = new StringBuffer();
		      
		      //Add acount info 
		      str.append("Account Number: ").append(acctNo).append("\n Name: ").append(name).append("\n Age").append(age).append("Address: ").append(address).
		      append("\n\n\n DATE\t").append("\tTransaction").append("\tAmmount").append("\tBalance");
		      myWriter.write(str.toString());
		      
		      
		      rs = stmt.executeQuery( getAllTransactions);
		      
		      while(rs.next()) {
		    	  StringBuffer str1 = new StringBuffer();
		    	 
		    	  //add transaction info
		    	  str1.append(rs.getDate("transactionDate")).append("\t").append(rs.getBigDecimal("ammount")).append("\t")
		    	  .append(rs.getString("transaction_Type")).append("\t").append(rs.getBigDecimal("balance"));
		    	  
		    	  myWriter.write(str1.toString());
		      }
		      //Shows msg in Screen that PassBook is printed infile
		      System.out.println("PassBook Printed Successfully...!!");
			
			
		} catch(Exception e) { //raise the exception if  any
			
		}
		
		
	}
	
	public void deleteAccount(Integer acctNo) {
		try {
			// load and establish conncetion to JDBC driver
//                        Connection connection;
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bank","root","");  
			String sqlacc = "SELECT name FROM `tbl_account` WHERE accno ='" + acctNo + "'";
         		   ResultSet rs = con.createStatement().executeQuery(sqlacc);
       			     String name = null;
     		       while (rs.next()) {
       			         name = rs.getString("name");
        		    }			

			//delete record in  tbl_transaction of given account number
			Statement stmt=con.createStatement();
			String deleteTrans = " DELETE FROM db_bank.tbl_transaction where account_number="+acctNo;
			PreparedStatement preparedStmt2 = con.prepareStatement(deleteTrans);
			System.out.println("deleted transactions");
			
			//delete record in  tbl_account of given account number
			String deleteAcct= "delete from db_bank.tbl_account where account_number`="+acctNo;	
			PreparedStatement preparedStmt = con.prepareStatement(deleteAcct);
			System.out.println("Account deleted");
			preparedStmt.execute();
			 String filename = name + ".txt";
           		 System.out.println("filename " + filename);
            			File f = new File(filename);           //file to be delete  
            		if (f.delete()) //returns Boolean value  
   		         {
              		  System.out.println(f.getName() + " deleted");   //getting and printing the file name  
           		 } else {
            			    System.out.println("failed");
        		    }
		      
			}catch(Exception e) {
			System.out.println(e);
		}
	}
	private static java.sql.Date getCurrentDate() {
		//to load currunt time in date 
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
}
