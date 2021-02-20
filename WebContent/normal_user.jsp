<%@page import="com.farmeasy.entities.User" %>

<% 
	//check whether a user login are not (through session we check that inside httpSession current-user name user is present are not )
	User user = (User)session.getAttribute("current-user");
	
	if(user == null){
		session.setAttribute("message", "You are not Logged in !! Login first");
		response.sendRedirect("login.jsp");
		return;
	}
	else{
		if(user.getUserType().equals("admin")){
			session.setAttribute("message", "You are not normal user ! Do not access this page");
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
	<title>Normal User Panel</title>
	<!-- include css -->
	<%@include file="components/common_css_js.jsp" %>
		
	</head>
	<body>
	
		<!-- include navbar -->
		<%@include file="components/navbar.jsp" %>
	
		
		
	</body>
</html>