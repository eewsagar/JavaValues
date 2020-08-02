/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sagar Das
 */
package Com.bank;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	//POJO class for tbl_Trancsaction in database
	
	Integer accontNumber;
	Date transactionDate;
	String transactionType;
	BigDecimal ammount;
	BigDecimal balance;
	public Integer getAccontNumber() {
		return accontNumber;
	}
	public void setAccontNumber(Integer accontNumber) {
		this.accontNumber = accontNumber;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmmount() {
		return ammount;
	}
	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	

}
