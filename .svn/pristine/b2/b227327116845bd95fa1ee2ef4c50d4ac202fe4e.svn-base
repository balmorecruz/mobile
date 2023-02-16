<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Inicio</title>
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
</head>

<body>

	<div class="toolbar">
		<h1 id="pageTitle"></h1>
	</div>
	<form id="screen1" title="Pedido" class="panel" name="formname"
		action="${pageContext.request.contextPath}/busqueda.jsp?ad_user_id=<%=request.getParameter("ad_user_id")%>"
		method="post" selected="true">
		<fieldset>
			<div class="row">
				<label>Tipo de pedido</label> <select name="m_warehouse_id">
					<option value="10000071" selected>PECUARIO</option>
					<option value="10000072">MASCOTAS</option>
					<option value="1000005">MATERIA PRIMA</option>
					<%
						if (request.getParameter("ad_user_id").equals("1000013")) {
							out.println("<option value=\"1000044\">DIANA</option>");
						}	
					%>
					<%
						if (request.getParameter("ad_user_id").equals("1000032") 
								|| request.getParameter("ad_user_id").equals("1000016")) {
							out.println("<option value=\"10000073\">TROTEMOR</option>");
						}
						if (request.getParameter("ad_user_id").equals("1000010")) {
							out.println("<option value=\"10000074\">EXTRANJERO</option>");
						}		
					%>
					<!-- <option value="1000014" >HUEVOS</option>
					<option value="1000003" >INSUMOS</option>
					<option value="1000006" >PRODUCTO PROCESO</option> -->

				</select>
			</div>
		</fieldset>
		<input type="submit" class="whiteButton" name="tipo_pedido"
			value="CONTINUAR" id="btnLeft" />
	</form>
	<div class="toolbar">
		<a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>"
			class=blueButtonRegresar>Regresar</a>
		<h1 id="pageTitle">Tipo de pedido</h1>
		<!-- <a
			id="previousButton" target="_self" href="pedido?usuario=0"
			class="button"> Atras </a> -->
	</div>
	<div class=footer>
		<!--	<a href=busquedaProducto.jsp?c_order_id="+ cOrderID +"&c_bpartner_id="+ c_bpartner_id +"&ad_user_id="+ adId +" target=_self class=redButton>Guardar</a> -->
	</div>
</body>
</html>