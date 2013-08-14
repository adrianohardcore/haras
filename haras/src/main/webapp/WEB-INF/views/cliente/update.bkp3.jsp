<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<head>
<title><spring:message code="cliente.update" /></title>
<c:url value="/cliente/cliente-endereco" var="deleteUrl" />

<script type='text/javascript' src='<c:url value="/resources/jqgrid/js/jquery-1.9.0.min.js"/>'></script>

<script type="text/javascript">
</script>

//  $("a").on("click", function(event){
//  	alert('Alô!');
//  	});
	
// //$('tr').delegate('click', 'a.excluir', function(){
// $(document).delegate('click', 'a', function(){  	
// 	alert("eim");
// });


// $("#excluir").live("click", function(){ 
// 	alert('Eim ');

// 	var row = $(this).parents('tr:first');
// 	$.ajax({
// 		type: "DELETE",
// 		url:'${deleteUrl}' + id ,				
// 		success: function(){
// 			$(row).remove(); //Remove the row containing the image element
// 		}
// 	});


// 	return false;
// });

//   function ExcluirEndereco(id) {
// 	  alert('Vai excluir?' + id );
// 	  var row = $(this).parents('tr:first');
// 	  $(row).remove(); //Remove the row containing the image element
//   }


//   $( "#excluir" ).click(function() {
// 	  alert( "Handler for .click() called." );
// 	  });


	
// 	$(document).ready(function() {		
// 		var index = ${fn:length(cliente.enderecos)};
// 		var id = ${id};		
// 		$("#adicionar").off("click").on("click", function() {
// 			$("#enderecoListContainer").after(				
// 				'<tr>'+					
// 					'<input type="hidden" name="enderecos[' + index + '].cliente.id" value="${id}" />'+
// 					'<td><input type="text" name="enderecos[' + index + '].endereco" class="input-small"/></td>'+
// 					'<td><input type="text" name="enderecos[' + index + '].numero" class="input-mini"/></td>'+
// 					'<td><input type="text" name="enderecos[' + index + '].complemento" class="input-small"/></td>'+
// 					'<td><input type="text" name="enderecos[' + index + '].bairro" class="input-small"/></td>'+
// 					'<td><input type="text" name="enderecos[' + index + '].cidade" class="input-small"/></td>'+
// 					'<td><input type="text" name="enderecos[' + index + '].uf" class="input-mini"/></td>'+
// 					'<td><input type="text" name="enderecos[' + index + '].pais" class="input-small"/></td>'+
// 					'<td><a href="#" onclick="excluir($(this).parent().parent().remove());" ><span class="ui-icon ui-icon-closethick"></span></a></td>'+
// 				'</tr>'
// 			);           
//            index++;
//            return false;
// 		});	
		
	});

	
	
	
// 	function deleteRow() {



// 		$.ajax({
// 			url:'${deleteUrl}' + id ,
// 			type: 'DELETE',
// 			success : function(data) {
// 				$('#msgbox').text('Registro excluido com sucesso');
// 				$('#msgbox').dialog( 
// 					{	title: 'Sucesso',
// 						modal: true,
// 						buttons: {"Ok": function()  {
// 							$(this).dialog("close");} 
// 						}
// 					});													
// 			},
							
// 		});		






		
// 		$('#msgbox').text('Você tem certesa que irá excluir este registro?');
// 		$('#msgbox').dialog({
// 			title : 'Exclusão',
// 			modal : true,
// 			buttons : {
// 				"Deletar" : function() {					
// 					row.remove();

// 					$.ajax({
// 						url:'${deleteUrl}' + id ,
// 						type: 'DELETE',
// 						success : function(data) {
// 							$('#msgbox').text('Registro excluido com sucesso');
// 							$('#msgbox').dialog( 
// 								{	title: 'Sucesso',
// 									modal: true,
// 									buttons: {"Ok": function()  {
// 										$(this).dialog("close");} 
// 									}
// 								});													
// 						}					
// 					});				
					
// 					$(this).dialog("close");
// 				}
// 				,"Cancelar" : function() {
// 					$(this).dialog("close");
// 				}
// 			}		
// 		});		
//	}	
//</script>


</head>
<body>
	<c:url var="contador" value="0" />
	<c:url var="urlCliente" value="/cliente" />
	<c:url var="urlEndereco" value="/cliente_endereco" />
	<form:form action="${urlCliente}" method="PUT" modelAttribute="cliente"
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
			<legend>Endereços</legend>


			<table id="tabela" class="table table-bordered">
				<thead>
					<tr>
						<th>Endereço</th>
						<th>Numero</th>
						<th>Complemento</th>
						<th>Bairro</th>
						<th>Cidade</th>
						<th>UF</th>
						<th>País</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody id="enderecoListContainer">
					<c:forEach items="${cliente.enderecos}" var="endereco"
						varStatus="status">
						<tr>
							<form:hidden path="enderecos[${status.index}].id" />
							<form:hidden path="enderecos[${status.index}].cliente.id"
								value="${id}" />
							<td><form:input maxlength="50"
									path="enderecos[${status.index}].endereco" size="2"
									cssClass="input-small" /></td>
							<td><form:input maxlength="10"
									path="enderecos[${status.index}].numero" cssClass="input-mini" /></td>
							<td><form:input maxlength="20"
									path="enderecos[${status.index}].complemento"
									cssClass="input-small" /></td>
							<td><form:input maxlength="20"
									path="enderecos[${status.index}].bairro" cssClass="input-small" /></td>
							<td><form:input maxlength="20"
									path="enderecos[${status.index}].cidade" cssClass="input-small" /></td>
							<td><form:input maxlength="2"
									path="enderecos[${status.index}].uf" cssClass="input-mini" /></td>
							<td><form:input maxlength="20"
									path="enderecos[${status.index}].pais" value=""
									cssClass="input-small" /></td>							
							<td>${endereco.id}</td>
<!-- 							<td><a id="excluir" title="Excluir cliente"  href="#"><span   class="ui-icon ui-icon-closethick"></span>Excluir</a></td> -->
							<td><a href="#" class="remove">Remove</a></td>  
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>

		<div>
			<p>
				<input type="button" value="Adicionar Endereço" id="adicionar">
				<input type="submit" value="Salvar endereço" id="submit" />
			</p>
		</div>
		<div id="msgbox"></div>
	</form:form>
</body>

