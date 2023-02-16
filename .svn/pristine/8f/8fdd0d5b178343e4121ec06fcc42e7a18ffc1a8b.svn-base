<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Inicio</title>
<link href="${pageContext.request.contextPath}/recursos/mor2.png"
	rel="apple-touch-icon" />
<link href="${pageContext.request.contextPath}/recursos/mor2.png"
	rel="icon" sizes="192x192" />
<link href="${pageContext.request.contextPath}/recursos/mor2.png"
	rel="icon" sizes="128x128" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/iui/iui.css" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/recursos/saram.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/iui/t/default/default-theme.css"
	type="text/css" />
<script type="application/x-javascript"
	src="${pageContext.request.contextPath}/iui/iui.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<style>
.centrado {
	margin: auto;
	width: 50%;
}

P.blocktext {
	margin-left: auto;
	margin-right: auto;
	width: 6em
}

.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 120px;
    height: 120px;
    animation: spin 2s linear infinite;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
</style>
</head>

<body>
	
	<div class="toolbar">
		<h1>Pedido</h1>
	</div>
	<form  method="post" selected="true" class="panel" action="completarPedidoBoton?ad_user_id=<%=request.getParameter("ad_user_id")%>
	&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>&c_order_id=<%=request.getParameter("c_order_id")%>">
	<div id="procesando" class="procesando">
			<p class="blocktext"><b>Procesando</b></p>
			<div class="centrado">
				</br>
				<div id="loader" class="loader"></div>
			</div>
		</div>
	</form>
	<div class=footer>
		<!--	<a href=busquedaProducto.jsp?c_order_id="+ cOrderID +"&c_bpartner_id="+ c_bpartner_id +"&ad_user_id="+ adId +" target=_self class=redButton>Guardar</a> -->
	</div>
		<!--
response.sendRedirect("completarPedidoBoton?ad_user_id="+ request.getParameter("ad_user_id")
 				+"&c_bpartner_id=" + request.getParameter("c_bpartner_id") +"&c_order_id=" + request.getParameter("c_order_id") +"");
	-->
</body>
</html>
<script>
location.href ="completarPedidoBoton?ad_user_id=<%=request.getParameter("ad_user_id")%>&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>&c_order_id=<%=request.getParameter("c_order_id")%>";
</script>
