<%@page import="sun.invoke.empty.Empty"%>
<%@page contentType="text/html" import="java.util.*"%>
<%@page contentType="text/html" import="java.util.Date"%>
<%@page contentType="text/html" import="java.util.Calendar"%>
<%@page contentType="text/html" import="org.saram.accesos.MWareHouse_X"%>
<%@page contentType="text/html" import="org.saram.accesos.COrder_X"%>
<%@page contentType="text/html" import="org.saram.modelo.COrder"%>
<%@page contentType="text/html" import="org.saram.accesos.CBPartner_X"%>
<%@page contentType="text/html" import="org.saram.modelo.CBPartner"%>
<%@page contentType="text/html" import="org.saram.modelo.MWareHouse"%>
<%@page contentType="text/html" import="org.saram.accesos.sqlHQL_X"%>
<%@page contentType="text/html"
	import="org.saram.accesos.CBPartnerLocation_X"%>
<%@page contentType="text/html"
	import="org.saram.modelo.CBPartnerLocation"%>
<%@page contentType="text/html"
	import="javax.servlet.http.HttpServletRequest"%>
<%!Integer CLocationID = 0;%>
<%!Integer DropShopLocation = 0;%>
<%!String documentNo;%>
<%!Integer CBPartnerID;%>
<%!String nameCBPartner;%>
<%!String orderDate;%>
<%!String CBPartnerName;%>
<%!String esEntregaLocal;%>
<%!Integer MWareHouseID;%>
<%!Float totalLine;%>
<%!Float grandTotal;%>
<%!String tipodepago; %>
<%!String descripcion; %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Pedido</title>
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
	href="${pageContext.request.contextPath}/css/bootstrap-theme.css" crossorigin="anonymous">
	<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/css/bootstrap-theme.css" crossorigin="anonymous">
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
$(document).ready(function($) {
    $(".table-row").click(function() {
        window.document.location = $(this).data("href");
    });
});
</script>
</head>
<body>
	<%
		poblarJSP(request.getParameter("c_order_id"));
	%>
	<%!private void poblarJSP(String request) {
		try {
			COrder co = new COrder();
			COrder_X cox = new COrder_X();
			CBPartner_X cbx = new CBPartner_X();
			CBPartner cb = new CBPartner();
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
			co = cox.buscarUno(Integer.parseInt(request));
			CLocationID = co.getC_bpartner_location_id();
			DropShopLocation = co.getDropship_location_id();
			documentNo = co.getDocumentno();
			CBPartnerID = co.getC_bpartner_id();
			cb = cbx.buscarUno(CBPartnerID);
			CBPartnerName = cb.getName();
			orderDate = df.format(co.getDateordered());
			MWareHouseID = co.getM_warehouse_id();
			totalLine = co.getTotallines();
			grandTotal = co.getGrandtotal();
			descripcion = co.getDescription();
			if (co.getC_paymentterm_id()!=null){
				if (co.getC_paymentterm_id()==1000011){
					tipodepago = "Credito";
				} else 
					tipodepago = "Contado";
			}
			
			if (co.getEsentregalocal().equals("N")){
				esEntregaLocal = "No";
					} else esEntregaLocal = "Si";
		} catch (Exception e) {
			System.out.println(e);
		}
	}%>
	<form id="screen1" title="Pedido" class="panel" name="formname"
		action="busquedaPedidosV?c_order_id=<%=request.getParameter("c_order_id")%>&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>"
		method="post" selected="true">	
		<fieldset>
			<div class="row">
				<label id="POReferenceL" for="POReferenceF"
					title="N&uacute;mero de referencia de de la transacci&oacute;n (Orden de Venta; Orden de Compra) de su Socio del Negocio)">
					Tipo Documento </label> <input id="POReferenceF" disabled="true"
					value="Pedido" name="documenttype" maxlength="20" type="text" />
			</div>
			<div class="row">
				<label id="POReferenceL" for="POReferenceF"
					title="N&uacute;mero de referencia de de la transacci&oacute;n (Orden de Venta; Orden de Compra) de su Socio del Negocio)">
					No. del Documento </label> <input id="documentno" value="<%=documentNo%>"
					disabled="true" name="documentno" maxlength="20" type="text" />
			</div>
			<div class="row">
				<label id="AD_Client_IDL"
					title="Compa&ntilde;&iacute;a para esta instalaci&oacute;n"
					class="mandatory" for="AD_Client_IDF"> Cliente: </label> <input
					id="C_BPartner_ID" value="<%=CBPartnerID%>" name="C_BPartner_ID"
					type="hidden" /> <input id="ad_user_id"
					value="<%=request.getParameter("ad_user_id")%>" name="ad_user_id"
					type="hidden" /> <input id="nameco" value="<%=CBPartnerName%>"
					name="nameco" type="text" disabled="true" />
			</div>
			<div class="row">
				<label id="DateOrderedL" for="DateOrderedF"
					title="Fecha de la orden"> Fecha requerida </label>
				<div class="fieldValue">
					<%
						java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
					%>
					<%
						String date = df.format(new Date());
					%>
					<input type="date" value="<%=date%>" id="DateOrdered"
						name="DateOrdered" disabled="true"/>
				</div>
			</div>
			<%--
			<div class="row">
				<label id="DatePromisedL" for="DatePromisedF"
					title="Fecha de promesa de la orden."> Fecha Prometida </label>
				<div class="fieldValue">
				 <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
				 <%String date = df.format(new Date()); %>
					<input type="date"  value = "<%=date %>" id="fechaOrden" />
				</div>
			</div>
			 --%>
			<div class="row">
				<label id="C_BPartner_Location_IDL" for="C_BPartner_Location_IDF"
					title="Identifica la direcci&oacute;n para este tercero">
					Direcci&oacute;n del Cliente </label> <select disabled="true" name="c_bpartner_location_id"
					id="c_bpartner_location_id">
					<%!private void menuLocaciones(javax.servlet.jsp.JspWriter out, Integer request) {
		try {
			CBPartnerLocation_X locImp = new CBPartnerLocation_X();
			Integer cb = 0;
			if (request != null) {
				cb = request;
			}
			List<CBPartnerLocation> loc = locImp.buscarTodos(cb);
			Integer i = 0;
			for (CBPartnerLocation l : loc) {
				i++;
				if ((l.getC_bpartner_location_id() - CLocationID) == 0) {
					out.println("<option value=\"" + l.getC_bpartner_location_id() + "\"selected>" + l.getName()
							+ "</option>");
				} else {
					out.println("<option value=\"" + l.getC_bpartner_location_id() + "\">" + l.getName() + "</option>");
				}
			}
			if (i == 0) {
				out.println("<option value=\"-1\" selected> No se le ha registrado una direccion</option>");
			}
		} catch (java.io.IOException e1) {
			System.out.println(e1);
		}
	}%>
					<%
						menuLocaciones(out, CBPartnerID);
					%>

					<%!private void menuLocacionesEnvio(javax.servlet.jsp.JspWriter out, Integer request) {
		try {
			CBPartnerLocation_X locImp2 = new CBPartnerLocation_X();
			Integer cb = 0;
			if (request != null) {
				cb = request;
				List<CBPartnerLocation> loc2 = locImp2.buscarTodos(cb);
				Integer i = 0;
				for (CBPartnerLocation l : loc2) {
					i++;
					if (DropShopLocation != null) {
						if ((l.getC_bpartner_location_id() - DropShopLocation) == 0) {
							out.println("<option value=\"" + l.getC_bpartner_location_id() + "\"selected>" + l.getName()
									+ "</option>");
						} else {
							out.println("<option value=\"" + l.getC_bpartner_location_id() + "\">" + l.getName()
									+ "</option>");
						}
					} else {
						if (l.getC_bpartner_location_id() == 0) {
							out.println("<option value=\"" + l.getC_bpartner_location_id() + "\"selected>" + l.getName()
									+ "</option>");
						} else {
							out.println("<option value=\"" + l.getC_bpartner_location_id() + "\">" + l.getName()
									+ "</option>");
						}
					}
				}
				if (i == 0) {
					out.println("<option value=\"-1\" selected> No se le ha registrado una direccion</option>");
				}
				System.out.println(esEntregaLocal);
				if (esEntregaLocal.equals("Si")){
					out.println("<option value=\"-1\" selected> Entrega en SARAM</option>");
				}
			}

		} catch (java.io.IOException e1) {
			System.out.println(e1);
		}
	}%>
				</select>
			</div>
		</fieldset>
		<h2>Entrega</h2>
		<fieldset>
			<div class="row">
				<label id="C_BPartner_Location_IDL" for="C_BPartner_Location_IDF"
					title="Identifica la direcci&oacute;n para este tercero">
					Direcci&oacute;n de Entrega </label> <select disabled="true" name=dropship_location_id
					id="dropship_location_id">
					<%
						menuLocacionesEnvio(out, CBPartnerID);
					%>
				</select>
			</div>
			<div class="row">
				<label id="M_Warehouse_IDL" for="M_Warehouse_IDF"
					title="Almac&eacute;n y punto de servicio"> Almac&eacute;n
				</label> <select disabled="true" name="m_warehouse_id">
					<%!private void menuAlmacen(javax.servlet.jsp.JspWriter out) {
		try {
			MWareHouse_X locImp = new MWareHouse_X();
			List<MWareHouse> loc = locImp.buscarTodos();
			Integer i = 0;
			for (MWareHouse l : loc) {
				i++;
				if ((l.getM_warehouse_id() - MWareHouseID) == 0) {
					out.println("<option value=\"" + l.getC_location_id() + "\"selected>" + l.getName() + "</option>");
				} else {
					out.println("<option value=\"" + l.getC_location_id() + "\">" + l.getName() + "</option>");
				}
			}
			if (i == 0) {
				out.println("<option value=\"-1\" selected> No se ha encontrado ningun almacen</option>");
			}
		} catch (java.io.IOException e1) {
			System.out.println(e1);
		}
	}%>
					<%
						menuAlmacen(out);
					%>
				</select>
			</div>
			<div class="row">
				<label id="POReferenceL" for="POReferenceF"
					title="Descripcion">
					Descripcion </label> <input id="description" disabled="true"
					value="<%=descripcion%>" name="description" maxlength="250" type="text" disabled="true"/>
			</div>
		<%-- 	
			<div class="row">
				<label id="C_BPartner_saram" for="C_BPartner_saram"
					title="Ingresa si el pedido sera en sucursal"> Entregado en
					sucursal</label> 
				<select name=esentregalocal id="esentregalocal" disabled="true">
					<option value="N" selected><%=esEntregaLocal%></option>
					<option value="S">Si</option>
				</select>
			</div>
			--%>
			<div class="row">
				<label id="C_BPartner_saram" for="C_BPartner_saram"
					title="Ingresa si el pedido sera en sucursal"> Tipo de pago</label> <select disabled="true" name=cpaymentterm id="cpaymentterm">
					<option value="1000011" selected><%=tipodepago%></option>
					<option value="1000001">Contado</option>
				</select>
			</div>
		</fieldset>
		<%--
		<h2>Facturación</h2>
		<fieldset>
			<div class="row">
				<label id="InvoiceRuleL" for="InvoiceRuleF"
					title="Frecuencia y m&eacute;todos de facturaci&oacute;n">
					Regla de Facturaci&oacute;n </label>
				<div class="fieldValue">Inmediato</div>
			</div>
			<div class="row">
				<label id="M_PriceList_IDL" for="M_PriceList_IDF"
					title="Identificador &uacute;nico de mi lista de precios">
					Lista de Precios </label>
				<div class="fieldValue">Mayorista</div>
			</div>
			<div class="row">
				<label id="SalesRep_IDL" for="SalesRep_IDF"
					title="Representante Comercial"> Representante Comercial </label>
				<div class="fieldValue">Asturias Flores, Juan Francisco</div>
			</div>
			<div class="row">
				<label id="C_PaymentTerm_IDL" for="C_PaymentTerm_IDF"
					title="Condiciones de pago de esta transacci&oacute;n">
					T&eacute;rmino de Pago </label>
				<div class="fieldValue">12 dias</div>
			</div>
		</fieldset>
		 --%>
		<!-- <h2>Estado</h2>
		<fieldset>
			<div class="row">
				<label id="TotalLinesL" for="TotalLinesF"
					title="Total de todas las l&iacute;neas del documento">
					Total de L&iacute;neas </label>
				<div class="fieldValue"><%=totalLine%></div>
			</div>
			<div class="row">
				<label id="GrandTotalL" for="GrandTotalF"
					title="Total del documento"> Gran Total </label>
				<div class="fieldValue"><%=grandTotal%></div>
			</div>
			<!-- <div class="row">
				<label id="DocStatusL" for="DocStatusF"
					title="El estado actual del documento"> Estado del
					Documento </label>
				<div class="fieldValue">Borrador</div>
			</div> -->
		<!--</fieldset>
		 -->
		<div>
			<input type="submit" class="whiteButton" name="lineapedido"
				value="VER PRODUCTOS" id="btnLeft" /> <!-- <input
				id="botonAlerta" type="button" class="greenButton"
				value="GUARDAR DATOS" onclick='mostrar()'> -->
			<!-- <input type="submit" class="greenButton" name="guardarPedido"
			value="Guardar" id="btnLeft" />x
			<input type="submit" class="redButton" name="EliminarPedido" id ="eliminarpedido"
				value="ELIMINAR PEDIDO" id="btnLeft" />pedido_m.jsp
		</div>


		<%!private void cpaymentterm(javax.servlet.jsp.JspWriter out, String request) {
		sqlHQL_X sqlHql = new sqlHQL_X();
		List value = sqlHql.buscarUno("select name from c_paymentterm where c_paymentterm_id= "
				+ "(select c_paymentterm_id from c_bpartner where c_bpartner_id =" + request + ")");
		try {
			if (value.get(0) != null) {
				out.println("<especial> <em>" + value.get(0).toString() + "</em></especial>");
			}
		} catch (java.io.IOException e1) {
			System.out.println(e1);
		}
	}%>
		<%!private void validaLimite(javax.servlet.jsp.JspWriter out, String request) {

	}%>
		<script type="text/javascript">
			function mostrar() {
				document.getElementById('ventana-flotante').className = 'mostrar';
				document.getElementById('alerta').style.display = 'block';
				document.getElementById('botonAlerta').style.display = 'none';
				document.getElementById('eliminarpedido').style.display = 'none';
				var theSelect = screen1.c_bpartner_location_id;
				var theSelectD = screen1.dropship_location_id;
				var limite = document.getElementById('limitecredito');
				if (theSelectD[theSelectD.selectedIndex].text.length > 0) {
					document.getElementById('btnLeft').style.display = 'block';
				} else {
					document.getElementById('btnLeft').style.display = 'none';
				}
				if (limite == "ok") {
					document.getElementById('btnLeft').style.display = 'block';
					document.getElementById('botonAlerta').style.display = 'block';
					document.getElementById('eliminarpedido').style.display = 'block';
				} else {
					document.getElementById('btnLeft').style.display = 'none';
					document.getElementById('botonAlerta').style.display = 'none';
					document.getElementById('eliminarpedido').style.display = 'none';
				}
			}
			function ocultar() {
				document.getElementById('ventana-flotante').className = 'oculto';
				document.getElementById('btnLeft').style.display = 'block';
				document.getElementById('botonAlerta').style.display = 'block';
				document.getElementById('eliminarpedido').style.display = 'block';
			}
		</script>
		<div id='ventana-flotante' class='oculto'>

			<div id='contenedor' align="center">

				<div class='contenido'>

					<div id="alerta" class="alert">
						<!-- 	<b>¿Está seguro que la siguiente información es correcta?</b><br>
			<br> Cliente:
			<especial> <em><%=CBPartnerName%></em></especial>
			<br> Direccion del cliente:
			<especial> <em><especial id="dircliente"></especial></em></especial>
			<br> Direccion de entrega:
			<especial> <em><especial id="direntrega"></especial></em></especial>
			<br> Termino de pago: <%cpaymentterm(out, request.getParameter("c_bpartner_id"));%>-->
						<%
							validaLimite(out, request.getParameter("c_bpartner_id"));
						%></br>
					</div>
				</div>

			</div>

		</div>
		<!--<div> <input type="submit" class="whiteButton" name="eliminar" disabled="true"	value="Eliminar" id="btnLeft" /></div> -->
	</form>
	<div class=footer>
	<!--	<a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>" class=blueButton>Regresar</a>
	<!--	<a href=busquedaProducto.jsp?c_order_id="+ cOrderID +"&c_bpartner_id="+ c_bpartner_id +"&ad_user_id="+ adId +" target=_self class=redButton>Guardar</a> -->
	</div>
	<div class="toolbar">
		<a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>"
			class=blueButtonRegresar>Menu principal</a>
		<h1 id="pageTitle">Pedido</h1>
		<!-- <a
			id="previousButton" target="_self" href="pedido?usuario=0"
			class="button"> Atras </a> -->
	</div>
</body>
</html>