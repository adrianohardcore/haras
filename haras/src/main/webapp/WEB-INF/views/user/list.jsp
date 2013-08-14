<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/userlist" var="recordsUrl" />
<c:url value="/user/form" var="addUrl" />
<c:url value="/user/" var="editUrl" />
<c:url value="/user/" var="deleteUrl" />
<html>
<head>
	<title>Usu�rios Datatables</title>
	
	<script type='text/javascript'>
	$(function() {
		$("#grid").jqGrid({
		   	url:'${recordsUrl}',
			datatype: 'json',
			mtype: 'GET',
		   	colNames:['','Id', 'Nome', 'Usu�rio', 'Email'],
		   	colModel:[
		   	   	{name : 'id',index:'id',title:'show',width:30,align:"center",editable: false,formatter:'RESTfulLink',formatoptions:{prefixo:'user/',sufixo:'/show'},},		
				{name : 'id',index : 'id',/*width : 55,*/editable : false,editoptions : {readonly : true,size : 10},hidden : false},
				{name : 'name',index : 'name',/*width : 100,*/editable : true,editrules : {required : true},editoptions : {size : 10}},
				{name : 'userName',index : 'userName',/*width : 100,*/editable : true,editrules : {required : true},editoptions : {size : 10},edittype : 'password',hidden : false},			
			 	{name : 'email',index : 'email',/*width : 100,*/editable : true,editrules : {required : true},editoptions : {size : 10}}
			],
			
		   	postData: {},
			rowNum:10,
		   	rowList:[10,20,40,60],
		   	height: 300,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		   	sortname: 'name',
		    viewrecords: true,
		    sortorder: "asc",
		    caption:"Registros",
		    emptyrecords: "N�o h� registros",
		    loadonce: false,
		    loadComplete: function() { 	
	
		    },
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
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

		// Toolbar Search
		//$("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
		
		
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
			$('#msgbox').text('Voc� deve selecionar um registro primeiro!');
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
						'Voc� tem certeza que ir� excluir este registro?');
				$('#msgbox').dialog({
					title : 'Aten��o',
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
				$('#msgbox').text('Voc� deve selecionar um registro primeiro!');
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
		<table id='grid'></table>
		<div id='pager'></div>
	</div>
	<div id='msgbox' title='' style='display:none'></div>
</body>
</html>