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
//import javax.servlet.http.HttpSession;
import com.Shoppingcart.dto.Admin;


@WebServlet("/myadmin")
public class SaveAdmin  extends HttpServlet {
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {

	//we are not genarating admin id automatically cause there are only one or two admins
	
	String id=req.getParameter("id");
	int id1=Integer.parseInt(id);
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	Admin admin=new Admin();
	admin.setId(id1);
	admin.setName(name);
	admin.setEmail(email);
	admin.setPassword(password);
	 et.begin();
	 em.persist(admin);
	 et.commit();
	 
	 
	 PrintWriter pw=resp.getWriter();	
	 pw.print("account created Successfully");

	 RequestDispatcher rd=req.getRequestDispatcher("login.html");
	 rd.include(req, resp);
	 resp.setContentType("text/html");
	
		
//		HttpSession hs=req.getSession();
//		hs.setAttribute("id",id1);
//		hs.setAttribute("name",name);
//		hs.setAttribute("password", password);

}
}
