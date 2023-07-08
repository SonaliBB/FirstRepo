<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.Shoppingcart.dto.Admin"%>
<%@page import="com.Shoppingcart.dto.Merchant"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String id=request.getParameter("id");
int id1=Integer.parseInt(id);
EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
EntityManager em=emf.createEntityManager();
EntityTransaction et=em.getTransaction();

Merchant m=em.find(Merchant.class,id1);
//System.out.println(m.getId());
m.setStatus("active");

//Here there is no need to Create the object of HttpSession like HttpSession hs=req.getSession(); cause JSP already provide that  which is "session"
Admin a=(Admin)session.getAttribute("admin");//here data of admin is get from the session object which created in loginvalidation then stored inside one admin variable
m.setAdmin(a);  //giving the admin data to merchat     ITS IMPORTANT TO SET ADMIN TO MERCHANT AND MERCHANT TO THE ADMINgt6

List<Merchant> merchant=a.getMerchants();
merchant.add(m);//here we are adding only one merchant

a.setMerchants(merchant);//that single merchant is set for that admin+

 et.begin();
em.merge(m);//used to update the data
em.merge(a);
et.commit();
//System.out.println(merchant.size());
/* if(merchant!=null)
{
	merchant.add(m);
	
}
else
	{
	merchant=new ArrayList();
	merchant.add(m);
	}*/
	
	
 
RequestDispatcher rd=request.getRequestDispatcher("approvemerchant.jsp");
rd.forward(request, response);
//relation bet admin and merchant 1 -m
//bet merchant and admin m-1
//need to set Admin_id col in db with id of admin
//to get the admin details of admin get the help of session tracking inside that httpsession
//when admin login the need to store the details in httpsession 
%>
</body>
</html>