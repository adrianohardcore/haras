<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="user" var="userUrl" />
<c:url value="role" var="roleUrl" />
<c:url value="logout" var="logoutUrl" />
<c:url value="login" var="loginUrl" />

<!-- Nav Sidebar -->
<!-- This is source ordered to be pulled to the left on larger screens -->
<div class="three panel columns pull-nine">
	<sec:authorize access="Authenticated">
		<h4>
		<span> <%=SecurityContextHolder.getContext().getAuthentication().getName()%></span>
		</h4>
	</sec:authorize>

	<ul class="side-nav">
		<li><a href="${homeUrl}">Início</a></li>
		<sec:authorize access="hasRole('ADMIN')">
			<li><a href="${userUrl}">Usuários</a></li>
			<li><a href="${roleUrl}">Regras de acesso</a></li>
		</sec:authorize>
		<sec:authorize access="Authenticated">
			<li><a href="${logoutUrl}">Logout</a></li>
		</sec:authorize>
		<sec:authorize access="anonymous">
			<li><a href="${loginUrl}">Login</a></li>
		</sec:authorize>
	</ul>
</div>