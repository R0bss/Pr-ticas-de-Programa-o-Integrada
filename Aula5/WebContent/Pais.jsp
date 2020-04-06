<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Pais"%>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pais</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<!-- 	<h3>O seu cliente acaba de ser cadastrado</h3>
<!--	
	Id: <br>
	Nome: <br>
	Fone: <br>
	E-mail: <br>
</body>
</html>  -->
	<%
		Pais pais = (Pais) request.getAttribute("Pais");
	%>

	<!-- Barra superior comos menus denavegação -->
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">
			VisualizarPais #<%=pais.getId()%></h3>
		<div class="row">
			<div class="col-md-12">
				<p>
					<strong>Nome</strong>
				</p>
				<p>
					<%=pais.getNome()%>
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<p>
					<strong>Populaçao</strong>
				</p>
				<p>
					<%=pais.getPopulacao()%>
				</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Area</strong>
				</p>
				<p>
					<%=pais.getArea()%>
				</p>
			</div>
		</div>
		<hr />
		<div id="actions" class="row">
			<div class="col-md-12">
				<a href="index.html" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>