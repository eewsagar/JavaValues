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
import java.math.BigDecimal;
import java.util.Scanner;

public class BankAppliction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int choice;
		
		//to get choice from user
		
		do {
			System.out.println("1. Add Customer.");
			System.out.println("2. Credit Transaction.");
			System.out.println("3. Withdraw Transaction.");
			System.out.println("4. Print Passbook");
			System.out.println("5. Delete Account");
			System.out.println("6. Exit");
			
			System.out.println("Enter Your Choice");
			choice = sc.nextInt();
			
			OperationManager op = new OperationManager();
			Customer c = new Customer(); //object of pojo class customer which used as Account in database
			BigDecimal ammount;
			switch(choice){
				case 1:
						//gettinng info from customer for creating a account as he entered Choice 1
					System.out.println("Enter AcctNo: ");
					c.customerNumber = sc.nextInt(); 	//get account number
					System.out.println("Enter Name: ");
					c.customerName = sc.next();			//get name
					System.out.println("Enter Age: ");
					c.age = sc.nextInt();				//get age
					System.out.println("Enter City: ");
					c.address = sc.next();				//get city
					System.out.println("Enter OpeningBalace: ");
					c.openingBalanceAmmout = sc.nextBigDecimal();//get opening ammout
					
					//Method will save new account in account table in DB  
					op.saveCustomer(c.customerNumber, c.customerName, c.age, c.address, c.openingBalanceAmmout);//Method will save new account in account table in DB  
						break;
				case 2:
					System.out.println("Enter AcctNo: ");
					c.customerNumber = sc.nextInt();		//get acc. no
					System.out.println("Enter Ammount to credit: ");
					ammount  = sc.nextBigDecimal();			//get ammount to be credited
					
					//Method will call credit transcantion method in operationManager class 
					op.creditTransaction(c.customerNumber , ammount); 
						break;
				case 3:
					System.out.println("Enter AcctNo: ");
					c.customerNumber = sc.nextInt();		//get acc. no
					System.out.println("Enter Ammount to Withdraw: ");
					ammount  = sc.nextBigDecimal();			//get withdraw ammount
				
					//Method will call Withdrwa transcantion method in operationManager class 
					op.withdrawTransaction(c.customerNumber , ammount);
						break;
				case 4:
					System.out.println("Enter AcctNo: ");
					c.customerNumber = sc.nextInt();	//get acc. no
				//Call  printPassbook Method in in operationManager class to Print info
					op.printPassBook(c.customerNumber);
						break;
				case 5: 
					System.out.println("Enter AcctNo: ");
					c.customerNumber = sc.nextInt();	//get acc. no
					
				//Call deleteAccount Method in in operationManager class to flush info
					op.deleteAccount(c.customerNumber);
						break;
				case 6: 
					System.out.println("BYE");	//Exit message will be shown on this choice
					break;
				default:
					System.out.println("Oops.....Select correct option.");	//Deafult statement for wrong choice
					
			}
			
		} while(choice!=6);

	}

}