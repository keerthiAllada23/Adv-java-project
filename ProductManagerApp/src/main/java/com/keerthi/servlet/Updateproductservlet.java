package com.keerthi.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;


@WebServlet("/Updateproductservlet")
@MultipartConfig
public class Updateproductservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String proId=request.getParameter("proId");
		String Updatedproductname=request.getParameter("proName");
		double Updatedproductprice=Double.parseDouble(request.getParameter("proPrice"));
		String UpdatedproductBrand=request.getParameter("proBrand");
		String UpdatedproductMadeIn=request.getParameter("proMadeIn");
		Date UpdatedproductMfgDate=Date.valueOf(request.getParameter("proMfgDate"));
		Date UpdatedproductExpDate=Date.valueOf(request.getParameter("proExpDate"));
		
		Product product=new Product();
		product.setProId(proId);
		product.setProName(Updatedproductname);
		product.setProPrice(Updatedproductprice);
		product.setProBrand(UpdatedproductBrand);
		product.setProMadeIn(UpdatedproductMadeIn);
		product.setProMfgDate(UpdatedproductMfgDate);
		product.setProExpDate(UpdatedproductExpDate);
		
		
		Part filepart=request.getPart("newProImage"); //new "ProImage" is the name of your file i/p field
		if(filepart !=null&& filepart.getSize()>0)
		{
			
			InputStream inputstream=filepart.getInputStream();
			byte[] newImageData=IOUtils.toByteArray(inputstream);
			product.setProImage(newImageData);
			
		}
	/*	else
		{
			Part file=request.getPart("existingImage");
			InputStream inputstream=file.getInputStream();
			byte[] existingImage=IOUtils.toByteArray(inputstream);
			product.setProImage(existingImage);
		}  
             */
		else
		{
		String s=request.getParameter("existingImage");
		byte [] existingImage=Base64.getDecoder().decode(s);
		product.setProImage(existingImage);
		}
		
			ProductDao dao=new 	ProductDao();
			int result=dao.updateById(product);
			if(result==1)
			{
				RequestDispatcher dispatcher=request.getRequestDispatcher("productlist.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				response.setContentType("text/html");
				PrintWriter writer=response.getWriter();
				writer.println("Data Updation fail check once....."+result);
				RequestDispatcher dispatcher=request.getRequestDispatcher("add-product.html");
				dispatcher.include(request, response);
			}
		
		
		
		
	}

}
