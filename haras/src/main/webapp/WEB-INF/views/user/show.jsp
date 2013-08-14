<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<title>Detalhes do Usuário</title>
</head>
<body>	
	<form:form modelAttribute="user">	
		<fieldset>
			<legend>Dados do usuário</legend>
			
			<table class="table table-bordered">
					<tr>
						<td>Id</td>
						<td>Nome</td>
						<td>Usuário</td>
						<td>Email</td>
						<td>Dt Criação</td>
						<td>Dt Modificação</td>
						<td>Usuário Criação</td>
						<td>Usuário Modificação</td>
					</tr>
						<tr>
							<td>${user.id}</td>
							<td width="200">${user.name}</td>
							<td>${user.userName}</td>
							<td>${user.email}</td>
							<td><fmt:formatDate value="${user.dateCreation}" pattern="dd/MM/yyyy"/></td>
							<td><fmt:formatDate value="${user.dateModify}" pattern="dd/MM/yyyy"/></td>
							<td>${user.userCreation}</td>
							<td>${user.userModify}</td>			
						</tr>						
				</table>
		</fieldset>

		<fieldset>
			<legend>Regras de acesso</legend>
			<c:if test="${not empty user.roles}">
				<table class="table table-bordered">
					<tr>
						<td>Id</td>
						<td><spring:message code="role.roleName" /></td>
					</tr>
					<c:forEach items="${user.roles}" var="role" varStatus="index">
						<c:url var="url" value="/user/${role.id}" />
						<tr>
							<td>${role.id}</td>
							<td>${role.roleName}</td>
						</tr>
					</c:forEach>	
				</table>
			</c:if>
			<c:if test="${empty user.roles}">Não há regras cadastradas.</c:if>
		</fieldset>		
	</form:form>
</body>

