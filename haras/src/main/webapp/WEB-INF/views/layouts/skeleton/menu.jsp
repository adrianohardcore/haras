<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="/user" var="userUrl" />
<c:url value="/role" var="roleUrl" />
<c:url value="/logout" var="logoutUrl" />
<c:url value="/login" var="loginUrl" />


<div class="three columns">
	<ul>
		<li><span> <%=SecurityContextHolder.getContext().getAuthentication().getName()%></span></li>
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