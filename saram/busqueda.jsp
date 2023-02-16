<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>
			Pedido
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
		<title>Inicio</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/iui/iui.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/saram.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/iui/t/default/default-theme.css"  type="text/css"/>
		<script type="application/x-javascript" src="${pageContext.request.contextPath}/iui/iui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js" language="JavaScript1.2"></script>
		<link href="${pageContext.request.contextPath}/css/calendar-blue.css" type="text/css" rel="stylesheet"/>
		<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;"/>
	</head>
	<body>
	
	<div class="toolbar">	
	<a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>" class=blueButtonRegresar>Regresar</a>
		<h1 id="pageTitle"></h1>
	</div>
	
    <div class="toolbar">
		<a href="tipo_pedido.jsp?ad_user_id=<%=request.getParameter("ad_user_id")%>"
			class=blueButtonRegresar>Regresar</a>
		<h1 id="pageTitle">Cliente</h1>
		<!-- <a
			id="previousButton" target="_self" href="pedido?usuario=0"
			class="button"> Atras </a> -->
	</div>
	<form id="screen1" title="Mobile Saram" class="panel" name="formname" action="${pageContext.request.contextPath}/busqueda?ad_user_id=<%=request.getParameter("ad_user_id")%>
	&m_warehouse_id=<%=request.getParameter("m_warehouse_id")%>" method="post" selected="true">
	   <fieldset>
			<div class="row">
				<label>Nombre</label>
				<input type="text" name="name" placeholder="nombres" style="text-transform:uppercase;"  autocomplete="off">
			</div>
			<div class="row">
				<label>Dui</label>
				<input type="text" name="taxid" placeholder="DUI CON GUIONES" style="text-transform:uppercase;" >
			</div>
			<div class="row">
				<label>Nit</label>
				<input type="text" name="value" placeholder="NIT CON GUIONES" style="text-transform:uppercase;" >
			</div>
			<input id="ad_user_id"
					value="<%=request.getParameter("ad_user_id")%>" name="ad_user_id"
					type="hidden" disabled="true" />
		</fieldset>
		 <input type="submit" class="whiteButton" name="buscar" value="Buscar" id="btnLeft"/>
    </form>

	</body>
</html>