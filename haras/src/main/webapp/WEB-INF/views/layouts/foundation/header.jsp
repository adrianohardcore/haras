<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="logout" var="logoutUrl" />
<c:url value="login" var="loginUrl" />

<!-- Header and Nav -->
<div class="row">
	<div class="seven columns">
		<h1>
		SisHaras <small> Gerenciador de Haras. </small>		
	</h1>		
	</div>
	<div class="five columns">
		<ul class="link-list right">
			<li><a href="${homeUrl}">Início</a></li>
			<sec:authorize access="Authenticated">
				<li><a href="${logoutUrl}">Logout</a></li>
			</sec:authorize>
			<sec:authorize access="anonymous">
				<li><a href="${loginUrl}">Login</a></li>
			</sec:authorize>
		</ul>
	</div>
	<hr>	
</div>
<!-- End Header and Nav -->