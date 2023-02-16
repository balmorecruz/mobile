<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Busqueda de producto</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Busqueda de producto</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/iui/iui.css" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/recursos/saram.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/iui/t/default/default-theme.css"
	type="text/css" />
	<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/css/bootstrap-theme.css" crossorigin="anonymous">
<script type="application/x-javascript"
	src="${pageContext.request.contextPath}/iui/iui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"
	language="JavaScript1.2"></script>
<link href="${pageContext.request.contextPath}/css/calendar-blue.css"
	type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	history.forward();
</script>
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
</head>
<body>
	<div class="toolbar">
	<a href="busquedaPedidos?c_order_id=<%=request.getParameter("c_order_id")%>&ad_user_id=<%=request.getParameter("ad_user_id")%>&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>" class=blueButtonRegresar>Regresar</a>
		<h1>Mobile SARAM</h1>
	</div>

	<form id="screen1" title="Mobile Saram" class="panel" name="formname"
		action="${pageContext.request.contextPath}/busquedaProductos
	?c_order_id=<%=request.getParameter("c_order_id")%>&ad_user_id=<%=request.getParameter("ad_user_id")%>
	&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>"
		method="post" selected="true">
		<fieldset>
			<div class="row">
				<label>Nombre</label> <input type="hidden" name="ad_user_id"
					value="<%=request.getParameter("ad_user_id")%>"> <input
					type="hidden" name="c_bpartner_id"
					value="<%=request.getParameter("c_bpartner_id")%>"> <input
					type="text" name="name" style="text-transform: uppercase;"
					placeholder="NOMBRE DE PRODUCTO"  autocomplete="off">
			</div>
			<div class="row">
				<label>Codigo</label> <input type="text" name="mProductID"
					style="text-transform: uppercase;" placeholder="CODIGO DE PRODUCTO" autocomplete="off">
			</div>
			<input id="ad_user_id"
				value="<%=request.getParameter("ad_user_id")%>" name="ad_user_id"
				type="hidden" disabled="true" />
		</fieldset>
		<div class="alert alert-danger">ATENCION: <%=request.getParameter("razonable")%>, favor hacer otro pedido o borrar un producto.</div>
		<input type="submit" class="whiteButton" name="buscar" value="Buscar"
			id="btnLeft" />
	</form>
	<!--<div class=footer>-->
		<!-- <a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>" class=blueButton>Regresar</a> -->
	<!--	<a href=busquedaProducto.jsp?c_order_id="+ cOrderID +"&c_bpartner_id="+ c_bpartner_id +"&ad_user_id="+ adId +" target=_self class=redButton>Guardar</a> -->
		<!--</div>-->
</body>
</html>