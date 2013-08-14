<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<head>
<title>Clientes</title>

<c:url value="/clientes" var="recordsUrl" />
<c:url value="/cliente/form" var="addUrl" />
<c:url value="/cliente/" var="editUrl" />
<c:url value="/cliente/delete" var="deleteUrl" />


</head>
<body>			
		<table class="table table-bordered">
				<tr>
						<td>Id</td>
						<td>Nome</td>
						<td>CPF</td>
						<td>CNPJ</td>
						<td>Dt Criação</td>
						<td>Dt Modificação</td>
						<td>Usuário Criação</td>
						<td>Usuário Modificação</td>
				</tr>
				<c:forEach items="${clientes}" var="cliente" varStatus="status">
					<tr>						
							<td>${cliente.id}</td>
							<td width="200">${cliente.nome}</td>
							<td>${cliente.cpf}</td>
							<td>${cliente.cnpj}</td>
							<td><fmt:formatDate value="${cliente.dateCreation}" pattern="dd/MM/yyyy"/></td>
							<td><fmt:formatDate value="${cliente.dateModify}" pattern="dd/MM/yyyy"/></td>
							<td>${cliente.userCreation}</td>
							<td>${cliente.userModify}</td>					
					</tr>				
				</c:forEach>		
			</table>		
</body>
</html>