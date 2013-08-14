<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/animal" var="list" />
<c:url value="/animal/form" var="form" />

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="${list}">Cadastro de Animais</a>
    <ul class="nav" id="submenu">
      <li><a href="${list}">Listar</a></li>
      <li><a href="${form}">Adicionar</a></li>      
    </ul>
  </div>
</div>