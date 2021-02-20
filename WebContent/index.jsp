<%@page import="com.farmeasy.dao.CategoryDao"%>
<%@page import="com.farmeasy.entities.Product"%>
<%@page import="com.farmeasy.entities.Category"%>
<%@page import="com.farmeasy.helper.Helper"%>
<%@page import="com.farmeasy.helper.ConnectionProvider"%>
<%@page import="com.farmeasy.dao.ProductDao"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FarmEasy | Homepage</title>

<!-- include css & js in this page -->
<%@include file="components/common_css_js.jsp" %>

</head>
<body>
	<!-- use or include navbar component here  -->
	<%@include file="components/navbar.jsp" %>
	
	<div class="container-fluid">
	
	<!-- Create row -->
	<div class="row mt-3 mx-2">
	
		<%
		
			String cat = request.getParameter("category");
			//out.println(cat); 
			
			ProductDao pdao = new ProductDao(ConnectionProvider.getConnection());
			List<Product> list = null;
			
			if(cat == null || cat.trim().equals("all")){
				list = pdao.getAllProducts();
			}else{
				int cId = Integer.parseInt(cat.trim());
				list = pdao.getAllProductsById(cId);
				
			}
			
			
			
			//take variable of categorydao
			CategoryDao cdao = new CategoryDao(ConnectionProvider.getConnection());
			ArrayList<Category> clist = cdao.getCategories();
			
		%>
	
	
		<!-- Show Categories -->
		<div class="col-md-2">
		
				<%-- <h1><%= clist.size() %></h1> --%>
				
				
				<div class="list-gruop mt-4">
				
					 <a href="index.jsp?category=all" class="list-group-item list-group-item-action active" aria-current="true">All Products</a>
									
				
				<!-- //Now traverse by using foreach loop -->
				<%
				
					for(Category c:clist){
						
				   %>
				
					<a href="index.jsp?category=<%= c.getCategoryId() %>" class="list-group-item list-group-item-action"><%= c.getCategoryTitle() %></a>
				   
				   <%
					}
				%>
				
				</div>	
					
		</div>	
		
		<!-- Show Products -->
		<div class="col-md-10"  >
		
				<div class="row mt-4 " >
				
					<!-- col:12 grid -->
					<div class="col-md-12" >
					
						<div class="card-columns" data-masonry='{"percentPosition": true }'>
						
							<!-- Traversing products -->
							<%
								for(Product p:list){
							%>
						
							<!-- Product card -->		
							<div class="card product-card" style="width: 18rem;">
							
								  <div class="container text-center"><img src="img/products/<%= p.getpPhoto() %>" style="max-height: 200px; max-width: 100%;width:auto;" class="card-img-top m-2" alt="..."></div>
								
								  <div class="card-body" >
					
								    <h5 class="card-title"><%= p.getpTitle() %></h5>
								    <p class="card-text"><%= Helper.get10Words(p.getpDesc()) %></p>
					
								  </div>
																
								
								<div class="card-footer text-center">
								
									<button class="btn custom-bg text-white" onclick="add_to_cart(<%= p.getpId() %>,'<%= p.getpTitle() %>',<%= p.getPriceAfterApplyingDiscount() %>)">Add to Cart</button>
									<button class="btn btn-outline-success"> &#8377;<%= p.getPriceAfterApplyingDiscount() %>/-  <span class="text-secondary discount-label"> &#8377; <%= p.getpPrice() %> , <%= p.getpDiscount() %> % off</span> </button>
								
								</div>
							
							</div>
										
							
							<%
								}
							
								if(list.size() == 0){
									out.println("<h1>No item in this category<h1>");
								}
							%>
						
						</div>	
					
					</div>
				
				</div>

				
		</div>	
		
		
		
		
	</div>	
		
		
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/masonry-layout@4.2.2/dist/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>
	
	<!-- include common_modals page -->
	<%@include file="components/common_modals.jsp" %>
	
</body>
</html>