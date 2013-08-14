<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<head>
<title>Usuários Datatables</title>

<c:url value="/userlist" var="recordsUrl" />
<c:url value="/users/create" var="addUrl" />
<c:url value="/user/" var="editUrl" />
<c:url value="/users/delete" var="deleteUrl" />

<script type='text/javascript'>
	$(function() {
		$("#grid").jqGrid({
			url : '${recordsUrl}',
			datatype : 'json',
			mtype : 'GET',
			colNames : [ '','Id', 'Nome', 'Usuário', 'Email'],
			colModel : [
			            
			//name: 'myac', width:80, fixed:true, sortable:false, resize:false, formatter:'actions', formatoptions:{keys:true},
				
			{						
				name:'id',
				index:'id',
				width:30,
				align:"center",
				editable: true,
				formatter:'RESTfulLink',
				formatoptions:{
					prefixo:'user/'
					,sufixo:'/form'
				},					
			}, 
			
			
			
			{				
				name : 'id',
				index : 'id',
				//width : 55,
				editable : false,
				editoptions : {
					readonly : true,
					size : 10
				},
				hidden : false
			}, {
				name : 'name',
				index : 'name',
				//width : 100,
				editable : true,
				editrules : {
					required : true
				},
				editoptions : {
					size : 10
				}
			}, {
				name : 'userName',
				index : 'userName',
				//width : 100,
				editable : true,
				editrules : {
					required : true
				},
				editoptions : {
					size : 10
				},
				edittype : 'password',
				hidden : false
			},			
			 {
				name : 'email',
				index : 'email',
				//width : 100,
				editable : true,
				editrules : {
					required : true
				},
				editoptions : {
					size : 10
				}	
			} ],
			postData : {},
			rowNum : 10,
			rowList : [ 10, 20, 40, 60 ],
			height : 300,
			autowidth : true,			
			rownumbers : true,
			pager : '#gridpager',
			sortname : 'name',
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

		$("#grid").jqGrid('navGrid', '#gridpager', {
			edit : false,
			add : false,
			del : false,
			search : false
		}, {}, {}, {}, { // search
			sopt : [ 'cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : true,
			closeAfterSearch : true
		});

		// Toolbar Search
// 		$("#grid").jqGrid('filterToolbar', {
// 			stringResult : true,
// 			searchOnEnter : true,
// 			defaultSearch : "cn"
// 		});
		
		

	$.extend($.fn.fmatter, {
			RESTfulLink : function(cellvalue, options, rowdata) {
		
				
				var prefixo = options.colModel.formatoptions.prefixo;
				var sufixo = options.colModel.formatoptions.sufixo;
				if (typeof prefixo == 'string') {
					if (typeof sufixo == 'string') {					
						return '<a href="'+prefixo + cellvalue + sufixo   +'">' + '<span class="ui-icon ui-icon-pencil">' + '</a>';						
					}
					else{
						return '<a href="'+prefixo + cellvalue +'">' + '<span class="ui-icon ui-icon-pencil">' + '</a>';
					}					
				} else {
					return cellvalue;
				}
			}
		});
	
	});
</script>
</head>
<body>
	<div id='jqgrid'>
		<table id='grid'></table>
		<div id='gridpager'></div>
	</div>
</body>
</html>