<%@page contentType="text/html" import="java.util.*"%>
<%@page contentType="text/html" import="java.util.Date"%>
<%@page contentType="text/html" import="java.util.Calendar"%>
<%@page contentType="text/html" import="org.saram.accesos.*"%>
<%@page contentType="text/html" import="org.saram.modelo.*"%>
<%@page contentType="text/html"
	import="javax.servlet.http.HttpServletRequest"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/calendar.js"
	language="JavaScript1.2"></script>
<link href="${pageContext.request.contextPath}/css/calendar-blue.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-theme.css"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-theme.css"
	crossorigin="anonymous">
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;" />

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('form')
				.submit(
						function() {
							$(':submit').attr('disabled', 'disabled');
							// trigger the colorbox thing here
							objQty = document.getElementById("QtyEntered");
							if (objQty.value != "") {
								obj = document.getElementById("procesando");
								obj.style.display = (obj.style.display == 'none') ? 'block'
										: 'block';
								document.getElementById("formulario").style.display = "none";
								document.getElementById("formulario2").style.display = "none";
								document.getElementById("formulario3").style.display = "none";
								document.getElementById("formulario4").style.display = "none";
							}
						});
	});
	history.forward();
</script>
<style>
.centrado {
	margin: auto;
	width: 50%;
}

.loader {
	border: 16px solid #f3f3f3; /* Light grey */
	border-top: 16px solid #3498db; /* Blue */
	border-radius: 50%;
	width: 120px;
	height: 120px;
	animation: spin 2s linear infinite;
}

P.blocktext {
	margin-left: auto;
	margin-right: auto;
	width: 6em
}

.procesando {
	display: none;
}

.formulario {
	display: block;
	border-style: none;
	border-color: #F26666;
}

.formulario2 {
	display: block;
	border-style: none;
	border-color: #F26666;
}

@keyframes spin { 0% {
	transform: rotate(0deg);
}
100%{transform:rotate(360deg);}
}
</style>
</head>
<script type="text/javascript">
	function bar() {
		document.getElementById('netolinea').value = document
				.getElementById('QtyEntered').value
				* document.getElementById('PriceEntered').value;
	}
</script>
<%!Float precio = new Float(0);%>
<%!String QtyEntered = "";%>
<%!String PriceEntered = "";%>
<%!String netolinea = "";%>
<%!Integer COrderID = 0;%>
<%!String mProductIDName = "";%>
<%!private void precioProducto(javax.servlet.jsp.JspWriter out, String idcol, String idcb, String idmp) {
		try {
			sqlHQL_X datos = new sqlHQL_X();
			String sql = "select coalesce(sum(d.pricelist),0) " + "from " + "m_pricelist a " + "left join "
					+ "c_bpartner b " + "on a.m_pricelist_id = b.m_pricelist_id " + " left join "
					+ " m_pricelist_version c " + "on c.m_pricelist_id = a.m_pricelist_id " + "left join "
					+ "m_productprice d " + "on  d.m_pricelist_version_id = c.m_pricelist_version_id "
					+ "where c.isactive = 'Y' and b.c_bpartner_id =" + idcb + " and d.m_product_id = " + idmp;
			List<Object> d = datos.buscarUno(sql);
			MProduct_X mpx = new MProduct_X();
			MProduct mpo = new MProduct();
			mpo = mpx.buscarUno(Integer.parseInt(idmp));
			COrderLine_X colx = new COrderLine_X();
			COrderLine col = new COrderLine();
			col = colx.buscarUno(Integer.parseInt(idcol));
			COrderID = col.getC_order_id();
			if (d.get(0) == null) {
				precio = new Float(0.0);
			} else {
				precio = Float.parseFloat(d.get(0).toString());
			}
			Integer v = col.getQtyentered().intValue();
			QtyEntered = v.toString();
			PriceEntered = col.getPriceentered().toString();
			netolinea = col.getLinenetamt().toString();
			mProductIDName = mpo.getValue() + " - " + mpo.getName();

		} catch (Exception e1) {
			System.out.println(e1);
		}
	}%>
