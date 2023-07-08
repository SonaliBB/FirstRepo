package com.Shoppingcart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deletepro")
public class DeleteProduct extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		 String id=req.getParameter("id");
		 int id1=Integer.parseInt("id");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Query q=em.createQuery("delete from Product p where id=?1");
		
		q.setParameter(1,id1);
		et.begin();
		em.persist(q);
		et.commit();
		
//		PrintWriter pw=resp.getWriter();
//		pw.print("product deleted successfully");
		
		RequestDispatcher rd=req.getRequestDispatcher("deleteproduct.jsp");
		rd.forward(req, resp);
		//resp.setContentType("html/text");
	}
}
