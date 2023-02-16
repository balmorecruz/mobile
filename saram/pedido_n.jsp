<%@page import="sun.invoke.empty.Empty"%>
<%@page import="org.saram.busquedas.utilerias"%>
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
<%@page contentType="text/html" import="org.saram.accesos.COrderLine_X"%>
<%@page contentType="text/html" import="org.saram.modelo.COrderLine"%>
<%@page contentType="text/html" import="org.saram.accesos.MProduct_X"%>
<%@page contentType="text/html" import="org.saram.modelo.MProduct"%>
<%@page contentType="text/html" import="java.text.DecimalFormat"%>
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
<%!Integer cOrderID;%>
<%!Integer adUserID;%>
<%!String orderDate;%>
<%!String CBPartnerName;%>
<%!String esEntregaLocal;%>
<%!Integer MWareHouseID;%>
<%!Float totalLine;%>
<%!Float grandTotal;%>
<%!String tipodepago;%>
<%!Float saldoFacturaOpenAmt = new Float(0.0); %>
<%!Float montoExcedido = new Float(0.0); %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Pedido</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
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
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
    $(function() {
        $('form').submit(function() {
            $(':submit').attr('disabled', 'disabled');
            // trigger the colorbox thing here
            obj = document.getElementById("procesando");
        	  obj.style.display = (obj.style.display=='none') ? 'block' : 'block';
        	  document.getElementById("formulario").style.display="none";
        });
        
    });
</script>
<link href="${pageContext.request.contextPath}/css/calendar-blue.css"
	type="text/css" rel="stylesheet" />
