<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<title><spring:message code="user.update" /></title>
</head>
<body>
	<c:url var="url" value="/user" />
	<form:form action="${url}" method="PUT" modelAttribute="user"> 	
	
		 
						
		<form:hidden path="id" />
		<form:hidden path="password" />
		<fieldset>
			<legend>Dados do usuário</legend>
			<div>
				<label for="name"><spring:message code="user.name" /></label>				
				<form:input maxlength="50" path="name" size="50" />
				<form:errors path="name" cssClass="alert alert-error"  />
			</div>
			<div>
				<label for="userName"> <spring:message code="user.username" /></label>				
				<form:input maxlength="50" path="userName" size="50" />
				<form:errors path="userName" cssClass="alert alert-error" />
			</div>
			<div>
				<label for="email"> <spring:message code="user.email" />
				</label>				
				<form:input maxlength="50" path="email" size="50" />
				<form:errors path="email" cssClass="alert alert-error"/>
			</div>
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
		<fieldset>
			<legend>Alterar as Regras de acesso</legend>
			<form:checkboxes items="${roleList}" path="roles"
				itemLabel="roleName" itemValue="id" /> 
		</fieldset>		
		
		<fieldset>
			<legend>Alterar senha</legend>
			
			<div>
				<label for="passwordForm"><spring:message code="user.passwordForm" /></label>								
				<form:password maxlength="20" path="passwordForm" size="20" />
				<form:errors path="passwordForm" cssClass="alert alert-error"/>
			</div>
	
			<div>
				<label for="confirmPasswordForm"><spring:message code="user.confirmPasswordForm" /></label>								
				<form:password maxlength="20" path="confirmPasswordForm" size="20" />
				<form:errors path="confirmPasswordForm" cssClass="alert alert-error"  />
			</div>			
			
		</fieldset>
		
		<div>
			<input id="atualizar" type="submit"
				value="<spring:message code="user.save"/>" />
		</div>
	</form:form>
</body>

