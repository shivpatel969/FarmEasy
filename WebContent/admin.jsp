<!-- Protect the admin page from normal user -->
<!-- if someone is not login and if login and they are not admin he/she cann't open this page directly from url writing in browser --> 
<%@page import="java.sql.Connection"%>
<%@page import="com.farmeasy.helper.ConnectionProvider"%>
<%@page import="com.farmeasy.entities.Category"%>
<%@page import="com.farmeasy.entities.Product"%>
<%@page import="com.farmeasy.dao.CategoryDao"%>
<%@page import="com.farmeasy.dao.HelperDao"%>
<%@page import="com.farmeasy.entities.User" %>
<%@page import="com.farmeasy.helper.Helper" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import="java.util.Map" %>
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

	<%
		/* <!-- List of category is come through this code --> */
		CategoryDao cdao= new CategoryDao(ConnectionProvider.getConnection());
		ArrayList<Category> list = cdao.getCategories();    
				       						
	%>
	
	
	<!-- This is used for getting counting user and product from the HelperDao class -->
	<%
		HelperDao helpdao = new HelperDao(ConnectionProvider.getConnection());
		//Getting count		
		Map<String,Long> m = helpdao.getCounts();
	%>
	

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Admin Panel</title>
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
	
		<div class="container admin">
		
				
				<div class="container-fluid mt-3"> <!-- mt-3 means margin of 3 -->
				
					<%@include file="components/message.jsp" %>
				
				</div>
		
		
				<!-- First row -->		
				<div class="row mt-3">
					<!-- First col. -->
					<div class="col-md-4">
						<!-- box is a card -->
						
						<!-- This is first box -->
						<div class="card" data-bs-toggle="tooltip" data-bs-placement="left" title="Number of user in this website...">
													
							<!-- inside card body is present -->
							<div class="card-body text-center">
								
								<!-- include image in box -->
								<div class="container">
									<img style="max-width : 125px;" class="img-fluid rounded-circle" src="img/team.png" alt="user_icon">
								</div>
								
								<h1><%= m.get("userCount") %></h1>
								<h1 class="text-uppercase text-muted">Users</h1>
							</div>
							
						</div>
					</div>
					
					<!-- Second col. -->
					<div class="col-md-4">
						<!-- box is a card -->
						<div class="card" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Total categories">
							
							<!-- inside card body is present -->
							<div class="card-body text-center">
								
								<!-- include image in box -->
								<div class="container">
									<img style="max-width : 125px;" class="img-fluid rounded-circle" src="img/menu.png" alt="user_icon">
								</div>
								
								<h1><%= list.size() %></h1>
								<h1 class="text-uppercase text-muted">Categories</h1>
							</div>
							
						</div>
						
					</div>
					
					<!-- Third col. -->
					<div class="col-md-4">
						<!-- box is a card -->
						<div class="card" data-bs-toggle="tooltip" data-bs-placement="right" title="Total number of products">
							
							<!-- inside card body is present -->
							<div class="card-body text-center">
								
								<!-- include image in box -->
								<div class="container">
									<img style="max-width : 125px" class="img-fluid rounded-circle" src="img/package.png" alt="user_icon">
								</div>
								
								<h1><%= m.get("productCount") %></h1>
								<h1 class="text-uppercase text-muted">Products</h1>
							</div>
							
						</div>
					</div>
					
				</div>
			
			<!-- Second row -->
			<div class="row mt-3">
				
					<!-- Second row First col. -->
					<!-- md means medium device/screen -->
					<div class="col-md-6"> 
						<!-- box is a card -->
						<!-- This is first box -->
						<div class="card" data-bs-toggle="modal" data-bs-target="#add-category-modal">
													
							<!-- inside card body is present -->
							<div class="card-body text-center">
								
								<!-- include image in box -->
								<div class="container">
									<img style="max-width : 125px" class="img-fluid rounded-circle" src="img/keys.png" alt="user_icon">
								</div>
								
								<p class="mt-2">Click here to add new category</p>
								<h1 class="text-uppercase text-muted">Add Category</h1>
							</div>
							
						</div>
					
					</div>
					
					<!-- Second row Second col. -->
					<div class="col-md-6 ">
						<!-- box is a card -->
						<!-- This is first box -->
						<div class="card" data-bs-toggle="modal" data-bs-target="#add-product-modal">
													
							<!-- inside card body is present -->
							<div class="card-body text-center">
								
								<!-- include image in box -->
								<div class="container">
									<img style="max-width : 125px" class="img-fluid rounded-circle" src="img/plus.png" alt="user_icon">
								</div>
								
								<p class="mt-2">Click here to add new product</p>
								<h1 class="text-uppercase text-muted">Add Product</h1>
							</div>
							
						</div>
					</div>
					
			</div>
			
			<!-- View products row -->
			<div class="row mt-3">
			
				<div class="col-md-12"> 
						<!-- box is a card -->
						<!-- This is first box -->
						<div onclick="window.location=`index.jsp`" class="card" data-bs-toggle="tooltip" data-bs-placement="top" title="Click here to view all the products">
						<!-- <div onclick="window.location=`view_products.jsp`" class="card" data-bs-toggle="tooltip" data-bs-placement="top" title="Click here to view all the products"> -->
													
							<!-- inside card body is present -->
							<div class="card-body text-center">
								
								<!-- include image in box -->
								<div class="container">
									<img style="max-width : 125px" class="img-fluid rounded-circle" src="img/eye.png" alt="user_icon">
								</div>
								
								<p class="mt-2">Click here to view all the products</p>
								<h1 class="text-uppercase text-muted">View Products</h1>
							</div>
							
						</div>
					
					</div>
			
			</div>		
			
		</div>
		
		
		<!-- Add Category Modal -->
				
				<!-- Modal -->
				<div class="modal fade" id="add-category-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg">
				    <div class="modal-content">
				      <div class="modal-header custom-bg text-white">
				        <h5 class="modal-title" id="exampleModalLabel">Fill Category Details</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       
				       <!-- Create a form in Category Modal -->
				       <form action="ProductOperationServlet" method="post">
				       	
				       		<input type="hidden" name="operation" value="addcategory">
				       	
				       		<div class="form-group">
				       		
				       			<input type="text" class="form-control" name="catTitle" placeholder="Enter category title" required/>
				       		
				       		</div>
				       
				       		<div class="form-group mt-2">
				       		
				       			<textarea class="form-control" style="height: 300px" name="catDescription" placeholder="Enter category description" required></textarea>
				       		
				       		</div>
				       
				       		<div class="container text-center mt-2">
				       		
				       			<button class="btn btn-outline-success">Add Category</button>
	    	     			    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				       		</div>
				       
				       </form>
				    
				    </div>
				  </div>
				</div>
			</div>
		
		<!-- End Category Modal -->
		
		
		<!-- Add Product Modal -->
		
			<!-- Modal -->
			<div class="modal fade" id="add-product-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-lg">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Fill Product Details</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			       		
			       		<form action="ProductOperationServlet" method="post" enctype="multipart/form-data"> <!-- The enctype attribute specifies how the form-data should be encoded when submitting it to the server. ( multipart means media photo video) -->
			       			
			       			<input type="hidden" name="operation" value="addproduct">
			       			
			       			<!-- Product Title -->
			       			<div class="form-group">
			       			
			       				<input type="text" class="form-control" name="prdTitle" placeholder="Enter product title" required>
			       			
			       			</div>
			       			<!-- Product Description -->
			       			<div class="form-group mt-2">
			       			
			       				<textarea type="text" class="form-control" name="prdDescription" style="height: 150px" placeholder="Enter product description" required></textarea>
			       			
			       			</div>
			       			
			       			<div class="form-group mt-2">
			       			
			       				<input type="number" class="form-control" name="prdPrice" placeholder="Enter product price" required>
			       			
			       			</div>
			       			
			       			<div class="form-group mt-2">
			       			
			       				<input type="number" class="form-control" name="prdDiscount" placeholder="Enter product discount" required>
			       			
			       			</div>
			       			
			       			<div class="form-group mt-2">
			       			
			       				<input type="number" class="form-control" name="prdQuantity" placeholder="Enter product quantity" required>
			       			
			       			</div>
			       			
			       			<!-- Product category -->
			       					       			
			       			<div class="form-group mt-2">
			       			
			       				<select type="number" name="cId" class="form-control" id="">
			       					
			       					<option selected disabled>--- Select Category ---</option> <!-- Here we use selected disabled in option tag which means we can't select this option --> 
			       					
			       					<% 
			       						for(Category c:list){
			       					%>
			       					
			       					<option value="<%= c.getCategoryId()%>"><%= c.getCategoryTitle() %></option>
			       					
			       					<%
			       						}
			       				
			       					%>
			       					
			       					
			       					
			       				
			       				</select>
			       			
			       			</div>
			       			
			       			
			       					
			       			
			       			
			       			
			       			<!-- Product file -->
			       			<div class="form-group mt-2">
			       				<label for="prdPic">Select Picture of product</label>
			       				<input type="file" class="form-control" name="prdPic" required>
			       			
			       			</div>
			       		       			
			       			<div class="container text-center mt-2">
			       			
			       				<button class="btn btn-outline-success">Add Product</button>
			        			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			       				
			       			</div>
			       			
			       		</form>
			       		
			     
			      	
			      </div>
			    </div>
			  </div>
			</div>
			
			
		<!-- End Product Modal -->
		
		
		<!-- include common_modals page -->
		<%@include file="components/common_modals.jsp" %>
		
		
		<script>
		
			var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
			  return new bootstrap.Tooltip(tooltipTriggerEl)
			})	 
			
		</script>
		
	</body>
</html>