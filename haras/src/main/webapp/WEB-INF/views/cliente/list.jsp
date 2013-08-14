<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<head>
<title>Cliente</title>

<c:url value="/clientes" var="recordsUrl" />
<c:url value="/cliente/form" var="addUrl" />
<c:url value="/cliente/" var="editUrl" />
<c:url value="/cliente/delete" var="deleteUrl" />

<script type='text/javascript'>
	$(function() {
		$("#grid").jqGrid({
			url : '${recordsUrl}',
			datatype : 'json',
			mtype : 'GET',
			colNames : [ '', 'Id', 'Nome','CPF','CNPJ', 'Data criação','Data alteração','Usuário Ciação','Usuário atualização'],
			colModel : [			

			{	name : 'id',index : 'id',width : 30,align : "center",editable : true,formatter : 'RESTfulLink',formatoptions : {prefixo : 'cliente/',sufixo : '/show'},},
			{	name : 'id',index : 'id' /*,width : 100*/},
			{	name : 'nome',index : 'nome'}, 			
			{   name : 'cpf',index : 'cpf'},
			{   name : 'cnpj',index : 'cnpj'},		
			{	name : 'dateCreation',index : 'dateCreation'},
			{	name : 'dateModify',index : 'dateModify'},
			{	name : 'userCreation',index : 'userCreation'},
			{	name : 'userModify',index : 'userModify'} ,			
			
			],
			postData : {},
			rowNum : 10,
			rowList : [ 10, 20, 40, 60 ],
			height : 300,
			autowidth : true,
			rownumbers : true,
			pager : '#pager',
			sortname : 'nome',
			viewrecords : true,
			sortorder : "asc",
			caption : "Registros",
			emptyrecords : "Sem registros",
			loadonce : false,
			loadComplete : function() {

			},
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false,
				cell : "cell",
				id : "id"
			}
		});
		
		$("#grid").jqGrid('navGrid','#pager',
				{edit:false, add:false, del:false, search:false},
				{}, {}, {}, 
				{ 	// search
					sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
					closeOnEscape: true, 
					multipleSearch: true, 
					closeAfterSearch: true
				}
		);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Adicionar", 
					buttonicon:"ui-icon-plus", 
					onClickButton: addRow,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Editar", 
					buttonicon:"ui-icon-pencil", 
					onClickButton: editRow,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
		$("#grid").navButtonAdd('#pager',
			{ 	caption:"Excluir", 
				buttonicon:"ui-icon-trash", 
				onClickButton: deleteRow,
				position: "last", 
				title:"", 
				cursor: "pointer"
			} 
		);		
		
		$.extend($.fn.fmatter, {
			RESTfulLink : function(cellvalue, options, rowdata) {
		
				
				var prefixo = options.colModel.formatoptions.prefixo;
				var sufixo = options.colModel.formatoptions.sufixo;
				if (typeof prefixo == 'string') {
					if (typeof sufixo == 'string') {					
						return '<a href="'+prefixo + cellvalue + sufixo   +'">' + '<span class="ui-icon ui-icon-search">' + '</a>';						
					}
					else{
						return '<a href="'+prefixo + cellvalue +'">' + '<span class="ui-icon ui-icon-search">' + '</a>';
					}					
				} else {
					return cellvalue;
				}
			}
		});		
	});

	function addRow() {
		window.location = '${addUrl}';
	} 


	function editRow() {
		var row = $('#grid').jqGrid('getGridParam','selrow');		

   		
		// Get the currently selected row
		var row = $('#grid').jqGrid('getGridParam','selrow');
		
		if( row != null ) {
			window.location = '${editUrl}' + row + '/form';			
			}
		else {
			$('#msgbox').text('Você deve selecionar um registro primeiro!');
			$('#msgbox').dialog( 
					{	title: 'Erro',
						modal: true,
						buttons: {"Ok": function()  {
							$(this).dialog("close");} 
						}
					});
		}
	}
	
	function deleteRow() {
		// Get the currently selected row
	    var row = $('#grid').jqGrid('getGridParam','selrow');
	    // A pop-up dialog will appear to confirm the selected action
		if (row != null) {	    
		$('#msgbox').text(
						'Você tem certeza que irá excluir este registro?');
				$('#msgbox').dialog({
					title : 'Atenção',
					modal : true,
					buttons : {
						"Excluir" : function() {
							$(this).dialog("close");
				$.ajax({
					url:'${deleteUrl}' + row ,
					type: 'DELETE',
					success : function(data) {
						$('#msgbox').text('Registro excluido com sucesso');
						$('#msgbox').dialog( 
							{	title: 'Sucesso',
								modal: true,
								buttons: {"Ok": function()  {
									$(this).dialog("close");} 
								}
							});													
					}					
				});
						},
						"Cancelar" : function() {
							$(this).dialog("close");
						}
					}
				});
			}		
		else {
				$('#msgbox').text('Você deve selecionar um registro primeiro!');
				$('#msgbox').dialog({
					title : 'Erro',
					modal : true,
					buttons : {
						"Ok" : function() {
							$(this).dialog("close");
						}
					}
				});
			}
		}	
</script>
</head>
<body>	
		<div id='jqgrid'>
			<table id='grid' style="width: 400px"></table>
			<div id='pager'></div>
		</div>
		<div id='msgbox' title='' style='display:none'></div>
</body>
</html>