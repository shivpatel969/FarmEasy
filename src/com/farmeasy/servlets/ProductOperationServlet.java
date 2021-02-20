package com.farmeasy.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.farmeasy.dao.CategoryDao;
import com.farmeasy.dao.ProductDao;
import com.farmeasy.entities.Category;
import com.farmeasy.entities.Product;
import com.farmeasy.helper.ConnectionProvider;

/**
 * Servlet implementation class ProductOperationServlet
 */
@MultipartConfig //apply because this servlet accept MultipartConfig type form (i.e. accept images etc.)
@WebServlet("/ProductOperationServlet")
public class ProductOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			
			
			/*
			 * Servlet do both of two work => 1.addCategory; 2.addProduct;
			 */
			
			String op = request.getParameter("operation");
			
			//Now check the value of op wheather it is addcategory or addproduct
			
			if(op.trim().equals("addcategory")) {
				//addcategory
				
				//Fetching category data
				String cat_title = request.getParameter("catTitle");
				String cat_Description = request.getParameter("catDescription");
				
				Category category = new Category(cat_title, cat_Description);
				
				/*
				 * //Check code Category category = new Category();
				 * category.setCategoryTitle(cat_title);
				 * category.setCategoryDescription(cat_Description);
				 */				
				
				//Save data in database
				CategoryDao dao = new CategoryDao(ConnectionProvider.getConnection());
				
				if(dao.saveCategory(category)) {
					out.println("<h1>Category Successfully saved</h1>");
				}else {
					out.println("<h1>error</h1>");
				}
				
				
				  category.setCategoryTitle(cat_title);
				  category.setCategoryDescription(cat_Description);
				 
				
				//Now save the category inside database
				
				
				
				out.println(cat_title);
				out.println(cat_Description);
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("message", "Category added successfully");
				//Redirect
				response.sendRedirect("admin.jsp");
				return;
				
			}
			else if(op.trim().equals("addproduct")) {
				//addproduct
				
				//Fetch the form data of product modal
				String pTitle = request.getParameter("prdTitle");
				String pDesc = request.getParameter("prdDescription");
				int pPrice = Integer.parseInt(request.getParameter("prdPrice"));
				int pDiscount = Integer.parseInt(request.getParameter("prdDiscount"));
				int pQuantity = Integer.parseInt(request.getParameter("prdQuantity"));
				int cId = Integer.parseInt(request.getParameter("cId"));
				//int catId = Integer.parseInt(request.getParameter("categoryId"));
				//To store the file we use Part type data
				Part part = request.getPart("prdPic");
				
				//Store the detail in the database
				//Check code for cid
				Product product = new Product(pTitle, pDesc, part.getSubmittedFileName(), pPrice, pDiscount, pQuantity, cId);
				
				/*
				 * product.setpTitle(pTitle); product.setpDesc(pDesc);
				 * product.setpPrice(pPrice); product.setpDiscount(pDiscount);
				 * product.setpQuantity(pQuantity);
				 * product.setpPhoto(part.getSubmittedFileName()); //for getting the file name
				 * we use part.getSubmittedFileName() product.setcId(cId);
				 */
				
				//Get category by id
				CategoryDao cdao = new CategoryDao(ConnectionProvider.getConnection());
				//Category category = cdao.getCategoryById(catId);
				
				//Now set product in category
				//product.setCategory(category);
			
				
				//product save.....
				//Provide connection to ProductDao
				ProductDao pdao = new ProductDao(ConnectionProvider.getConnection());
				pdao.saveProduct(product);
				
				//Upload a pic
				/* Find the to upload photo */
				String path = request.getRealPath("img")+File.separator+"products"+File.separator+part.getSubmittedFileName();
				/* System.out.println(path); */
				
				//It throw exception so we put into try catch block
				try {
					
					//Uploading code
					FileOutputStream fos = new FileOutputStream(path);
					
					InputStream is = part.getInputStream();
					
					//Reading data
					byte []data = new byte[is.available()];
					is.read(data);
					
					//Writing the data 
					fos.write(data);
					
					fos.close();
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				out.println("Product save success");
				
				//out.println("Category id is "+catId);
				
				
				  HttpSession httpSession = request.getSession();
				  httpSession.setAttribute("message", "Product is added successfully.....");
				  //Redirect
				  response.sendRedirect("admin.jsp");
				  
				  return;				 
				
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
