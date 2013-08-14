<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/user" var="list" />
<c:url value="/user/form" var="form" />

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="${list}">Usuários</a>
    <ul class="nav">
      <li><a href="${list}">Listar</a></li>
      <li><a href="${form}">Adicionar</a></li>      
    </ul>
  </div>
</div>