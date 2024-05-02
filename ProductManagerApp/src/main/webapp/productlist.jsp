

<%@page import="com.keerthi.servlet.ProductDao"
	import="java.util.Base64"%>
<%@ page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Product List</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
<div>
<h1 class="text-align-middle">List of Available Products....</h1>
</div>
<div>
<a class="btn btn-primary" href="add-product.html">Save Product</a>
</div>
<div>
<input type="text" placeholder="search"/>
</div>
<div>
<c:if test="${saveResult==1}">
<h1 class="text-danger text-center">Data inserted successfully....</h1>
</c:if>
</div>
<div>
<c:if test="${deleteResult==1}">
<h1 class="text-danger text-center">Data Deletion successfull....</h1>
</c:if>
<c:if test="${deleteResult==0}">
<h1 class="text-danger text-center">Data Deletion Fails....</h1>
</c:if>
</div>
	<table class="table table-bordered table-stripped">
		<thead class="thead-dark">
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Cost</th>
				<th>Product Brand</th>
				<th>Made In</th>
				<th>Manufacture Date</th>
				<th>Expiry Date</th>
				<th>Image</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="<%=new ProductDao().findAll()%>">
				<tr>
					<td>${product.proId}</td>
					<td>${product.proName}</td>
					<td>${product.proPrice}</td>
					<td>${product.proBrand}</td>
					<td>${product.proMadeIn}</td>
					<td>${product.proMfgDate}</td>
					<td>${product.proExpDate}</td>
					<td><img
						src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(product.proImage)}"
						alt="product Image" style="max-width: 100px; max-height: 100px;" ></td>
						<td>
						<audio controls>
						<source src="data:audio/mpeg;base64,${Base64.getEncoder().encodeToString(product.proAudio)}" type="audio/mpeg">
			
						</audio>
						</td>
						<td>
						<video controls>
						<source src="data:video/mpeg;base64,${Base64.getEncoder().encodeToString(product.proVideo)}" type="video/mp4">
			
						</video>
						</td>
						<td><a class="btn btn-primary" href="./Deleteproductservlet?proId=${product.proId}">Delete</a>
						<a class="btn btn-primary" href="./Editproductservlet?proId=${product.proId}">Edit</a></td>
					
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
</body>
</html>