<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<head>
<tiles:insertAttribute name="head" />
</head>
<body>
	<div class="container-narrow">
		<tiles:insertAttribute name="header" />
		<hr>
		<div class="row-fluid span12">
			<div class="span3">		
				<tiles:insertAttribute name="menu" />
			</div>
			<div class="span9">
				<tiles:insertAttribute name="body" />
			</div>		
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
	    
<%--     <script src='<c:url value="/resources/bootstrap/js/jquery.js"/>'></script> --%>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-transition.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-alert.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-modal.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-dropdown.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-scrollspy.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-tab.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-tooltip.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-popover.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-button.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-collapse.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-carousel.js"/>'></script>
    <script src='<c:url value="/resources/bootstrap/js/bootstrap-typeahead.js"/>'></script>		
</body>
</html>




