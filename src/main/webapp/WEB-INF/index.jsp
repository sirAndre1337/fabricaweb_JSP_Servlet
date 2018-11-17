<%@page import="br.com.fabricadeprogramador.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="menu.jsp"%>
	
	<%
	Usuario usuario = (Usuario) session.getAttribute("usuAutenticado");
	%>
	
	Bem vindo <%=usuario.getNome()%>.
	
</body>
</html>