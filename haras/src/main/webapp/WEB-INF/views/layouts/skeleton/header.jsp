<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="logout" var="logoutUrl" />
<c:url value="login" var="loginUrl" />


<div>
<div class="twelve columns">
	<h1 class="remove-bottom" style="margin-top: 40px">SisHaras</h1>
	<h5>Gerenciador de Haras.</h5>
</div>

<div class="four columns">
	<ul>
		<li><a href="${homeUrl}">Início</a></li>
			<sec:authorize access="Authenticated">
				<li><a href="${logoutUrl}">Logout</a></li>
			</sec:authorize>
			<sec:authorize access="anonymous">
				<li><a href="${loginUrl}">Login</a></li>
			</sec:authorize>
	</ul>
</div>
<hr />
</div>