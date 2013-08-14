<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<head>
<title><spring:message code="user.create" /></title>
</head>
<body>
	<c:url var="url" value="/user" />
	<form:form action="${url}" method="POST" modelAttribute="user">
		<%--<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<form:hidden path="password"/>
		<div>
			<label for="name"><spring:message code="user.name" /></label>
			<form:errors path="name" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:input maxlength="50" path="name" size="50" />
		</div>

		<div>
			<label for="userName"><spring:message code="user.username" /></label>
			<form:errors path="userName" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:input maxlength="50" path="userName" size="50" />
		</div>

		<div>
			<label for="email"><spring:message code="user.email" /></label>
			<form:errors path="email" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:input maxlength="50" path="email" size="50" />
		</div>

		<div>
			<label for="passwordForm"><spring:message code="user.passwordForm" /></label>
			<form:errors path="passwordForm" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:password maxlength="20" path="passwordForm" size="20" />
		</div>

		<div>
			<label for="confirmPasswordForm"><spring:message code="user.confirmPasswordForm" /></label>
			<form:errors path="confirmPasswordForm" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:password maxlength="20" path="confirmPasswordForm" size="20" />
		</div>

		<br />
		<div>
			<input type="submit" value="<spring:message code="user.save"/>" />
		</div>
	</form:form>
</body>