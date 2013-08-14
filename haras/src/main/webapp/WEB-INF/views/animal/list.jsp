<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/dojo-release-1.9.1/dojox/grid/resources/claroGrid.css"/>' />
<%-- <script src='<c:url value="/resources/dojo-release-1.9.1/dojo/dojo.js"/>'  data-dojo-config="async: true" ></script> --%>
<!-- <script src="/haras/resources/dojo-release-1.9.1/dojo/dojo.js"></script> -->


<script src="<c:url value="/resources/dojo-release-1.9.1/dojo/dojo.js"/>"  data-dojo-config="isDebug: true,parseOnLoad: true" ></script>
<title>Animais</title>
<script>
	dojo.require("dojo.store.JsonRest");
	dojo.require("dojo.store.Memory");
	dojo.require("dojo.store.Cache");
	dojo.require("dojox.grid.DataGrid");
	dojo.require("dojo.data.ObjectStore");
	dojo.ready(function() {
		myStore = dojo.store.Cache(dojo.store.JsonRest({
			target : "animais",
			sortParam : "sort"
		}), dojo.store.Memory());

		grid = new dojox.grid.DataGrid({
			store : dataStore = dojo.data.ObjectStore({
				objectStore : myStore
			}),
			structure : [
			{name:"ID", field:"id", width: "50px"},
			{
				name : "Nome",
				field : "nome",
				width : "200px",
				editable : true
			} ]
		}, "target-node-id"); // make sure you have a target HTML element with this id
		grid.startup();
		dojo.query("#save").onclick(function() {
			dataStore.save();
		});
	});
</script>





</head>
<body>

	<div id="target-node-id" style="height: 200px;"></div>

</body>
