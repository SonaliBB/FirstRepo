<%@page import="com.Shoppingcart.dto.Merchant"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.*"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@page import="javax.crypto.spec.PSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
EntityManager em=emf.createEntityManager();
Query q=em.createQuery("select b from Merchant b where b.status='inactive'");              //insinglequote cause DT of //status in db is varchar
List<Merchant> merchants=q.getResultList();                                                   //method  will give the detlis of inavtive merchants in the form of list 
if(merchants.size()>0)
{%>
<table border="2px" cellspacing="20px" cellpadding="20px">
<th>id</th>
<th>name</th>
<th>email</th>
<th>password</th>
<th>mobileno</th>

<th>status</th>
<th>approve</th>
<%  for(Merchant m:merchants){ 
	                                                                                      // all inactive merchants are  already present inside the 'merchants' variable  
%>

<tr>
<td> <%=m.getId() %></td>
<td> <%=m.getName() %></td>
<td> <%=m.getEmail() %></td>
<td> <%=m.getPassword() %></td>
<td> <%=m.getMobileno() %></td>
<td> <%=m.getStatus() %></td>
<td> <a  href="merchantstatusactive.jsp?id=<%=m.getId()%>">Approve</a></td>
<%                                                                                             //the process of storing the info at the end of url is nothing but url rewritting %>
</tr>
<%} %>
</table>
<%
} else{
	
	PrintWriter pw=response.getWriter();
	pw.print("No merchants to be approve");
	RequestDispatcher rd=request.getRequestDispatcher("adminoption.html");
	rd.include(request, response);
	response.setContentType("text/html");	
}
%>
</body>
</html>