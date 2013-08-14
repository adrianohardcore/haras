<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<head>
<title>Usuários Datatables</title>

<link href="<c:url value="/resources/datatables/css/demo_page.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/datatables/css/demo_table.css"/>"
	rel="stylesheet" type="text/css" />
</head>

<script type='text/javascript'
	src='<c:url value="/resources/datatables/js/jquery.js"/>'>	
</script>

<script type='text/javascript'
	src='<c:url value="/resources/datatables/js/jquery.dataTables.js"/>'>	
</script>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#example').dataTable({
			//"sDom": '<"filter"i>rt',	
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "userlist",
			"oLanguage" : {
			"sSearch" : "Pesquisar:"},
			"sPaginationType": "full_numbers",
			
			
			"aoColumns" : 
				[ 
				  {	"mData" : "id"},
				  {	"mData" : "name"},
				  {	"mData" : "userName"},
				  {	"mData" : "email"},
				  
				  
				],				
		});
	});
	
	
	
// 	$(document).ready(function() {
// 		var oTable = $('#example').dataTable({
// 			//"sDom": '<"filter"i>rt',			
// 			"aoColumns" : [ {
// 				"mData" : "id"
// 			}, {
// 				"mData" : "name"
// 			}, {
// 				"mData" : "userName"
// 			}, {
// 				"mData" : "email"
// 			} ],
// 			"bProcessing" : true,
// 			"bServerSide" : true,
// 			"sAjaxSource" : "userlist",
// 			"oLanguage" : {
// 				"sSearch" : "Pesquisar:"
// 			}
// 		});
// 	});
</script>



	<table cellpadding="0" cellspacing="0" border="0" class="display"
		id="example">
		<thead>
			<tr>
				<th width="15%">Editar</th>
				<th width="20%">Id</th>
				<th width="25%"><spring:message code="user.name" /></th>
				<th width="25%"><spring:message code="user.username" /></th>
				<th width="15%"><spring:message code="user.email" /></th>				
			</tr>
		</thead>
		<tbody>
			<tr>
		
				<td colspan="5" class="dataTables_empty">Dados do servidor</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th>Editar</th>
				<th width="20%">Id</th>
				<th><spring:message code="user.name" /></th>
				<th><spring:message code="user.username" /></th>
				<th><spring:message code="user.email" /></th>
			</tr>
		</tfoot>
	</table>

</body>
</html>