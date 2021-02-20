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

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
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
			
//			String check= request.getParameter("check");
//			
//			if(check==null){
//				out.println("Box not checked ");
//			}else { 
			
				//We use this try catch to print exception on console 
				try{
					
					String userName = request.getParameter("user_name");
					String userEmail = request.getParameter("user_email");
					String userPassword = request.getParameter("user_password");
					String userConfirmPassword = request.getParameter("user_confirm_password");
					String userMobileNumber = request.getParameter("user_phone");
					
					//String type = "normal";
					
					//server side validations
					if(userName.isEmpty()) {
						out.println("Name is blank");
						return;
					}
					
					//Now we create user object
					
					
					User user = new User(userName, userEmail, userPassword, userMobileNumber, null,"normal");
					
					//Now create UserDao object
					UserDao dao = new UserDao(ConnectionProvider.getConnection());
					
					if(dao.saveUser(user)) {
						//IF SUCCEESFULLY SAVE THEN SHOW MSG DONE
						out.println("<h1>Successfully Saved</h1>");
						out.println("done");
					}else {
						//IF UNSUCEESFULL THEN SHOW ERROR MSG
						out.print("error");
					}
					
					
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("message", "Registration Successful !!");
					response.sendRedirect("register.jsp");
					return;
					
				}catch(Exception e) {
					e.printStackTrace();
				}
		
			}
			
		}
		
	}
	
//}