<%
	precioProducto(out, request.getParameter("c_orderline_id"), request.getParameter("c_bpartner_id"),
			request.getParameter("m_product_id"));
%>
<body>
	<form title="Linea" method="post" selected="true" class="panel"
		action="validaproducto?c_order_id=<%=request.getParameter("c_order_id")%>
		&modo=2&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>"
		enctype="application/x-www-form-urlencoded">
		<fieldset>
			<div id="formulario">
				<!-- <div class="row">
				<label id="LineL"
					title="No. L&iacute;nea &uacute;nico para este documento"
					class="mandatory" for="LineF"> No. L&iacute;nea </label> <input
					id="m_product_id" value="<%=request.getParameter("m_product_id")%>"
					name="m_product_id" maxlength="22" type="text" />
				<input id="LineF" value="10" class="Cmandatory" name="Line"
					maxlength="22" type="number" />
			</div>
			 -->
				<div class="row">
					<label id="M_Product_IDL" for="M_Product_IDF"
						title="Producto; servicio o art&iacute;culo."> Producto </label><input
						id="ad_user_id" value="<%=request.getParameter("ad_user_id")%>"
						class="Cmandatory" name="ad_user_id" maxlength="22" type="hidden" /><input
						id="m_product_id"
						value="<%=request.getParameter("m_product_id")%>"
						name="m_product_id" maxlength="22" type="hidden" /><input
						id="c_orderline_id"
						value="<%=request.getParameter("c_orderline_id")%>"
						name="c_orderline_id" maxlength="22" type="hidden" /><input
						id="c_bpartner_id"
						value="<%=request.getParameter("c_bpartner_id")%>"
						class="Cmandatory" name="c_bpartner_id" maxlength="22"
						type="hidden" /> <input disabled="true" id="mProductIDName"
						value="<%=request.getParameter("m_product_id")%>" rows="10"
						name="mProductIDName" /> <input disabled="true"
						id="mProductIDName" value="<%=mProductIDName%>" rows="10"
						name="mProductIDName" />
				</div>
				<!--  
				<div class="row">
					<label id="C_Charge_IDL" for="C_Charge_IDF" title="Cargos adicionales del documento">
						Cargo
					</label>
					<select onchange="startUpdate(this);" id="C_Charge_ID" name="C_Charge_ID">
						<option value="-1">
							
						</option>
						
						<option value="100153">
							5105020923-Alquileres
						</option>
						<option value="100154">
							5105020924-Gas
						</option>
						<option value="100155">
							5105020925-Pollita de un dia
						</option>
						<option value="100156">
							5105020926-Otras Prestaciones al Personal
						</option>
						<option value="100157">
							5105020927-Combustibles de Vehiculos 
						</option>
						<option value="100158">
							5105020928-Mantenimientos de Vehiculos
						</option>
						<option value="100159">
							5105020929-Gastos varios
						</option>
					</select>
				</div>
				<div class="row">
					<label id="DescriptionL" for="DescriptionF" title="Descripci&oacute;n corta opcional del registro">
						Descripci&oacute;n
					</label>
				<input disabled="true" id="DescriptionF" value="Pedido gestionado desde el sistema mobil" rows="10" name="Description"/>
				</div>
