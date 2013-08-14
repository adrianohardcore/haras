
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>





<div>
    <h2>OPS ...</h2>
    <p>${exception.message}</p>
    
    Exception: ${exception.message}.
  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
		${stackTrace} 
	</c:forEach>

</div>

