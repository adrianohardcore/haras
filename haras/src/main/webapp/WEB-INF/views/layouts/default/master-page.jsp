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
				<tiles:insertAttribute name="sub-menu" />
				<form:errors path="*" cssClass="alert alert-error" element="div" />
				<tiles:insertAttribute name="body" />
			</div>		 
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
	
	
	<script type="text/javascript">	
		$(function() {
			$('.nav-list li').each(function() {
				var href = $(this).find('a').attr('href');
				var current = window.location.pathname;				
				
				if (href === window.location.pathname) {									
					$(this).addClass('active');
				}
				//if (window.location.pathname.contains(href) && href != "/haras/" ){									
				if (window.location.pathname.indexOf(href) != -1 && href != "/haras/" ){
					$(this).addClass('active');
				}				
				//indexOf
			});
		});
	</script>
	
	<script type="text/javascript">	
		$(function() {
			$('.nav li').each(function() {
				var href = $(this).find('a').attr('href');
				var current = window.location.pathname;				
				
				if (href === window.location.pathname) {									
					$(this).addClass('active');
				}		
			});
		});
	</script>
</body>
</html>




