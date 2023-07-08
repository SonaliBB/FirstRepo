package com.Shoppingcart.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)//user can enter wrong id also so we cannot allow user to enter id fo that use this annotation
//used to generate Automatic id (if AUTO:if i have one table having three rows and at anather table having two then first table id like 1,2,3 then for anather table count goes in sequential order
//like 4,5 thats not good practice so use as IDENTITY for each table id starts from one
private int id;
private String name;
@Column(unique = true)
private String email;
private String password;
@Column(unique = true)//cutomer cannot create multiple accounts by using same mobno and email so  
private int mobno;
private String status;

@OneToOne

private Cart cart;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getMobno() {
	return mobno;
}

public void setMobno(int mobno) {
	this.mobno = mobno;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Cart getCart() {
	return cart;
}

public void setCart(Cart cart) {
	this.cart = cart;
}


}
