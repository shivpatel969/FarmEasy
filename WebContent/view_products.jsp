<%@page import="com.farmeasy.entities.User" %>

<%
	//check whether a user login are not (through session we check that inside httpSession current-user name user is present are not )
	User user = (User) session.getAttribute("current-user"); //In jsp we can use session keyword directly //pass key->"current-user" inside getAttribute() method
	
	if(user == null){
		session.setAttribute("message", "You are not logged in !! Login first");
		response.sendRedirect("login.jsp");//redirect to login.jsp page
		return;
	}
	else{
		if(user.getUserType().equals("normal")){
			session.setAttribute("message", "You are not admin ! Do not access this page");
			response.sendRedirect("login.jsp");
			return;
		}
	}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Admin Panel-Products page</title>
	<!-- include css -->
	<%@include file="components/common_css_js.jsp" %>
		
		<style>
		
			body {
				background: #e2e2e2;
			}
		
		</style>
		
	</head>
<body>
		<!-- include navbar -->
		<%@include file="components/navbar.jsp" %>
		
		
		<!-- include common_modals page -->
		<%@include file="components/common_modals.jsp" %>
		
</body>
</html>