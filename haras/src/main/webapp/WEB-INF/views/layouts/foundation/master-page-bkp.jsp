<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/resources/css/styles.css"/>' />
</head>
<body>
	<div>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<sec:authorize access="anonymous">
		</sec:authorize>
		<sec:authorize access="hasRole('ADMINISTRADOR')">
		</sec:authorize>
		<sec:authorize access="hasRole('USUARIO')">
		</sec:authorize>
	</div>
	<div>
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>