<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<head>
<title><spring:message code="cliente.create" /></title>
<c:url value="/cliente/cliente-endereco" var="deleteUrl" />

<script type='text/javascript' src='<c:url value="/resources/jqgrid/js/jquery-1.9.0.min.js"/>'></script>

    <script>  
      $(document).ready(function(){
  		//adicionaLinha();		 
          $(document).on('click', 'a.add', function(){
        	  adicionaLinha();
        });        
          $(document).on('click', 'a.remove', function(){  
        	  deleteRow();  
          	$(this).closest('tr').remove();  
        });  
      });  
      
      var index = 0;
      function adicionaLinha(){    	  
    	  var row = 
        	  "<tbody id='enderecos'>"+
				"<tr>"+										
					"<td><input type='text' name='enderecos[" + index + "].endereco' class='input-small'/></td>"+
					"<td><input type='text' name='enderecos[" + index + "].numero' class='input-mini'/></td>"+
					"<td><input type='text' name='enderecos[" + index + "].complemento' class='input-small'/></td>"+
					"<td><input type='text' name='enderecos[" + index + "].bairro' class='input-small'/></td>"+
					"<td><input type='text' name='enderecos[" + index + "].cidade' class='input-small'/></td>"+
					"<td><input type='text' name='enderecos[" + index + "].uf' class='input-mini'/></td>"+
					"<td><input type='text' name='enderecos[" + index + "].pais' class='input-small'/></td>"+				
			        "<td><a href='#' class='add'>    <span class='ui-icon ui-icon-plusthick'>  </span>  </a></td>"+  
			        "<td><a href=''#' class='remove'> <span class='ui-icon ui-icon-closethick'> </span> </a></td>"+				
				"</tr>"+
				"</tbody>";
          $("#enderecos").closest('table').append(row); 
          index = index + 1;    	  
      };      
    </script>
    
</head>
<body>
	<c:url var="contador" value="0" />
	<c:url var="urlCliente" value="/cliente" />
	<c:url var="urlEndereco" value="/cliente_endereco" />
	<form:form action="${urlCliente}" method="POST" modelAttribute="cliente"
		id="cliente">
		<form:errors path="*" cssClass="alert alert-error" element="div" />

		<div>
			<form:hidden path="id" />
		</div>
		<div>
			<label for="nome"><spring:message code="cliente.nome" /></label>
			<form:input maxlength="50" path="nome" size="50" />
		</div>

		<div>
			<label for="cpf"><spring:message code="cliente.cpf" /></label>
			<form:input maxlength="11" path="cpf" size="11" />
		</div>

		<div>
			<label for="cnpj"><spring:message code="cliente.cnpj" /></label>
			<form:input maxlength="14" path="cnpj" size="14" />
		</div>
		<fieldset>
			<legend>Endere�os</legend>


			<table id="tabela" class="table table-bordered">
				<thead>
					<tr>
						<th>Endere�o</th>
						<th>Numero</th>
						<th>Complemento</th>
						<th>Bairro</th>
						<th>Cidade</th>
						<th>UF</th>
						<th>Pa�s</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody id="enderecos">

				</tbody>
			</table>
		</fieldset>
		<div>
			<p>
				<input type="button" value="Adicionar Endere�o" id="adicionar" onclick="adicionaLinha();">
				<input type="submit" value="Salvar endere�o" id="submit" />
			</p>
		</div>
		<div id="msgbox"></div>
	</form:form>
</body>

