package com.keerthi.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class Addproductservlet
 */
@WebServlet("/Addproductservlet")
@MultipartConfig
public class Addproductservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// READ THE DATA
		String proId=request.getParameter("proId");
		String proName=request.getParameter("proName");
		
		double proprice = Double.parseDouble(request.getParameter("proPrice"));
		String proBrand=request.getParameter("proBrand");
		String proMadeIn=request.getParameter("proMadeIn");
		
		Date proMfgDate=Date.valueOf(request.getParameter("proMfgDate"));
		Date proExpDate=Date.valueOf(request.getParameter("proExpDate"));
		
		Part part=request.getPart("proimg");
		InputStream inputstream=part.getInputStream();
		
		//COVERSION OF INPUTSTREAM INTO BYTE
     	byte[] proImage=IOUtils.toByteArray(inputstream);
     	
     	
     	Part part1=request.getPart("proAudio");
		InputStream inputstream1=part1.getInputStream();
		byte[] proAudio=IOUtils.toByteArray(inputstream1);
		

     	Part part2=request.getPart("proVideo");
		InputStream inputstream2=part2.getInputStream();
		byte[] proVideo=IOUtils.toByteArray(inputstream2);
		
     	
		
		//USING ABOVE DETAILS CREATE PRODUCT OBJECT
		Product product=new Product();
		product.setProId(proId);
		product.setProName(proName);
		product.setProPrice(proprice);
		product.setProBrand(proBrand);
		product.setProMadeIn(proMadeIn);
		product.setProMfgDate(proMfgDate);
		product.setProExpDate(proExpDate);
		product.setProImage(proImage);
		product.setProAudio(proAudio);
		product.setProVideo(proVideo);
		
		//GIVING THE PRODUCT OBJECT TODAO LAYER
		ProductDao productdao=new ProductDao();
		
		
	int result=productdao.save(product);
		
		if(result==1)
		{
			request.setAttribute("saveResult", result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("productlist.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("Data insertion fails check once....."+result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("add-product.html");
			dispatcher.include(request, response);

			}
		
	}

}
