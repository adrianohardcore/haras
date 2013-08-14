<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<head>
<title><spring:message code="role.create" /></title>
</head>
<body>
	<c:url var="url" value="/role" />
	<form:form action="${url}" method="POST" modelAttribute="role">
		<div>
			<label for="roleName"><spring:message code="role.roleName" /></label>
			<form:errors path="roleName" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:input maxlength="50" path="roleName" size="50" />
		</div>

		<br />
		<div>
			<input type="submit" value="<spring:message code="role.save"/>" />
		</div>


	</form:form>

</body>

