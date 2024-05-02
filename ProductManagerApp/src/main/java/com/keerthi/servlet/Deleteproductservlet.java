package com.keerthi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deleteproductservlet
 */
@WebServlet("/Deleteproductservlet")
public class Deleteproductservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String proId=request.getParameter("proId");
	ProductDao dao=new ProductDao();
	int result=dao.deleteById(proId);
	
	if(result==1)
	{
		request.setAttribute("deleteResult", result);
		RequestDispatcher dispatcher=request.getRequestDispatcher("productlist.jsp");
		dispatcher.forward(request, response);
	}
	else
	{
		request.setAttribute("deleteResult", result);
		RequestDispatcher dispatcher=request.getRequestDispatcher("productlist.jsp");
		dispatcher.forward(request, response);
	}
	
	
	}

}
