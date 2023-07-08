package com.Shoppingcart.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Shoppingcart.dto.Admin;
import com.Shoppingcart.dto.Customer;
import com.Shoppingcart.dto.Merchant;
@WebServlet("/loginvalidate")
public class LoginValidation  extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String choose=req.getParameter("choose");
		if(choose.equals("Customer")){
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
			EntityManager em=emf.createEntityManager();
			Query query=em.createQuery("select a from Customer a where a.email=?1 and a.password=?2");
			query.setParameter(1, email);
			query.setParameter(2, password);
			List<Customer> customer=query.getResultList();
			if(customer.size()>0){//if object isn't empty
				
				//storing the customer login info in http session so it will become applicable through out the application 
				Customer c=customer.get(0);
				HttpSession hs=req.getSession();
				hs.setAttribute("customer", c);
				
				RequestDispatcher rd=req.getRequestDispatcher("customeroption.html");
				rd.forward(req,resp);
			}
			else{
				PrintWriter pw=resp.getWriter();
				pw.print("invalid credintials");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
			}	
		}
		else if(choose.equals("Merchant")){
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
			EntityManager em=emf.createEntityManager();
			Query query=em.createQuery("select a from Merchant a where a.email=?1 and a.password=?2");
			query.setParameter(1, email);
			query.setParameter(2, password);
			List<Merchant> merchant=query.getResultList();
			//if user is inter the correct email and password then only getResultList() method
			//will provide one list and data is stored  inside that list in the form of object and that list is retured back to the user 
			//if list size is>0 then we cqan say that correct credential so we can fetch details of merchant then we are checking the staus 
			//if active then provide access to merchantoption,html else display message
			if(merchant.size()>0){
	 			Merchant m=merchant.get(0);
	 			
	 			
	 			
			      if(m.getStatus().equals("active")) {
			    	  
			    	  HttpSession hs=req.getSession();
			 			hs.setAttribute("merchant",m);
			 			
				RequestDispatcher rd=req.getRequestDispatcher("merchantoption.html");
				rd.include(req,resp);
				resp.setContentType("text/html");
			      }
			      else if(m.getStatus().equals("inactive")) {
			      PrintWriter pw=resp.getWriter();
			      pw.print("your accont is not active");
			  	RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			      }
			      else if(m.getStatus().equals("blocked")) {
			    	  PrintWriter pw=resp.getWriter();
				      pw.print("your accont is blocked"); 
				  	RequestDispatcher rd=req.getRequestDispatcher("login.html");
					rd.include(req, resp);
					resp.setContentType("text/html");
			      }
			}
			else {
				PrintWriter pw=resp.getWriter();
				pw.print("invalid credentials");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, resp);
			resp.setContentType("text/html");	
		}
		}
		else if(choose.equals("Admin")){
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
			EntityManager em=emf.createEntityManager();
			Query query=em.createQuery("select a from Admin a where a.email=?1 and a.password=?2");
			query.setParameter(1, email);
			query.setParameter(2, password);
			List<Admin> admin=query.getResultList();
			if(admin.size()>0)
			{
				
				Admin a=admin.get(0);//to fetch the admin details from list 0 cause index starts from zero
				
				HttpSession hs=req.getSession();
            	hs.setAttribute("admin",a);//DT of key is string and DT of value Object
            	                           //Admin is subclass of object class
//				
				RequestDispatcher rd=req.getRequestDispatcher("adminoption.html");
				rd.forward(req,resp);
				resp.setContentType("text/html");
			}
			else {
				PrintWriter pw=resp.getWriter();
				pw.print("invalid credintials");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		         }
		}
	}
}


