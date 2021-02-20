<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>

<!-- include css -->
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
	<!-- include navbar -->
	<%@include file="components/navbar.jsp" %>
	
	<!-- Now create a form -->
	<div class="container-fluid">
			<div class="row mt-3"> 
				<div class="col-md-4 offset-md-4">
					
					<!-- Create a card -->
					<div class="card">
					
						<%@include file="components/message.jsp" %>
					
						<div class="card-body px-5">
						
							<center><img alt="logo" src="img/man.png" width="40%"></center>	
							<h3 class="text-center my-3">Register here !!</h3>
							
							<form action="UserRegisterServlet" method="post">
							
								  <div class="mb-3">
								    <label for="name" class="form-label">Name</label>
								    <input name="user_name" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter here">
								  </div>		
							  
								  <div class="mb-3">
								    <label for="email" class="form-label">Email address</label>
								    <input name="user_email" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter here">
								    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
								  </div>
								 
								  <div class="mb-3">
								    <label for="password" class="form-label">Password</label>
								    <input name="user_password" type="password" class="form-control" id="password" placeholder="Enter here">
								  </div>
								  	
								  <div class="mb-3">
								    <label for="mobilenumber" class="form-label">Mobile Number</label>
								    <input name="user_phone" type="number" class="form-control" id="mobilenumber" aria-describedby="emailHelp" placeholder="Enter here">
								  </div>		
													 
								  <div class="mb-3 form-check">
								    <input type="checkbox" class="form-check-input" id="exampleCheck1">
								    <label class="form-check-label" for="exampleCheck1">I Accept Terms and Conditions.</label>
								  </div>
								  
								  <div class="container text-center">
								  	<button class="btn btn-outline-success">Register</button>
								  	<button class="btn btn-outline-warning">Reset</button>
								  </div>
								
							</form>
					
						
						</div>
					</div>
					
				</div>
			</div>
		
		
	</div>
	
</body>
</html>