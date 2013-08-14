<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<head>
<title>Novo Animal</title>
</head>
<body>
	<c:url var="url" value="/animal" />
	<form:form action="${url}" method="POST" modelAttribute="animal">
		<div>
			<label for="nome">Nome</label>
			<form:errors path="nome" cssClass="errors" cssStyle="color:red" />
			<br />
			<form:input maxlength="50" path="nome" size="50" />
		</div>

		<br />
		<div>
			<input type="submit" value="Salvar" />
		</div>

	</form:form>

</body>

