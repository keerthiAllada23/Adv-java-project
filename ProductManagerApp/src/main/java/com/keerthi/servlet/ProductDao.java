package com.keerthi.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao 
{
	public int save(Product product) 
    {
		Connection connection=null;
		PreparedStatement preparedstatement=null;
		
   	 	int count=0;
   	 	try
   	 	{
   	 		 connection=DbConnection.createconnection();
   	 	
   	 			 preparedstatement=connection.prepareStatement("insert into Product values(?,?,?,?,?,?,?,?,?,?)");
   	 			 							
				
			  preparedstatement.setString(1,product.getProId());
			  preparedstatement.setString(2,product.getProName());
			  
			 
			  preparedstatement.setDouble(3,product.getProPrice());
			  preparedstatement.setString(4,product.getProBrand());
			  preparedstatement.setString(5,product.getProMadeIn());
			  
			  preparedstatement.setDate(6,product.getProMfgDate());
			  preparedstatement.setDate(7,product.getProExpDate());
			  
			  preparedstatement.setBytes(8, product.getProImage());	
			  preparedstatement.setBytes(9,product.getProAudio());
			  preparedstatement.setBytes(10,product.getProVideo());
			   
			count= preparedstatement.executeUpdate(); 
	        }
		  catch(SQLException e)
		  {
			  e.printStackTrace();
		  }
   	 finally
	 	{
	 		try
	 		{
	 			if(connection!=null && preparedstatement!=null) {
	 				connection.close();
	 				preparedstatement.close();
	 			}
	 		}
	 		catch(SQLException e)
	 		{
	 			e.printStackTrace();
	 		}
	 	}

		  return count;				  
				  

}
	public List<Product> findAll() throws SQLException
	  {
		  List<Product> pro=new ArrayList<Product>();
		 try(Connection connection=DbConnection.createconnection();
	   		  PreparedStatement preparedstatement=connection.prepareStatement("select * from Product"))
		 {
			 ResultSet resultset=preparedstatement.executeQuery();
			 while(resultset.next())
			 {
				 Product product=new Product();
				 product.setProId(resultset.getString("proId"));
				 product.setProName(resultset.getString("proName"));
				 
				 product.setProPrice(resultset.getDouble("proPrice"));
				 product.setProBrand(resultset.getString("proBrand"));
				 product.setProMadeIn(resultset.getString("proMadeIn"));
				 
				 product.setProMfgDate(resultset.getDate("proMfgDate"));
				 product.setProExpDate(resultset.getDate("proExpDate"));
				 product.setProImage(resultset.getBytes("proImage"));
				 product.setProAudio(resultset.getBytes("proAudio"));
				 product.setProVideo(resultset.getBytes("proVideo"));
				 
				 pro.add(product);
				 
			 }
		 }
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		 return pro;
		 }
	public int deleteById(String proId ) 
	  {
	 	 int deletecount=0;
	 	 try(Connection connection=DbConnection.createconnection();
	 			 PreparedStatement preparedStatement=connection.prepareStatement("delete from Product where proId=?"))
	 	 {
	 		 preparedStatement.setString(1, proId);
	 		 deletecount=preparedStatement.executeUpdate();
	 		 
	 	 }
	 	 catch(SQLException e)
	 	 {
	 		 e.printStackTrace();
	 	 }
	 	 return deletecount;
	  }
	 public Product findById(String proId) 
	  {
		  Product product=null;
	      try(Connection connection=DbConnection.createconnection();
	    		  PreparedStatement preparedstatement=connection.prepareStatement("select * from Product where proId=?"))
	    		  
	      {
	    	preparedstatement.setString(1, proId);
	    	ResultSet resultset=preparedstatement.executeQuery();
	    	if(resultset.next())
	    	{
	    		product=new Product();
	    		product.setProId(resultset.getString(1));
	    		product.setProName(resultset.getString(2));
	    		product.setProPrice(resultset.getDouble(3));
	    		product.setProBrand(resultset.getString(4));
	    		product.setProMadeIn(resultset.getString(5));
	    		product.setProMfgDate(resultset.getDate(6));
	    		product.setProExpDate(resultset.getDate(7));
	    		product.setProImage(resultset.getBytes(8));
	    		product.setProAudio(resultset.getBytes(8));
	    		product.setProVideo(resultset.getBytes(9));
	    	}
	      }
	      catch(SQLException e)
	      {
	    	  e.printStackTrace();
	      }
		  return product;
	  }
	 public int updateById(Product product)
	 {
		 String sql="update Product set proName=?,proPrice=?,proBrand=?,proMadeIn=?,proMfgDate=?,proExpDate=?,proImage=?,proAudio=?,proVideo=?,where proId=?";
		 int updateResult=0;
		 try(Connection connection=DbConnection.createconnection())
		 {
			PreparedStatement preparedstatement=connection.prepareStatement(sql) ;
			preparedstatement.setString(1,product.getProName());
			preparedstatement.setDouble(2, product.getProPrice());
			preparedstatement.setString(3,product.getProBrand());
			preparedstatement.setString(4,product.getProMadeIn());
			preparedstatement.setDate(5,product.getProMfgDate());
			preparedstatement.setDate(6,product.getProExpDate());
			preparedstatement.setBytes(7,product.getProImage());
			preparedstatement.setBytes(8,product.getProAudio());
			preparedstatement.setBytes(9,product.getProVideo());
			preparedstatement.setString(10,product.getProId());
			
			updateResult=preparedstatement.executeUpdate();
			
			
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		 return updateResult;
	 }
}