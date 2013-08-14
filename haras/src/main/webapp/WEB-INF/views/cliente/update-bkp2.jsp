<%@page import="br.com.adrianohardcore.model.Cliente"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<head>
<title><spring:message code="cliente.update" /></title>
<script type="text/javascript">

	function adicionar() {		
		$('#adicionaEndereco').show();	
		$("#cliente").val("${id}"); 
	};
	
	$(document).ready(function() {		
		$('#adicionaEndereco input').each(function(){
			  $(this).val('');
			  $("#cliente").val("${id}"); 
		});
		
		//$('#adicionaEndereco').hide();
	});
	
	
</script>


</head>
<body>
	<c:url var="contador" value="0" />
	<c:url var="urlCliente" value="/cliente" />
	<c:url var="urlEndereco" value="/cliente_endereco" />
	<form:form action="${urlCliente}" method="PUT" modelAttribute="cliente">
	<form:errors path="*" cssClass="alert alert-error" element="div" /> 
	
		<div>
			<form:hidden path="id" />
		</div>		
		<div>
			<label for="nome"><spring:message code="cliente.nome" /></label>		
			<form:input maxlength="50" path="nome" size="50" />
			<form:errors path="nome" cssClass="alert alert-error" element="div" />
		</div>
		
		<div>
			<label for="cpf"><spring:message code="cliente.cpf" /></label>		
			<form:input maxlength="11" path="cpf" size="11" />			
			<form:errors path="cpf" cssClass="alert alert-error" element="div" />
		</div>
		
		<div>
			<label for="cnpj"><spring:message code="cliente.cnpj" /></label>		
			<form:input maxlength="14" path="cnpj" size="14" />			
			<form:errors path="cnpj" cssClass="alert alert-error" element="div" />
		</div>	
		
			<fieldset>
		<legend>Endereços</legend>


			<table id="tabela" class="table table-bordered">
				<tr>
					<th>Seq</th>
					<th>Id</th>					
					<th>Endereço</th>
					<th>Numero</th>
					<th>Complemento</th>
					<th>Bairro</th>
					<th>Cidade</th>
					<th>UF</th>
					<th>País</th>
				</tr>
				<c:forEach items="${cliente.enderecos}" var="endereco" varStatus="status">
					<tr>						
						<td>${status.count}</td>
						<td>${endereco.id}</td>
						<td>${endereco.endereco}</td>
						<td>${endereco.numero}</td>
						<td>${endereco.complemento}</td>
						<td>${endereco.bairro}</td>
						<td>${endereco.cidade}</td>
						<td>${endereco.uf}</td>
						<td>${endereco.pais} </td>		
										
					</tr>				
				</c:forEach>		
			</table>			
		</fieldset>	
		
			
		<div id="adicionaEndereco">	
		<form:errors path="*" cssClass="alert alert-error" element="div" />	
			<fieldset>
			<legend>Adicionar endereço</legend>		
			<form:hidden path="enderecos[0].id" />	
 			<form:hidden id="cliente" path="enderecos[0].cliente.id" />				
			<div>
				<label for="enderecos[0].endereco">Endereço</label>		
				<form:input maxlength="50" path="enderecos[0].endereco" size="50"/>				
				<form:errors path="enderecos[0].endereco" cssClass="alert alert-error" element="div" />
			</div>
			<div>
				<label for="enderecos[0].numero">Numero</label>		
				<form:input maxlength="50" path="enderecos[0].numero" size="50" />
				<form:errors path="enderecos[0].numero" cssClass="alert alert-error" element="div" />
			</div>
			<div>
				<label for="enderecos[0].complemento">Complemento</label>		
				<form:input maxlength="50" path="enderecos[0].complemento" size="50" />
				<form:errors path="enderecos[0].complemento" cssClass="alert alert-error" element="div" />
			</div>		
			<div>
				<label for="enderecos[0].bairro">Bairro</label>		
				<form:input maxlength="50" path="enderecos[0].bairro" size="50" />
				<form:errors path="enderecos[0].bairro" cssClass="alert alert-error" element="div" />
			</div>
			<div>
				<label for="enderecos[0].cidade">Cidade</label>		
				<form:input maxlength="50" path="enderecos[0].cidade" size="50" />
				<form:errors path="enderecos[0].cidade" cssClass="alert alert-error" element="div" />
			</div>	
			<div>
				<label for="enderecos[0].uf">UF</label>		
				<form:input maxlength="50" path="enderecos[0].uf" size="50" />
				<form:errors path="enderecos[0].uf" cssClass="alert alert-error" element="div" />
			</div>				
			<div>
				<label for="enderecos[0].pais">País</label>		
				<form:input maxlength="50" path="enderecos[0].pais" size="50" />
				<form:errors path="enderecos[0].pais" cssClass="alert alert-error" element="div" />
			</div>		
		</fieldset>
				
		</div>
		<div>
		<p>
			<input type="button" value="Adicionar Endereço" onclick="adicionar();"> 			
			<input type="submit" value="Salvar endereço" />
		</p>
		</div>				
	</form:form>		
</body>

