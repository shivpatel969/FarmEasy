<%
	//fetch message value from the session
	String message = (String)session.getAttribute("message");

	if(message != null){
		//message print
		//out.println(message);
	%>	
	
		<!-- for html use -->
		<div class="alert alert-success alert-dismissible fade show" role="alert">
		  <strong><%= message %></strong>
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		
	<%	
		//remove message
		session.removeAttribute("message");
	}

%>