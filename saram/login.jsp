<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
	<title>Inicio</title>
	<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/bootstrap.min.css" type="text/css" /> -->
	<link href="${pageContext.request.contextPath}/recursos/mor2.png" rel="apple-touch-icon" />
	<link href="${pageContext.request.contextPath}/recursos/mor2.png" rel="icon" sizes="192x192" />
	<link href="${pageContext.request.contextPath}/recursos/mor2.png" rel="icon" sizes="128x128" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/iui/iui.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/saram.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/iui/t/default/default-theme.css"  type="text/css"/>
	<script type="application/x-javascript" src="${pageContext.request.contextPath}/iui/iui.js"></script>

</head>

<body>

	<div class="toolbar">
		<h1 id="pageTitle">Acceso</h1>
	</div>

	<form id="screen1" title="Mobile Saram" class="panel" name="formname" action="acceso" method="post" selected="true">
		<fieldset>
			<div class="row">
				<label>Usuario</label>
				<input type="text" name="usuario" placeholder="Usuario">
			</div>
			<div class="row">
				<label>Contraseña</label>
				<input type="password" name="password" placeholder="Password"  autocomplete="off">
			</div>
		</fieldset>
		 <input type="submit" class="whiteButton" name="entrar1" value="Entrar" id="btnLeft"/>
	</form>
 <div class=footer>
	<!--	<a href=busquedaProducto.jsp?c_order_id="+ cOrderID +"&c_bpartner_id="+ c_bpartner_id +"&ad_user_id="+ adId +" target=_self class=redButton>Guardar</a> -->
		</div>
</body>
</html>
