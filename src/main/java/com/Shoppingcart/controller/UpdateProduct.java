package com.Shoppingcart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.Shoppingcart.dto.Merchant;
//import com.Shoppingcart.dto.Merchant;
import com.Shoppingcart.dto.Product;

@WebServlet("/updatepro")
public class UpdateProduct  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id=req.getParameter("id");		int id1=Integer.parseInt(id);
		String name=req.getParameter("name");
		String category=req.getParameter("category");
		String prize=req.getParameter("prize");
		int prize1=Integer.parseInt(prize);
		String stock=req.getParameter("stock");
		int stock1=Integer.parseInt(stock);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();

		HttpSession hs1=req.getSession(); 
	Merchant m=(Merchant)hs1.getAttribute("merchant");
	
		Product product=new Product();
		product.setId(id1);
		product.setName(name);
		product.setCategory(category);
		product.setPrice(prize1);
		product.setStock(stock1);
//	Merchant m=(Merchant)session.getAttribute("merchant");
//	p.setMerchant(m);

	List<Product> products= new ArrayList<Product>();
products.add(product);
		//for one to many relationship
	m.setProducts(products);
//		HttpSession hs1=req.getSession();
//		Merchant m=(Merchant)hs1.getAttribute("merchant");
		//for many to one relationship
	product.setMerchant(m);
		
		et.begin();
		em.merge(product);
     	em.merge(m);
		et.commit();
		
		PrintWriter pw=resp.getWriter();
		pw.print("Product updated ");
		
	RequestDispatcher rd=req.getRequestDispatcher("selecttoupdate.jsp");
	rd.include(req, resp);
	resp.setContentType("text/html");
		
	}	
	}


