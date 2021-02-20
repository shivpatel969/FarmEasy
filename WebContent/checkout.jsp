<!-- Check wheather user is login or not if they logged in we show checkout page if they not logged in we transfer to the login page --> 
<%
	//check whether a user login are not (through session we check that inside httpSession current-user name user is present are not )
	User user = (User) session.getAttribute("current-user"); //In jsp we can use session keyword directly //pass key->"current-user" inside getAttribute() method
	
	if(user == null){
		session.setAttribute("message", "You are not logged in !! Login first to access checkout page");
		response.sendRedirect("login.jsp");//redirect to login.jsp page
		return;
	}else{
		
	}
%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>

	<%@include file="components/common_css_js.jsp" %>

</head>
<body>
	
		<!-- include navbar -->
		<%@include file="components/navbar.jsp" %>
	
		<div class="container">
			<!-- Row -->
			<div class="row mt-5">
				<!-- Col -->
				<div class="col-md-6"> 
					<!-- Card -->
					<div class="card">
						<div class="card-body">
							
							<h3 class="text-center mb-5">Your selected items</h3>
							
							<div class="cart-body">
								
							</div>						
						</div>
					</div>
				</div>
				
				<!-- Col -->
				<div class="col-md-6"> 
					<!-- Form details -->
					<div class="card">
						<div class="card-body">
							
							<h3 class="text-center mb-5">Your details for order</h3>
							<!-- Form -->
							<form action="CheckoutOperationServlet" method="post">
								<div class="mb-3">
								    <label for="exampleInputEmail1" class="form-label">Email address</label>
								    <input name="email" value="<%= user.getUserEmail() %>" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
								    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
								 </div>
								 
								 <div class="mb-3">
								    <label for="name" class="form-label">Your name</label>
								    <input name="name" value="<%= user.getUserName() %>" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter name">
								 </div>
								
								 <div class="mb-3">
								    <label for="mobilenumber" class="form-label">Your contact</label>
								    <input name="mobile" value="<%= user.getUserMobile() %>" name="user_phone" type="number" class="form-control" id="mobilenumber" aria-describedby="emailHelp" placeholder="Enter contact number">
								 </div>
								
								 <div class="mb-3">
								  <label for="exampleFormControlTextarea1" class="form-label">Your shipping address</label>
								  <textarea name="address" class="form-control" id="exampleFormControlTextarea1" placeholder="Enter your address" rows="3"></textarea>
								</div>
															
								<div class="container text-center">
									<button class="btn btn-outline-success">Order now</button>
									<button class="btn btn-outline-primary">Continue shopping</button>
								</div>
								
							</form>					
						</div>
					</div>
				</div>
				
			</div>
		
		</div>
	
		<!-- include common_modals page -->
		<%@include file="components/common_modals.jsp" %>
		
	
</body>
</html>