package com.keerthi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Editproductservlet")
public class Editproductservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proId=request.getParameter("proId");
		ProductDao dao=new ProductDao();
		Product existingobject=dao.findById(proId);
		
		//sending the product object to edit-form by adding
		request.setAttribute("existingobject", existingobject);
		
		//forward the request to edit-form
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("Edit-form.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

}
