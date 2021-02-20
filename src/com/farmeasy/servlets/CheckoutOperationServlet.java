package com.farmeasy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.farmeasy.dao.CheckoutDao;
import com.farmeasy.entities.Checkout;
import com.farmeasy.helper.ConnectionProvider;

/**
 * Servlet implementation class CheckoutOperationServlet
 */
@WebServlet("/CheckoutOperationServlet")
public class CheckoutOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutOperationServlet() {
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
			
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			Long mobile = Long.parseLong(request.getParameter("mobile"));
			String address = request.getParameter("address");
			
			/*
			 * out.println(email); out.println(name); out.println(mobile);
			 * out.println(address);
			 */
			
			Checkout ch = new Checkout(email,name,mobile,address);
			
			//save data in the database
			CheckoutDao chdao = new CheckoutDao(ConnectionProvider.getConnection());
			
			if(chdao.saveOrder(ch)) {
				out.println("<h1>Order Successfully saved</h1>");
			}else {
				out.println("<h1>Error</h1>");
			}
			
			//out.println("<h1>Order successfully placed</h1>");
			
			//now set the value in setter
			ch.setEmail(email);
			ch.setName(name);
			ch.setMobile(mobile);
			ch.setAddress(address);
			
			//Now save the Checkout details inside database
			out.println(email);
			out.println(name);
			out.println(name);
			out.println(address);
			
			out.println("Order save success");
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("message", "OrderPlaced Successfully");
			//redirect
			response.sendRedirect("order_placed_successfully.jsp");
			return;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
