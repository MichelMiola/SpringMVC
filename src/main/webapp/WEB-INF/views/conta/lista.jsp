<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/js/jquery-1.11.2.min.js"></script>
<title>Lista Contas</title>

<script type="text/javascript">

	function paga(id){
		$.post("pagaConta", {'id': id}, function(data){
			$("#conta_"+id).html("Paga");
		});
	}

</script>


</head>
<body>

	<table style="height: 10px; width: 775px;" border="1">

		<tr>

			<th>Código</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Paga</th>
			<th>Data de Pagamento</th>
			<th colspan="3">Ações</th>

		</tr>

		<c:forEach var="conta" items="${todasContas}">
			<tr>

				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td>
					<c:if test="${conta.paga eq false}">
           				Não paga
            		</c:if> 
            		<c:if test="${conta.paga eq true }">
	           			Paga
            		</c:if>
            	</td>
				<td><fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy"/></td>
				<td><a href="removeConta?id=${conta.id}"> Remover </a></td>
				<td><a href="mostraConta?id=${conta.id}">Alterar</a></td>
				
				<c:if test="${conta.paga eq false}">
					<td id="conta_${conta.id}"><a href="#" onclick="paga(${conta.id})">Pagar</a></td>
				</c:if>
				
			</tr>
		</c:forEach>

	</table>

</body>
</html>