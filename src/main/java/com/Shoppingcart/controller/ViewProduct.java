package com.Shoppingcart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Shoppingcart.dto.Merchant;
import com.Shoppingcart.dto.Product;

public class ViewProduct extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	String id=req.getParameter("id");
	int id1=Integer.parseInt(id);
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	HttpSession hs1=req.getSession(); 
	Merchant m=(Merchant)hs1.getAttribute("merchant");
	
	Product product=new Product();
	product.setId(id1);
//	product.setName(name);
//	product.setCategory(category);
//	product.setPrice(prize1);
//	product.setStock(stock1);
//	Merchant m=(Merchant)session.getAttribute("merchant");
//	p.setMerchant(m);
	
	List<Product> products= new ArrayList<Product>();
	products.add(product);
	//for one to many relationship
	m.setProducts(products);
//	HttpSession hs1=req.getSession();
//	Merchant m=(Merchant)hs1.getAttribute("merchant");
	//for many to one relationship
	product.setMerchant(m);
	
	et.begin();
	em.merge(product);
	em.merge(m);
	et.commit();
}
}