<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
<style>
.centrado{
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
.procesando{
	display: none;
}
.formulario{
	display: block;
	border-style: none;
	border-color: #F26666;
}
@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
</style>
</head>
<body>
	<div class="toolbar">

	</div>
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
			cOrderID = co.getC_order_id();
			adUserID = co.getAd_user_id();
			orderDate = df.format(co.getDateordered());
			MWareHouseID = co.getM_warehouse_id();
			totalLine = co.getTotallines();
			grandTotal = co.getGrandtotal();
			if (co.getC_paymentterm_id() != null) {
				if (co.getC_paymentterm_id() == 1000011) {
					tipodepago = "Credito";
				} else
					tipodepago = "Contado";
			}

			if (co.getEsentregalocal().equals("N")) {
				esEntregaLocal = "No";
			} else
				esEntregaLocal = "Si";
		} catch (Exception e) {
			System.out.println(e);
		}
	}%>
	<form id="screen1" title="Pedido" class="panel" name="formname"
		action="completarPedido?solicitud=1&ad_user_id=<%=request.getParameter("ad_user_id")%>
		&c_order_id=<%=request.getParameter("c_order_id")%>&c_bpartner_id=<%=request.getParameter("c_bpartner_id")%>"
		method="post" selected="true">
		<div id ="formulario">
		

			<%!private void validaLimite(javax.servlet.jsp.JspWriter out, String request) {
		CBPartner cb = new CBPartner();
		CBPartner_X cbx = new CBPartner_X();
		Float saldoFactura = new Float(0.0);
		Float saldoFacturaF = new Float(0.0);
		cb = cbx.buscarUno(Integer.parseInt(request));
		//sqlHQL_X sqlHql = new sqlHQL_X();
		Float value = new Float(0.0);
		//= sqlHql.buscarUno("select coalesce((so_creditlimit - so_creditused),0) from c_bpartner "
		//		+ "where c_bpartner_id =" + request //+ " and so_creditlimit <> 0"
		//		);
		sqlHQL_X saldos = new sqlHQL_X();
		DecimalFormat dfF = new DecimalFormat("###,###,##0.00");
		//	List<Object> pedidos = saldos.buscarUno("select coalesce(sum(grandtotal),0.0) from c_order " + "where c_bpartner_id = "
		//			+ cb.getC_bpartner_id() + " and docstatus = 'CO' and docaction = 'CL' and "
		//			+ "c_order_id not in (select c_order_id from c_invoice " + "where c_bpartner_id = "
		//			+ cb.getC_bpartner_id() + " and c_order_id is not null and docstatus = 'CO' and docaction = 'CL' )");
		String sql = "select sum(grandtotal) from (select * from (SELECT i.ad_org_id," + "i.ad_client_id,"
				+ "i.documentno," + "i.c_invoice_id," + "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx,"
				+ "i.dateinvoiced," + "i.dateacct," + "p.netdays,"
				+ "adempiere.paymenttermduedate(i.c_paymentterm_id, i.dateinvoiced) AS duedate,"
				+ "adempiere.paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced, adempiere.getdate()) AS daysdue,"
				+ "adempiere.adddays(i.dateinvoiced, p.discountdays) AS discountdate,"
				+ "round(i.grandtotal * p.discount / 100, 2) AS discountamt," + "i.grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, 0) AS openamt," + "i.c_currency_id," + "i.c_conversiontype_id,"
				+ "i.c_paymentterm_id," + "i.ispayschedulevalid," + "NULL AS c_invoicepayschedule_id,"
				+ "i.invoicecollectiontype," + "i.c_campaign_id," + "i.c_project_id," + "i.c_activity_id"
				+ "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_paymentterm p ON i.c_paymentterm_id = p.c_paymentterm_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, 0) <> 0 " + "	AND i.ispayschedulevalid <> 'Y' "
				+ "	AND (i.docstatus = ANY (ARRAY['CO', 'CL']))" + "	and i.c_bpartner_id=" + cb.getC_bpartner_id()
				+ "UNION" + " SELECT i.ad_org_id," + "i.ad_client_id," + "i.documentno," + "i.c_invoice_id,"
				+ "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx," + "i.dateinvoiced," + "i.dateacct,"
				+ "adempiere.daysbetween(ips.duedate, i.dateinvoiced) AS netdays," + "ips.duedate,"
				+ "adempiere.daysbetween(adempiere.getdate(), ips.duedate) AS daysdue," + "ips.discountdate,"
				+ "ips.discountamt," + "ips.dueamt AS grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) AS openamt," + "i.c_currency_id,"
				+ "i.c_conversiontype_id," + "i.c_paymentterm_id," + "i.ispayschedulevalid,"
				+ "ips.c_invoicepayschedule_id," + "i.invoicecollectiontype," + "i.c_campaign_id," + "i.c_project_id,"
				+ "i.c_activity_id" + "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) <> 0 "
				+ "	AND i.ispayschedulevalid = 'Y' AND (i.docstatus = ANY (ARRAY['CO', 'CL'])) "
				+ "	AND ips.isvalid = 'Y'" + "	and i.c_bpartner_id= " + cb.getC_bpartner_id()
				+ ") a where daysdue>0 and issotrx='Y' ) a";
		String sql2 = "select sum(openamt) from (select * from (SELECT i.ad_org_id," + "i.ad_client_id,"
				+ "i.documentno," + "i.c_invoice_id," + "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx,"
				+ "i.dateinvoiced," + "i.dateacct," + "p.netdays,"
				+ "adempiere.paymenttermduedate(i.c_paymentterm_id, i.dateinvoiced) AS duedate,"
				+ "adempiere.paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced, adempiere.getdate()) AS daysdue,"
				+ "adempiere.adddays(i.dateinvoiced, p.discountdays) AS discountdate,"
				+ "round(i.grandtotal * p.discount / 100, 2) AS discountamt," + "i.grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, 0) AS openamt," + "i.c_currency_id," + "i.c_conversiontype_id,"
				+ "i.c_paymentterm_id," + "i.ispayschedulevalid," + "NULL AS c_invoicepayschedule_id,"
				+ "i.invoicecollectiontype," + "i.c_campaign_id," + "i.c_project_id," + "i.c_activity_id"
				+ "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_paymentterm p ON i.c_paymentterm_id = p.c_paymentterm_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, 0) <> 0 " + "	AND i.ispayschedulevalid <> 'Y' "
				+ "	AND (i.docstatus = ANY (ARRAY['CO', 'CL']))" + "	and i.c_bpartner_id=" + cb.getC_bpartner_id()
				+ "UNION" + " SELECT i.ad_org_id," + "i.ad_client_id," + "i.documentno," + "i.c_invoice_id,"
				+ "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx," + "i.dateinvoiced," + "i.dateacct,"
				+ "adempiere.daysbetween(ips.duedate, i.dateinvoiced) AS netdays," + "ips.duedate,"
				+ "adempiere.daysbetween(adempiere.getdate(), ips.duedate) AS daysdue," + "ips.discountdate,"
				+ "ips.discountamt," + "ips.dueamt AS grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) AS openamt," + "i.c_currency_id,"
				+ "i.c_conversiontype_id," + "i.c_paymentterm_id," + "i.ispayschedulevalid,"
				+ "ips.c_invoicepayschedule_id," + "i.invoicecollectiontype," + "i.c_campaign_id," + "i.c_project_id,"
				+ "i.c_activity_id" + "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) <> 0 "
				+ "	AND i.ispayschedulevalid = 'Y' AND (i.docstatus = ANY (ARRAY['CO', 'CL'])) "
				+ "	AND ips.isvalid = 'Y'" + "	and i.c_bpartner_id= " + cb.getC_bpartner_id()
				+ ") a where issotrx='Y') a";
		String sql_openamt = "select sum(openamt) from (select * from (SELECT i.ad_org_id," + "i.ad_client_id,"
				+ "i.documentno," + "i.c_invoice_id," + "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx,"
				+ "i.dateinvoiced," + "i.dateacct," + "p.netdays,"
				+ "adempiere.paymenttermduedate(i.c_paymentterm_id, i.dateinvoiced) AS duedate,"
				+ "adempiere.paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced, adempiere.getdate()) AS daysdue,"
				+ "adempiere.adddays(i.dateinvoiced, p.discountdays) AS discountdate,"
				+ "round(i.grandtotal * p.discount / 100, 2) AS discountamt," + "i.grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, 0) AS openamt," + "i.c_currency_id," + "i.c_conversiontype_id,"
				+ "i.c_paymentterm_id," + "i.ispayschedulevalid," + "NULL AS c_invoicepayschedule_id,"
				+ "i.invoicecollectiontype," + "i.c_campaign_id," + "i.c_project_id," + "i.c_activity_id"
				+ "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_paymentterm p ON i.c_paymentterm_id = p.c_paymentterm_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, 0) <> 0 " + "	AND i.ispayschedulevalid <> 'Y' "
				+ "	AND (i.docstatus = ANY (ARRAY['CO', 'CL']))" + "	and i.c_bpartner_id=" + cb.getC_bpartner_id()
				+ "UNION" + " SELECT i.ad_org_id," + "i.ad_client_id," + "i.documentno," + "i.c_invoice_id,"
				+ "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx," + "i.dateinvoiced," + "i.dateacct,"
				+ "adempiere.daysbetween(ips.duedate, i.dateinvoiced) AS netdays," + "ips.duedate,"
				+ "adempiere.daysbetween(adempiere.getdate(), ips.duedate) AS daysdue," + "ips.discountdate,"
				+ "ips.discountamt," + "ips.dueamt AS grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) AS openamt," + "i.c_currency_id,"
				+ "i.c_conversiontype_id," + "i.c_paymentterm_id," + "i.ispayschedulevalid,"
				+ "ips.c_invoicepayschedule_id," + "i.invoicecollectiontype," + "i.c_campaign_id," + "i.c_project_id,"
				+ "i.c_activity_id" + "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) <> 0 "
				+ "	AND i.ispayschedulevalid = 'Y' AND (i.docstatus = ANY (ARRAY['CO', 'CL'])) "
				+ "	AND ips.isvalid = 'Y'" + "	and i.c_bpartner_id= " + cb.getC_bpartner_id()
				+ ") a where daysdue>0 and issotrx='Y' ) a";
		List<Object> saldo = saldos.buscarUno(sql);
		List<Object> saldoF = saldos.buscarUno(sql2);
		List<Object> saldoOpenAMT = saldos.buscarUno(sql_openamt);
		Float montoVencido = new Float(0.0);
		if (saldo.size() > 0) {
			try {
				saldoFactura = Float.parseFloat(saldo.get(0).toString().replace("[", "").replace("]", ""));
			} catch (Exception e) {
				saldoFactura = new Float(0.0);
			}
		}
		if (saldoF.size() > 0) {
			try {
				saldoFacturaF = Float.parseFloat(saldoF.get(0).toString().replace("[", "").replace("]", ""));
			} catch (Exception e) {
				saldoFacturaF = new Float(0.0);
			}
		}
		if (saldoOpenAMT.size()>0){
			try{
				saldoFacturaOpenAmt = Float.parseFloat(saldoOpenAMT.get(0).toString().replace("[", "").replace("]", ""));
			}catch(Exception e){
				saldoFacturaOpenAmt =  new Float(0.0);
			}
		}
		//if (pedidos.size()>0) 
		//	{
		//	montoExcedido= grandTotal;
		//	}
		value = cb.getSo_creditlimit() - cb.getSo_creditused() - grandTotal;
		if (saldoFacturaOpenAmt != 0) {
			montoVencido = saldoFacturaOpenAmt;
			System.out.println(cb.getSo_creditlimit());
			montoExcedido = saldoFacturaF - cb.getSo_creditlimit() + grandTotal;
			if (montoExcedido <= 0)
				montoExcedido = new Float(0.0);
		}
		try {
			//	if (!value.isEmpty()) {
			if (montoVencido != 0) {
				if (cb.getSo_creditlimit() == 0)
					montoExcedido = new Float(0.0);
				Float montoMostrar = new Float(0.0);
				if(montoVencido>montoExcedido) montoMostrar = montoVencido; else montoMostrar = montoExcedido;
				montoMostrar=montoMostrar;
				Float percepcion = new Float(0.0);
				if (cb.getLco_isic_id() == 1000417 && cb.getLco_taxpayertype_id() == 1000006 && totalLine>100
						|| cb.getLco_isic_id() == 1000417 && cb.getLco_taxpayertype_id() == 1000007 && totalLine>100) {
					percepcion = totalLine * new Float(0.01);
				}
				if (montoVencido >= 0 || saldoFactura > 0) {
					out.println("<especial id='campo1'><em>" + "El pedido del cliente es de: $"
							+ dfF.format(grandTotal + percepcion)
							+ "</br><especial id='limitecredito'><em>" + "El monto total de deuda es: $"
							+ dfF.format(saldoFacturaF)
							+ "</br><especial id='campo2'><em>" + "El limite de crédito aprobado del cliente es: $"
							+ dfF.format(cb.getSo_creditlimit())
							+ "</br>Monto excedido, incluyendo pedidos existentes y nuevo pedido: "
							+"<input type=\"text\" style=\"border-width:0;font-weight:bold;font-size: 15px;font-style: italic;color: red;background-color: #ffdcdc\" name=\"montoex\" "
							+" value =\"$" + dfF.format(montoExcedido)+"\" readonly=\"readonly\"/>"
							//+ dfF.format(montoExcedido) 
							+ " </br></br>" + "Monto vencido de: <input type=\"text\" style=\"border-width:0;font-weight:bold;font-style: italic;font-size: 15px;color: red;background-color: #ffdcdc\" name=\"montoven\" "
									+" value =\"$" + dfF.format(saldoFacturaOpenAmt)+"\" readonly=\"readonly\"/></br></br>"
							//+ dfF.format(saldoFacturaOpenAmt) + " </br></br> "
							+ "Total a pagar para solventar estatus de cuentas por cobrar: $" + dfF.format(montoMostrar)  
							+ "</em></especial></br>");
					out.println("<p><n> Comentarios</n> </p>");
					out.println("<textarea name=\"aprobarcredito\" rows=\"10\" cols=\"50\"></textarea>");
					out.println(
							"<input type=\"submit\" class=\"whiteButton\" id=\"regresar\" name=\"regresar\" value=\"REGRESAR\" id=\"btnLeft\" />");
					out.println(
							"<input type=\"submit\" class=\"redButton\" id=\"completarPedido\" name=\"completarPedido\" value=\"COMPLETAR\" id=\"btnLeft\" />");
					//out.println("<input type=\"button\" class=\"whiteButton\" value=\"Cancelar\" onclick='ocultar()'>");
				} else {
					out.println(
							"<input type=\"submit\" class=\"greenButton\" id=\"completarPedido\" name=\"completarPedido\" value=\"COMPLETAR PEDIDO\" id=\"btnLeft\" />");
					//out.println("<input type=\"button\" class=\"whiteButton\" value=\"Cancelar\" onclick='ocultar()'>");
				}
			} else {
				Float montoMostrar = new Float(0.0);
				montoExcedido = saldoFacturaF - cb.getSo_creditlimit() + grandTotal;
				if(montoVencido>montoExcedido) montoMostrar = montoVencido; else montoMostrar = montoExcedido;
				out.println("<especial id='limitecredito'><em>" + "El limite de crédito aprobado del cliente es: $"
						+ dfF.format(cb.getSo_creditlimit())
						+ "</br></br>Monto excedido, incluyendo pedidos existentes y nuevo pedido: <input type=\"text\" style=\"border-width:0;font-weight:bold;font-size: 15px;font-style: italic;color: red;background-color: #ffdcdc\" name=\"montoex\" "
						+" value =\"$" + dfF.format(montoExcedido)+"\" readonly=\"readonly\"/>"
						//+ dfF.format(montoExcedido) 
						+ " </br></br>" + "Monto vencido de: <input type=\"text\" style=\"border-width:0;font-weight:bold;font-style: italic;font-size: 15px;color: red;background-color: #ffdcdc\" name=\"montoven\" "
								+" value =\"$" + dfF.format(saldoFacturaOpenAmt)+"\" readonly=\"readonly\"/></br></br>"
						//+ "</br></br>Monto excedido, incluyendo pedidos existentes y nuevo pedido: $"
						//+ dfF.format(montoExcedido) + " </br></br>" + "Monto vencido de: $"
						//+ dfF.format(saldoFacturaOpenAmt) + " </br></br> "
						+ "Total a pagar para solventar estatus de cuentas por cobrar: $" + dfF.format(montoMostrar)  
						+ "</em></especial></br>");
				out.println("<p><n> Comentarios</n> </p>");
				//out.println("<div id=\"loader\" style=\"display:none;\"></div>");
				out.println("<textarea name=\"aprobarcredito\" rows=\"10\" cols=\"50\"></textarea>");
				out.println(
						"<input type=\"submit\" class=\"whiteButton\" id=\"regresar\" name=\"regresar\" value=\"REGRESAR\" id=\"btnLeft\" />");
				out.println(
						"<input type=\"submit\" class=\"redButton\" id=\"completarPedido\" name=\"completarPedido\" value=\"COMPLETAR\" id=\"btnLeft\" />");
				//out.println("<especial id='limitecredito'><em>ok</em></especial>");
			//	out.println(
				//		"<input type=\"submit\" class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\" id=\"btnLeft\" />");
				//out.println("<input type=\"button\" class=\"whiteButton\" value=\"Cancelar\" onclick='ocultar()'>");
			}
			//			} else {
			//				out.println("<especial id='limitecredito'><em>ok</em></especial>");
			//				out.println("<input type=\"button\" class=\"whiteButton\" value=\"Cancelar\" onclick='ocultar()'>");
			//			}
		} catch (java.io.IOException e1) {
			System.out.println(e1);
		}
	}%>
			<div>

				<div id='contenedor' align="center">
					<div class='contenido'>
						<div id="alerta" class="alert">
							<!-- 	<b>¿Está seguro que la siguiente información es correcta?</b><br>
			<br> Cliente:
			<especial> <em><%=CBPartnerName%></em></especial>
			<br> Direccion del cliente:
			<especial> <em><especial id="dircliente"></especial></em></especial>
			<br> Direccion de entrega:
			<especial> <em><especial id="direntrega"></especial></em></especial>-->
							<%
								validaLimite(out, request.getParameter("c_bpartner_id"));
							%></br>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id = "procesando" class="procesando">
´<p class="blocktext">Procesando...</p>
<div class="centrado"></br><div id="loader" class="loader" ></div></div></div>
		<!--<div> <input type="submit" class="whiteButton" name="eliminar" disabled="true"	value="Eliminar" id="btnLeft" /></div> -->
	</form>
	
		<!--	<a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>" class=blueButton>Regresar</a>
	<!--	<a href=busquedaProducto.jsp?c_order_id="+ cOrderID +"&c_bpartner_id="+ c_bpartner_id +"&ad_user_id="+ adId +" target=_self class=redButton>Guardar</a> 
	</div>
	<div class="toolbar">
		<a href="pedido?ad_user_id=<%=request.getParameter("ad_user_id")%>"
			class=blueButtonRegresar>Regresar</a>
		<h1 id="pageTitle">Pedido</h1>
		<!-- <a
			id="previousButton" target="_self" href="pedido?usuario=0"
			class="button"> Atras </a> 
	</div>-->
</body>
</html>