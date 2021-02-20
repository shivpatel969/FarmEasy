<%@page import="com.farmeasy.entities.User" %>
<%@page import="com.farmeasy.dao.CategoryDao"%>
<%@page import="com.farmeasy.entities.Category"%>
<%@page import="com.farmeasy.helper.ConnectionProvider"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%
	User user1 = (User)session.getAttribute("current-user");
%>


<nav class="navbar navbar-expand-lg navbar-dark custom-bg">
 	<div class="container">
 		<div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">FarmEasy</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
       
		<li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            Category
	          </a>
	          <ul class="dropdown-menu dropdown-menu">
	           
	            <li><a class="dropdown-item">
	            
	            	<%
		            	//take variable of categorydao
		    			CategoryDao categorydao = new CategoryDao(ConnectionProvider.getConnection());
		    			ArrayList<Category> clist1 = categorydao.getCategories();
	    			
	            	%>
	            
	            
	            			<%
					
						for(Category category:clist1){
							
					   %>
	            		
	            		<a href="index.jsp?category=<%= category.getCategoryId() %>" class="list-group-item list-group-item-action"><%= category.getCategoryTitle() %></a>
	            		
	            		 <%
						}
					%>
	            
	            </a></li>
	           
	           <!--  <li><a class="dropdown-item" href="#">Another action</a></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li> -->
	          </ul>
	        </li>
	       
	      </ul>
	    
	    
      
      <ul class="navbar-nav ml-auto">
      
      	<li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#!" data-bs-toggle="modal" data-bs-target="#cart"> <i class="fa fa-cart-plus" style="font-size: 20px;"></i> <span class="cart-items">( 0 )</span> </a>
	        </li>
	        
      
      
      	<!-- Conditional rendering -->
      	<%
      		if(user1 == null){
      			
      	 	%>
      		<li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="login.jsp">Login</a>
	        </li>
	        
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="register.jsp">Register</a>
	        </li>	
      		
      		<%	
      		
      		}
      		else{
      			
      			
      			%>
      			
      			<li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="<%= user1.getUserType().equals("admin")?"admin.jsp":"index.jsp" %>"><%= user1.getUserName() %></a>
		          <%-- <a class="nav-link active" aria-current="page" href="<%= user1.getUserType().equals("admin")?"admin.jsp":"normal_user.jsp" %>"><%= user1.getUserName() %></a> --%>
		        </li>
		        
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="Logout">Logout</a>
		        </li>
      			
      			<%
      		}
      	%>
      	
      </ul>
      
      
    
    </div>
  </div>
 		
 	</div>
</nav>