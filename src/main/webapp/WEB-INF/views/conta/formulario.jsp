<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionaro Contas</title>
</head>
<body>

	<h3> Adicionar Contas</h3>
	
	<form action="adicionaConta" method="post">
	
		Descrição: <br />
		<textarea name="descricao" rows="5" cols="100"></textarea>
		<form:errors path="conta.descricao"/>
		<br />
		Valor: 
		<input type="text" name="valor">
		<br />
		Tipo:
		<select name="tipo">
			<option value="ENTRADA">Entrada</option>
			<option value="SAIDA">Saída</option>
		</select>
	
		<br /><br />
		
		<input type="submit" value="Adiciona" />
		
	</form>	

</body>
</html>