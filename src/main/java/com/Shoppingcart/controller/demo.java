package com.Shoppingcart.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
 @WebServlet("/hello")
public class demo  extends HttpServlet {
 public static void main(String[] args) {
	
	 EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	
	System.out.println("hello");
}
}
