<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="/logout" var="logoutUrl" />
<c:url value="/login" var="loginUrl" />


<div class="masthead">
	<div class="btn-group pull-right">
		<a class="btn btn-info" href="${homeUrl}"><i
			class="icon-home icon-white"></i> Início</a>
		<sec:authorize access="Authenticated">
			<a class="btn btn-primary" href="#"><i class="icon-user icon-white"></i>
				<%=SecurityContextHolder.getContext().getAuthentication().getName()%></a>
			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
				href="#"><span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="#"><i class="icon-pencil"></i> Editar</a></li>
				<li><a href="${logoutUrl}"><i class="icon-off"></i> Logout</a></li>
			</ul>
		</sec:authorize>
	</div>
	<h3 class="muted">SysHaras</h3>
</div>



