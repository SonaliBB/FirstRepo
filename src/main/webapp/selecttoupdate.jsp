<%@page import="java.io.PrintWriter"%>
<%@page import="com.Shoppingcart.dto.Product"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
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
EntityManagerFactory emf=Persistence.createEntityManagerFactory("sonali");
EntityManager em=emf.createEntityManager();
Query q=em.createQuery("select b from Product b");                
List<Product> products=q.getResultList();                                                   //method  will give the detlis of inavtive merchants in the form of list 
if(products.size()>0)
{%>
<table border="2px" cellspacing="20px" cellpadding="20px">
<th>id</th>
<th>name</th>
<th>category</th>
<th>price</th>
<th>stock</th>
<th>update</th>
<%  for(Product p:products){ 
	                                                                                      // all inactive merchants are  already present inside the 'merchants' variable  
%>

<tr>
<td> <%=p.getId() %></td>
<td> <%=p.getName() %></td>
<td> <%=p.getCategory() %></td>
<td> <%=p.getPrice() %></td>
<td> <%=p.getStock() %></td>
<td> <a  href="newupdatedproduct.html?id=<%=p.getId()%>">update</a></td>                   //the process of storing the info at the end of url is nothing but url rewritting
</tr>
<%} %>
</table>
<%
} else{
	
	PrintWriter pw=response.getWriter();
	pw.print("No product to update");
	RequestDispatcher rd=request.getRequestDispatcher("merchantoption.html");
	rd.include(request, response);
	response.setContentType("text/html");	
}
%>
</body>
</html>