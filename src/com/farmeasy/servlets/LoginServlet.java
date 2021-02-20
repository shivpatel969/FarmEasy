package com.farmeasy.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.farmeasy.dao.UserDao;
import com.farmeasy.entities.User;
import com.farmeasy.helper.ConnectionProvider;
import com.sun.xml.internal.ws.wsdl.writer.document.Message;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			
			//data fetch
			String uemail = request.getParameter("email");
			String upassword = request.getParameter("password");
			
			
			//validations(not blank)
						
						
			//Authenticating user
			UserDao dao = new UserDao(ConnectionProvider.getConnection());
			
			User user = dao.getUserByEmailAndPassword(uemail, upassword);
			
			//getSession
			HttpSession httpSession = request.getSession();
			
			  if(user == null){
				
				//Send message
				httpSession.setAttribute("message", "Invalid Details !! Try with another one");//key is message
				
				//redirect to login page
				response.sendRedirect("login.jsp");
			  
				return;
			  }
			  else{
				  out.println("<h1> WELCOME "+user.getUserName()+"</h1>");
				  
				  //login 
				  //for login we use session(httpSession)
				  httpSession.setAttribute("current-user", user);//to store the data session we setAttribute method  //key is "current-user" & value is u
				  
				  //check whether user is normal or admin
				  
				  if(user.getUserType().equals("admin")) {
				  	  //if user is admin then we have to redirect to admin.jsp page
					  response.sendRedirect("admin.jsp");
				  }
				  else if(user.getUserType().equals("normal")) {
					  //if user is normal so then we have to redirect to normal.jap 
					  //response.sendRedirect("normal_user.jsp");
					  
					  //check
					  response.sendRedirect("index.jsp");
				  }
				  else {
					  out.println("We have not identified user type");
				  }
			  }
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
