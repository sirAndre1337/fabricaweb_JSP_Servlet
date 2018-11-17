<%@page import="br.com.fabricadeprogramador.model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuários.</title>
<script type="text/javascript">
function confirmaExclusao (id) {
	if (window.confirm('Tem certeza que deseja excluir ?')){
		location.href="usucontroller.do?acao=excluir&id=" + id;
	}
}

function novo() {
	location.href="usucontroller.do?acao=novo";
}

</script>

</head>
<body>

	<%
		List<Usuario> usuarios = (List<Usuario>) request.getAttribute("lista");
	%>
	<%@include file="menu.jsp"%>
	<table border="1">
	<tr>
		<th>id</th>
		<th>nome</th>
		<th>ação</th>
	</tr>	
	<% for(Usuario usuario : usuarios){  %>
	<tr>
		<td> <%= usuario.getId() %></td>
		<td> <%= usuario.getNome() %></td>
		<td> <a href="usucontroller.do?acao=atualizar&id=<%=usuario.getId()%>">Editar</a> | <a href="javascript:confirmaExclusao(<%=usuario.getId()%>)">Excluir </a> </td>
		</tr>
	<%} %>
	</table>
	<input type="button" value="Novo" onclick="javascript:novo()">
</body>
</html>