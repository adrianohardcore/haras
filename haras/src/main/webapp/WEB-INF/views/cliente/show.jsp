<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<title>Detalhes do Cliente</title>
</head>
<body>	
	<form:form modelAttribute="cliente">
		<fieldset>
			<legend>Dados do Cliente</legend>

			<table class="table table-bordered">
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>CPF</th>
					<th>CNPJ</th>
					<th>Dt Criação</th>
					<th>Dt Modificação</th>
					<th>Usuário Criação</th>
					<th>Usuário Modificação</th>
				</tr>
				<tr>
					<td>${cliente.id}</td>
					<td width="200">${cliente.nome}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.cnpj}</td>
					<td><fmt:formatDate value="${cliente.dateCreation}"
							pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatDate value="${cliente.dateModify}"
							pattern="dd/MM/yyyy" /></td>
					<td>${cliente.userCreation}</td>
					<td>${cliente.userModify}</td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>Endereços</legend>
			<table id="tabela" class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Endereço</th>
						<th>Numero</th>
						<th>Complemento</th>
						<th>Bairro</th>
						<th>Cidade</th>
						<th>UF</th>
						<th>País</th>
					</tr>
				</thead>
				<tbody id="enderecos">
					<c:forEach items="${cliente.enderecos}" var="endereco"
						varStatus="status">
						<tr>
							<td>${endereco.id}</td>
							<td>${endereco.endereco}
							<td>${endereco.numero}</td>
							<td>${endereco.complemento}</td>
							<td>${endereco.bairro}</td>
							<td>${endereco.cidade}</td>
							<td>${endereco.uf}</td>
							<td>${endereco.pais}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
		
<%-- 		<form:button  value="/cliente/${cliente.id}/form" >Editar</form:button> --%>
		
		<a href="<c:url value="/cliente/${cliente.id}/form"/>" id="editar" >Editar</a>




	</form:form>
</body>