-->
			</div>
		</fieldset>
		<div id="formulario2">
			<h2>Cantidades</h2>
		</div>
		<fieldset>
			<div id="formulario3">
				<div class="row">
					<label id="QtyEnteredL"
						title="La cantidad incorporada se basa en la UM seleccionada."
						class="mandatory" for="QtyEnteredF"> Cantidad </label> <input
						class="Cmandatory" onChange="return bar()" id="QtyEntered"
						name="QtyEntered" maxlength="22" value="<%=QtyEntered%>"
						onchange="startUpdate(this);" type="number" />
				</div>
				<div class="row">
					<label id="PriceEnteredL"
						title="Precio cargado - El Precio esta basado en la selecci&oacute;n de UM"
						class="mandatory" for="PriceEnteredF"> Precio ($)</label> <input
						class="Cmandatory" id="PriceEntered" name="PriceEntered"
						maxlength="|0" value="<%=precio%>" onchange="startUpdate(this);"
						disabled="true" type="number" />
				</div>
				<div class="row">
					<label id="LineNetAmtL"
						title="Total neto de la l&iacute;nea (Cantidad * Precio Actual) sin fletes ni cargos"
						class="readonly" for="LineNetAmtF"> Neto de L&iacute;nea </label>
					<div class="fieldValue">
						<input class="Cmandatory" id="netolinea" name="netolinea"
							value="<%=netolinea%>" disabled="true" type="number" />
					</div>
				</div>
				<div class="row" style="display: none">
					<label id="C_Tax_IDL" title="Identificador del Impuesto"
						class="mandatory" for="C_Tax_IDF"> Impuesto </label> <select
						disabled="true" id="C_Tax_ID" name="C_Tax_ID" class="Cmandatory">
						<option selected="selected" value="1000020">D&eacute;bito
							Fiscal</option>
						<option value="1000015">Standard</option>
					</select>
				</div>
				<!-- 
			<div class="row">
				<label id="QtyDeliveredL" title="Cantidad entregada"
					class="readonly" for="QtyDeliveredF"> Cantidad Entregada </label>
				<div class="fieldValue">0</div>
			</div>
			<div class="row">
				<label id="QtyReservedL" title="Cantidad reservada" class="readonly"
					for="QtyReservedF"> Cantidad Reservada </label>
				<div class="fieldValue">0</div>
			</div>
			<div class="row">
				<label id="QtyInvoicedL" title="Cantidad facturada" class="readonly"
					for="QtyInvoicedF"> Cantidad Facturada </label>
				<div class="fieldValue">0</div>
			</div>
			-->
		</fieldset>
		</div>
		<!-- <h2>Totales</h2>
		<fieldset>
			<div class="row">
				<label id="PriceActualL" title="Precio Actual" class="readonly"
					for="PriceActualF"> Precio Actual </label>
				<div class="fieldValue">0</div>
			</div>
			<div class="row">
				<label id="PriceListL" title="Precio de Lista" class="readonly"
					for="PriceListF"> Precio de Lista </label>
				<div class="fieldValue">0</div>
			</div>
			<div class="row">
				<label id="totalunidadesL" for="totalunidadesF" class="readonly">
					totalunidades </label>
				<div class="fieldValue" id="totalunidades" name="totalunidade">0</div>
			</div>
		</fieldset>
		 -->
		 <div class="alert alert-danger">ATENCION: favor seleccionar otra cantidad que no genere PROMOCION.</div>
		<div id="formulario4">
			<input type="submit" class="greenButton" name="guardarlineapedido"
				value="Guardar" id="btnLeft" /><input type="submit"
				class="redButton" name="Eliminar" value="Eliminar" id="btnLeft" />
		</div>
		<div id="procesando" class="procesando">
			´
			<p class="blocktext">Procesando...</p>
			<div class="centrado">
				</br>
				<div id="loader" class="loader"></div>
			</div>
		</div>
	</form>
	<!--<div class="toolbar">
		<h1 id="pageTitle"></h1>
		<!--<form id="screen1" title="Pedido" class="panel" name="formname"
		action="busquedaPedidos?ad_user_id=<%=request.getParameter("ad_user_id")%>&c_order_id=<%=request.getParameter("c_order_id")%>&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>"
		method="post" selected="true">
	
		<!-- <a id="previousButton" target="_self" href="#_self"
			class="button" type="submit"> Atras </a> 
			
			</form>-->
	</div>
	-->
	<div class="toolbar">
		<a
			href="busquedaPedidos?ad_user_id=<%=request.getParameter("ad_user_id")%>&&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>&&c_order_id=<%=COrderID%>"
			class=blueButtonRegresar>Regresar</a>
		<h1 id="pageTitle"></h1>
	</div>
</body>
</html>