package com.Shoppingcart.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Shoppingcart.dto.Customer;
import com.Shoppingcart.dto.Merchant;
@WebServlet("/hello12")
public class saveMerchantAndCustomer extends HttpServlet 
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
{
	//we are not reading id(made generated automatically) and status(Decided by the admin) from the user 
	String name=req.getParameter("name");
	String mobileno=req.getParameter("mobileno");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	int mobileno1=Integer.parseInt(mobileno);
	String choose=req.getParameter("choose");
	if(choose.equals("create account as merchant")){
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	Merchant merchant=new Merchant();
		merchant.setName(name);
       merchant.setMobileno(mobileno1);
	merchant.setEmail(email);
	merchant.setPassword(password);
	merchant.setStatus("inactive");
	//By default merchant status is inactive cause admin is going to add products for that admin needs to give the approval
	et.begin();
	em.persist(merchant);
	et.commit();
PrintWriter pw=resp.getWriter();	
pw.print("account created Successfully");
RequestDispatcher rd=req.getRequestDispatcher("login.html");
rd.include(req, resp);
resp.setContentType("text/html");
	}
	else{	
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Customer customer=new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setMobno(mobileno1);
		customer.setStatus("active"); 
		//customer status is active bydefault cause he gonna use the application 
		et.begin();
		em.persist(customer);
		et.commit();
		PrintWriter pw=resp.getWriter();	
		pw.print("account created Successfully");
		RequestDispatcher rd=req.getRequestDispatcher("login.html");
		rd.include(req, resp);
		resp.setContentType("text/html");
	}	
}	
}
