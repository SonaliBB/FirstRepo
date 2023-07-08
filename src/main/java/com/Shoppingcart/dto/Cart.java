package com.Shoppingcart.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Cart {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private int id;
private String totalprice;

@OneToOne
private Customer customer;

@OneToMany

private List<Item> items;


public List<Item> getItems() {
	return items;
}

public void setItems(List<Item> items) {
	this.items = items;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}


public String getTotalprice() {
	return totalprice;
}

public void setTotalprice(String totalprice) {
	this.totalprice = totalprice;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}
}
