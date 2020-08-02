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

public class Customer {

	//POJO class for Bank Account
	
	Integer customerNumber;
	String customerName;
	Integer age;
	String address;
	BigDecimal openingBalanceAmmout;
	BigDecimal totalBalance;
	
	public Integer getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		address = address;
	}
	public BigDecimal getOpeningBalanceAmmout() {
		return openingBalanceAmmout;
	}
	public void setOpeningBalanceAmmout(BigDecimal openingBalanceAmmout) {
		this.openingBalanceAmmout = openingBalanceAmmout;
	}
	
}