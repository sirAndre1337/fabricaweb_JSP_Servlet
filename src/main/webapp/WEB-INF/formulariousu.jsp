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

	Usuario usuario = (Usuario) request.getAttribute("usuario");
	

%>
<form action="usucontroller.do" method="post">
	
	<input type="hidden" id="id" name="id" value="<%=usuario.getId() %>">
	Nome:
	<input type="text" id="nome" name="nome" value="<%=usuario.getNome()%>">
	Login:
	<input type="text" id="login" name="login" value="<%=usuario.getLogin() %>">
	Senha:
	<input type="password" id="senha" name="senha" value="<%=usuario.getSenha() %>">
	<input type="submit" value="Salvar">
</form>



</body>
</html>