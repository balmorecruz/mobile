package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.CBPartner_X;
import org.saram.accesos.COrderLine_X;
import org.saram.accesos.COrderTax_X;
import org.saram.accesos.COrder_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.CBPartner;
import org.saram.modelo.COrder;
import org.saram.modelo.COrderLine;;

/**
 * Servlet implementation class pedidoIngreso
 */
@WebServlet("/busquedaPedidosV")
public class busquedaPedidosLineaV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public busquedaPedidosLineaV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("rawtypes")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		COrderLine_X cp = new COrderLine_X();
		COrder_X cx = new COrder_X();
		String cOrderID;
		String adId = request.getParameter("ad_user_id");
		String c_bpartner_id = request.getParameter("c_bpartner_id");
		COrder_X cox = new COrder_X();
		COrder co  = new COrder();
		if (request.getParameter("c_order_id") == null) {
			cOrderID = "";
		} else {
			cOrderID = request.getParameter("c_order_id");
		};
		co = cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
		List<COrderLine> adl = cp.buscarTodos(Integer.parseInt(cOrderID));
		if (request.getParameter("EliminarPedido") != null) {
			COrderTax_X cot = new COrderTax_X();
			COrderLine_X col = new COrderLine_X();
			Long error;
			error = cot.eliminarTodos(Integer.parseInt(cOrderID));
			error = col.eliminarTodos(Integer.parseInt(cOrderID));
			error = cox.eliminarUno(Integer.parseInt(cOrderID));
			request.getRequestDispatcher("pedido?ad_user_id=" + adId).forward(request, response);
			return;
		}
		if (request.getParameter("guardarPedido") != null) {
			request.getRequestDispatcher("pedidoIngreso?ad_user_id=" + adId).forward(request, response);
			return;
		}
		PrintWriter out = response.getWriter();
		if (request.getParameter("completar") != null) {
			sqlHQL_X sqlx = new sqlHQL_X();
			CBPartner_X cbx = new CBPartner_X();
			CBPartner cb = new CBPartner();
			cb = cbx.buscarUno(c_bpartner_id!=null?Integer.parseInt(c_bpartner_id):0);
			Float value = new Float(0);
			//Validacion sin credito
			sqlHQL_X saldos = new sqlHQL_X();
			String sql = "select sum(openamt) from (select * from (SELECT i.ad_org_id,"
					+ "i.ad_client_id,"
					+ "i.documentno,"
					+ "i.c_invoice_id,"
					+ "i.c_order_id,"
					+ "i.c_bpartner_id,"
					+ "i.issotrx,"
					+ "i.dateinvoiced,"
					+ "i.dateacct,"
					+ "p.netdays,"
					+ "adempiere.paymenttermduedate(i.c_paymentterm_id, i.dateinvoiced) AS duedate,"
					+ "adempiere.paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced, adempiere.getdate()) AS daysdue,"
					+ "adempiere.adddays(i.dateinvoiced, p.discountdays) AS discountdate,"
					+ "round(i.grandtotal * p.discount / 100, 2) AS discountamt,"
					+ "i.grandtotal,"
					+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
					+ "adempiere.invoiceopen(i.c_invoice_id, 0) AS openamt,"
					+ "i.c_currency_id,"
					+ "i.c_conversiontype_id,"
					+ "i.c_paymentterm_id,"
					+ "i.ispayschedulevalid,"
					+ "NULL AS c_invoicepayschedule_id,"
					+ "i.invoicecollectiontype,"
					+ "i.c_campaign_id,"
					+ "i.c_project_id,"
					+ "i.c_activity_id"
					+ "   FROM adempiere.rv_c_invoice i"
					+ " JOIN adempiere.c_paymentterm p ON i.c_paymentterm_id = p.c_paymentterm_id"
					+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, 0) <> 0 "
					+ "	AND i.ispayschedulevalid <> 'Y' "
					+ "	AND (i.docstatus = ANY (ARRAY['CO', 'CL']))"
					+ "	and i.c_bpartner_id=" + cb.getC_bpartner_id()
					+ "UNION"
					+ " SELECT i.ad_org_id,"
					+ "i.ad_client_id,"
					+ "i.documentno,"
					+ "i.c_invoice_id,"
					+ "i.c_order_id,"
					+ "i.c_bpartner_id,"
					+ "i.issotrx,"
					+ "i.dateinvoiced,"
					+ "i.dateacct,"
					+ "adempiere.daysbetween(ips.duedate, i.dateinvoiced) AS netdays,"
					+ "ips.duedate,"
					+ "adempiere.daysbetween(adempiere.getdate(), ips.duedate) AS daysdue,"
					+ "ips.discountdate,"
					+ "ips.discountamt,"
					+ "ips.dueamt AS grandtotal,"
					+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
					+ "adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) AS openamt,"
					+ "i.c_currency_id,"
					+ "i.c_conversiontype_id,"
					+ "i.c_paymentterm_id,"
					+ "i.ispayschedulevalid,"
					+ "ips.c_invoicepayschedule_id,"
					+ "i.invoicecollectiontype,"
					+ "i.c_campaign_id,"
					+ "i.c_project_id,"
					+ "i.c_activity_id"
					+ "   FROM adempiere.rv_c_invoice i"
					+ " JOIN adempiere.c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id"
					+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) <> 0 "
					+ "	AND i.ispayschedulevalid = 'Y' AND (i.docstatus = ANY (ARRAY['CO', 'CL'])) "
					+ "	AND ips.isvalid = 'Y'"
					+ "	and i.c_bpartner_id= " + cb.getC_bpartner_id() + ") a where daysdue>0 and issotrx='Y') a";
			List<Object> saldo = saldos.buscarUno(sql);
			Float saldoFactura = new Float(0.0);
			if (saldo.size() > 0) {
				try {
					saldoFactura = Float.parseFloat(saldo.get(0).toString().replace("[","").replace("]",""));
				} catch(Exception e){
					saldoFactura = new Float(0.0);
				}
			}
			value = saldoFactura;
			Float montoExcedido = new Float(0.0);
			Float montoVencido = new Float(0.0);
			if (cb.getSo_creditlimit()!=0){
				
				List<Object> pedidos = saldos.buscarUno("select coalesce(sum(grandtotal),0.0) from c_order " + "where c_bpartner_id = "
						+ cb.getC_bpartner_id() + " and docstatus = 'CO' and docaction = 'CL' and "
						+ "c_order_id not in (select c_order_id from c_invoice " + "where c_bpartner_id = "
						+ cb.getC_bpartner_id() + " and c_order_id is not null and docstatus = 'CO' and docaction = 'CL' )");
				
				
//				System.out.println(saldo.get(0).toString().replace("[","").replace("]",""));
//				System.out.println(saldo.get(0).toString().replace("[","").replace("]",""));
				
				if (saldo.size() > 0) 
				try {
					saldoFactura = Float.parseFloat(saldo.get(0).toString().replace("[","").replace("]",""));
				} catch(Exception e){
					saldoFactura = new Float(0.0);
				}
				if (pedidos.size()>0) 
					try {
						montoExcedido= Float.parseFloat(pedidos.get(0).toString().replace("[","").replace("]",""));
					} catch(Exception e){
						montoExcedido = new Float(0.0);
					}				
				value = saldoFactura;
			}
			if ((co.getGrandtotal()>cb.getSo_creditlimit())&&cb.getSo_creditlimit()>0){
				request.getRequestDispatcher(
						"/pedido_n.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=0")
						.forward(request, response);
			}
			if (value<=0){
				/* Si el cliente no debe nada guardamos informacion */
				co = cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
				List<Object> mLocatorObject = sqlx
						.buscarUno("select m_locator_id from m_locator where isactive='Y' and M_warehouse_id = "
								+ co.getM_warehouse_id());
				Integer mLocatorID = (mLocatorObject == null ? 0 : Integer.parseInt(mLocatorObject.get(0).toString()));
				for (COrderLine coi : adl) {
					if (coi.getM_product_id() != null) {
						sqlx.sql("UPDATE m_storage SET qtyreserved = qtyreserved + " + coi.getQtyentered()
								+ " WHERE m_product_id = " + coi.getM_product_id() + " "
								+ "AND m_attributesetinstance_id = 0 AND m_locator_id = " + mLocatorID);
					}
					coi.setProcessed("Y");
					cp.salvarOrden(coi);
				}
				System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE");
				co.setDocstatus("CO");
				co.setDocaction("CL");
				co.setProcessed("Y");
				cx.salvarOrden(co);
				//Redireccionamos pagina
				try{
				request.getRequestDispatcher(
						"/pedido_c.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=0")
						.forward(request, response);
				} catch(Exception e){
					//Te cache
				}
			} else {
			request.getRequestDispatcher(
					"/pedido_n.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=0")
					.forward(request, response);
			}
			return;
		}
		if (request.getParameter("solicitar") != null) {
			sqlHQL_X sqlx = new sqlHQL_X();
			co = cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
			List<Object> mLocatorObject = sqlx
					.buscarUno("select m_locator_id from m_ocator where isactive='Y' and m_warehourse = "
							+ co.getM_warehouse_id());
			Integer mLocatorID = (mLocatorObject == null ? 0 : Integer.parseInt(mLocatorObject.get(0).toString()));
			for (COrderLine coi : adl) {
				if (coi.getM_product_id() != null) {
					sqlx.sql("UPDATE m_storage SET qtyreserved = qtyreserved + " + coi.getQtyentered()
							+ " WHERE m_product_id = " + coi.getM_product_id() + " "
							+ "AND m_attributesetinstance_id = 0 AND m_locator_id = " + mLocatorID);
				}
				coi.setProcessed("Y");
				cp.salvarOrden(coi);
			}
			co.setDocstatus("CO");
			co.setDocaction("CL");
			co.setProcessed("Y");
			cx.salvarOrden(co);
			System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE");

			request.getRequestDispatcher(
					"/pedido_c.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=1")
					.forward(request, response);
			return;
		}
		Integer flete = 0;
		Integer cantidad = 0;
		if (adl.size() != 0) {
			
			out.println("<div class=\"table-responsive\"><table class=\"table table-hover\" style=\"table-layout: fixed;\">");
			out.println("<thead class=\"thead-inverse\"><tr><th width=\"50%\">Productos</th><th width=\"15%\">Cant</th><th width=\"15%\">Precio ($)</th><th width=\"20%\">Subtotal ($)</th></tr></thead><tbody>");
			List s;
			List mst;
			sqlHQL_X mp = new sqlHQL_X();
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			DecimalFormat df = new DecimalFormat("###,###,##0.00");
			for (COrderLine coi : adl) {
				s = mp.buscarUno("SELECT name FROM m_product WHERE m_product_id =" + "" + coi.getM_product_id());
				mst = mp.buscarUno("SELECT sum(qtyonhand) FROM m_storage WHERE m_locator_id = 1000023 and m_product_id ="
						+ "" + coi.getM_product_id());
//				out.println("<li>");
				if (coi.getPriceentered() != null & coi.getM_product_id()!=null) {
				if (coi.getPriceactual() <=0.03){
//					out.println(
//							"<tr><th scope=\"row\"><a " + "href=\"pedido_linea_m.jsp?" + "ad_user_id=" + adId + "" + "&c_order_id=" + cOrderID
//									+ "&c_bpartner_id=" + c_bpartner_id + "&c_orderline_id=" + coi.getC_orderline_id()
//									+ "&m_product_id=" + coi.getM_product_id() + "\" target=\"_self\"/>");
					out.println(
							"<tr><th scope=\"row\">");
					out.println("PROMOCION " + s.get(0).toString());
//					out.println(s.get(0).toString());
					out.println("</th><td>");
					out.println(coi.getQtyentered() + "</td><td>" + df.format(coi.getPriceactual())
							+ "</td><td>" + df.format(coi.getPriceactual() * coi.getQtyentered()));
//					out.println("CANTIDAD EN INVENTARIO:" + mst.get(0).toString());
					out.println("</td>");
//					out.println("</a>");
				} else
				if (coi.getM_product_id() != null) {
					cantidad = 1;
//					out.println(
//							"<tr><th scope=\"row\"><a " + "href=\"pedido_linea_m.jsp?" + "ad_user_id=" + adId + "" + "&c_order_id=" + cOrderID
//									+ "&c_bpartner_id=" + c_bpartner_id + "&c_orderline_id=" + coi.getC_orderline_id()
//									+ "&m_product_id=" + coi.getM_product_id() + "\" target=\"_self\"/>");
//					out.println(s.get(0).toString());
//					out.println("</a></th><td>");
					out.println(
							"<tr><th scope=\"row\">");
					out.println(s.get(0).toString());
					out.println("</th><td>");
					out.println(coi.getQtyentered()+ "</td><td>" + df.format(coi.getPriceactual())
					+ "</td><td>" + df.format(coi.getPriceactual() * coi.getQtyentered()));
//					out.println("<br><FONT SIZE=4 COLOR=BLUE><b>CANTIDAD EN INVENTARIO:" + mst.get(0).toString());
					out.println("</td>");
				}} else {
					flete = 1;
					s = mp.buscarUno("SELECT name FROM c_charge WHERE c_charge_id =" + coi.getC_charge_id());
					out.println("<tr><th scope=\"row\">");
					if (coi.getC_charge_id() == 1004412) {
						out.println("CUOTA AVE ");
					} else {
						out.println("FLETE " + s);
					}
					out.println("</th><td>");
					out.println(coi.getQtyentered() + "</td><td>" + coi.getPriceactual() + "</td><td>" 
							+ df.format(coi.getPriceactual() * coi.getQtyentered()));
					out.println("</td>");
//					out.println("</a>");
				}
//				out.println("</li>");
			}
			if (cantidad == 1){
			out.println("</tbody><tfoot><tr><th colspan=\"4\">");
			co = cox.buscarUno(Integer.parseInt(cOrderID));
//			out.println("</li>");
			out.println("IVA DE FACTURA: $" + df.format(co.getGrandtotal()-co.getTotallines()) );
			out.println("</tr></th><tr><th colspan=\"4\">");
			out.println("TOTAL DE LINEAS: $" + df.format(co.getTotallines()) );
			out.println("</tr></th><tr><th colspan=\"4\">");
			out.println("TOTAL DE FACTURA: $" + df.format(co.getGrandtotal()) );
			//out.println("</tr></th><tr><th colspan=\"4\">");
			//out.println("PERSEPCION: $" + df.format(co.getGrandtotal()) );
			}
			s = mp.buscarUno("SELECT sum(qtyentered) FROM c_orderline WHERE m_product_id is not null and c_order_id =" + co.getC_order_id());
			String ss = s.toString();
			ss = ss.replace("[","");
			ss = ss.replace("]","");
			out.println("</tr></th><tr><th colspan=\"4\">");
			out.println("TOTAL DE CANTIDAD: " + ss);
			out.println("</tr></th><tr>");
			s = mp.buscarUno("SELECT count(c_orderline_id) FROM c_orderline WHERE m_product_id is not null and c_order_id =" + co.getC_order_id());
			ss = s.toString();
			ss = ss.replace("[","");
			ss = ss.replace("]","");
			out.println("</tr></th><tr><th colspan=\"4\">");
			out.println("TOTAL DE PRODUCTOS ORDENADOS: " + ss);
			out.println("</th><tr></tfoot></table></div>");
		} else {
			out.println("<ul selected=\"true\" title=\"Mobile SARAM\">");
			out.println("<li>");
			out.println("<div class=\"secondary\">");
			out.println("SIN PRODUCTOS/FLETE");
			out.println("</div>");
			out.println("</li>");
			out.println("</ul>");
		}
		out.println("<div class=\"footer\">");
		// out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId
		// +"&c_bpartner_id="+ c_bpartner_id +"&c_order_id="+ cOrderID +"\"
		// class=\"blueButton\">Regresar a pedido</a>");
		// out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\"
		// target=\"_self\" class=\"greenButton\">Registrar</a>");
//		out.println("<a href=\"busquedaProducto.jsp?c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id
//				+ "&ad_user_id=" + adId + "\" target=\"_self\" class=\"greenButton\">REGISTRAR PRODUCTO</a>");
//		if (cantidad == 1)
//		out.println("<a href=\"busquedaPedidos?c_order_id="+ cOrderID + "&c_bpartner_id="+c_bpartner_id+"&completar=1\" target=\"_self\" class=\"greenButton\">COMPLETAR PEDIDO</a>");
//		out.println("<a "
//				+ "class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\""
//				+ "href=\"busquedaPedidos?c_order_id="+ cOrderID + "&c_bpartner_id="+c_bpartner_id+"\" target=\"_self\" "
//						+ "id=\"completar\" name=\"completar\" class=\"completapedido\"/>");
//		out.println("<input type=\"submit\" class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\" id=\"btnLeft\" />");
		out.println("&nbsp;&nbsp;");
//		if (flete == 0) {
//			out.println("<a href=\"busquedaCargo.jsp?c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id
//					+ "&ad_user_id=" + adId + "\" target=\"_self\" class=\"greenButton\">REGISTRAR FLETE</a>");
//		}
		out.println("</div>");
		out.println("<div class=\"toolbar\">");
		// out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId
		// +"&c_bpartner_id="+ c_bpartner_id +"&c_order_id="+ cOrderID +"\"
		// class=\"blueButton\">Regresar a pedido</a>");
		// out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\"
		// target=\"_self\" class=\"greenButton\">Registrar</a>");
		out.println("<a href=\"pedido_m_v.jsp?ad_user_id=" + adId + "&c_bpartner_id="+ 
		c_bpartner_id + "&c_order_id="+ cOrderID + "\" class=blueButtonRegresar>Regresar</a>");
		out.println("<h1 id=\"pageTitle\">Lineas</h1>");
		out.println("</div>");
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}






//otra clase
/*
package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.CBPartner_X;
import org.saram.accesos.COrderLine_X;
import org.saram.accesos.COrderTax_X;
import org.saram.accesos.COrder_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.CBPartner;
import org.saram.modelo.COrder;
import org.saram.modelo.COrderLine;;
@WebServlet("/busquedaPedidos")
public class busquedaPedidosLinea extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public busquedaPedidosLinea() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		COrderLine_X cp = new COrderLine_X();
		COrder_X cx = new COrder_X();
		String cOrderID;
		String adId = request.getParameter("ad_user_id");
		String c_bpartner_id = request.getParameter("c_bpartner_id");
		COrder_X cox = new COrder_X();
		COrder co  = new COrder();
		if (request.getParameter("c_order_id") == null) {
			cOrderID = "";
		} else {
			cOrderID = request.getParameter("c_order_id");
		}
		;
		List<COrderLine> adl = cp.buscarTodos(Integer.parseInt(cOrderID));
		if (request.getParameter("EliminarPedido") != null) {
			COrderTax_X cot = new COrderTax_X();
			COrderLine_X col = new COrderLine_X();
			Long error;
			error = cot.eliminarTodos(Integer.parseInt(cOrderID));
			error = col.eliminarTodos(Integer.parseInt(cOrderID));
			error = cox.eliminarUno(Integer.parseInt(cOrderID));
			request.getRequestDispatcher("pedido?ad_user_id=" + adId).forward(request, response);
			return;
		}
		if (request.getParameter("guardarPedido") != null) {
			request.getRequestDispatcher("pedidoIngreso?ad_user_id=" + adId).forward(request, response);
			return;
		}
		PrintWriter out = response.getWriter();
		if (request.getParameter("completar") != null) {
			sqlHQL_X sqlx = new sqlHQL_X();
			CBPartner_X cbx = new CBPartner_X();
			CBPartner cb = new CBPartner();
			cb = cbx.buscarUno(c_bpartner_id!=null?Integer.parseInt(c_bpartner_id):0);
			co = cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
			List<Object> mLocatorObject = sqlx
					.buscarUno("select m_locator_id from m_locator where isactive='Y' and M_warehouse_id = "
							+ co.getM_warehouse_id());
			Integer mLocatorID = (mLocatorObject == null ? 0 : Integer.parseInt(mLocatorObject.get(0).toString()));
			for (COrderLine coi : adl) {
				if (coi.getM_product_id() != null) {
					sqlx.sql("UPDATE m_storage SET qtyreserved = qtyreserved + " + coi.getQtyentered()
							+ " WHERE m_product_id = " + coi.getM_product_id() + " "
							+ "AND m_attributesetinstance_id = 0 AND m_locator_id = " + mLocatorID);
				}
				coi.setProcessed("Y");
				cp.salvarOrden(coi);
			}
			co.setDocstatus("CO");
			co.setDocaction("CL");
			co.setProcessed("Y");
			cx.salvarOrden(co);
			Float value = new Float(1);
			if (cb.getSo_creditlimit()!=0){
				value = cb.getSo_creditlimit()-cb.getSo_creditused() - co.getGrandtotal();
			}
			System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE");
			if (value>0){
				request.getRequestDispatcher(
						"/pedido_c.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=0")
						.forward(request, response);
			} else {
			request.getRequestDispatcher(
					"/pedido_n.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=0")
					.forward(request, response);
			}
			return;
		}
		if (request.getParameter("solicitar") != null) {
			sqlHQL_X sqlx = new sqlHQL_X();
			co = cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
			List<Object> mLocatorObject = sqlx
					.buscarUno("select m_locator_id from m_ocator where isactive='Y' and m_warehourse = "
							+ co.getM_warehouse_id());
			Integer mLocatorID = (mLocatorObject == null ? 0 : Integer.parseInt(mLocatorObject.get(0).toString()));
			for (COrderLine coi : adl) {
				if (coi.getM_product_id() != null) {
					sqlx.sql("UPDATE m_storage SET qtyreserved = qtyreserved + " + coi.getQtyentered()
							+ " WHERE m_product_id = " + coi.getM_product_id() + " "
							+ "AND m_attributesetinstance_id = 0 AND m_locator_id = " + mLocatorID);
				}
				coi.setProcessed("Y");
				cp.salvarOrden(coi);
			}
			co.setDocstatus("CO");
			co.setDocaction("CL");
			co.setProcessed("Y");
			cx.salvarOrden(co);
			System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE");

			request.getRequestDispatcher(
					"/pedido_c.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=1")
					.forward(request, response);
			return;
		}
		Integer flete = 0;
		Integer cantidad = 0;
		if (adl.size() != 0) {
			
			out.println("<ul selected=\"true\" title=\"Pedido\">");
			List s;
			List mst;
			sqlHQL_X mp = new sqlHQL_X();
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			DecimalFormat df = new DecimalFormat("###,###,###.00");
			for (COrderLine coi : adl) {
				s = mp.buscarUno("SELECT name FROM m_product WHERE m_product_id =" + "" + coi.getM_product_id());
				mst = mp.buscarUno("SELECT sum(qtyonhand) FROM m_storage WHERE m_locator_id = 1000023 and m_product_id ="
						+ "" + coi.getM_product_id());
				out.println("<li>");
				if (coi.getPriceentered() != null & coi.getM_product_id()!=null) {
				if (coi.getPriceactual() <=0.03){
					out.println(
							"<a " + "href=\"pedido_linea_m.jsp?" + "ad_user_id=" + adId + "" + "&c_order_id=" + cOrderID
									+ "&c_bpartner_id=" + c_bpartner_id + "&c_orderline_id=" + coi.getC_orderline_id()
									+ "&m_product_id=" + coi.getM_product_id() + "\" target=\"_self\"/>");
					out.println("PRODUCTO PROMOCIONAL - " + s.get(0).toString());
//					out.println(s.get(0).toString());
					out.println("<div class=\"secondary\">");
					out.println("CANTIDAD: " + coi.getQtyentered() + " PRECIO: $" + df.format(coi.getPriceactual())
							+ " TOTAL: $" + df.format(coi.getPriceactual() * coi.getQtyentered()));
					out.println("CANTIDAD EN INVENTARIO:" + mst.get(0).toString());
					out.println("</div>");
					out.println("</a>");
				} else
				if (coi.getM_product_id() != null) {
					cantidad = 1;
					out.println(
							"<a " + "href=\"pedido_linea_m.jsp?" + "ad_user_id=" + adId + "" + "&c_order_id=" + cOrderID
									+ "&c_bpartner_id=" + c_bpartner_id + "&c_orderline_id=" + coi.getC_orderline_id()
									+ "&m_product_id=" + coi.getM_product_id() + "\" target=\"_self\"/>");
					out.println(s.get(0).toString());
					out.println("<div class=\"secondary\">");
					out.println("CANTIDAD: " + coi.getQtyentered() + " PRECIO: $" + df.format(coi.getPriceactual())
							+ " TOTAL: $" + df.format(coi.getPriceactual() * coi.getQtyentered()));
					out.println("<br><FONT SIZE=4 COLOR=BLUE><b>CANTIDAD EN INVENTARIO:" + mst.get(0).toString());
					out.println("</b></font></div>");
					out.println("</a>");
				}} else {
					flete = 1;
					s = mp.buscarUno("SELECT name FROM c_charge WHERE c_charge_id =" + coi.getC_charge_id());
					out.println("<a " + "href=\"pedido_linea_f_m.jsp?" + "ad_user_id=" + adId + "" + "&c_order_id="
							+ cOrderID + "&c_bpartner_id=" + c_bpartner_id + "&c_orderline_id="
							+ coi.getC_orderline_id() + "&c_charge_id=" + coi.getC_charge_id()
							+ "\" target=\"_self\"/>");
					if (coi.getC_charge_id() == 1004412) {
						out.println("CUOTA AVE ");
					} else {
						out.println("FLETE - " + s);
					}
					out.println("<div class=\"secondary\">");
					out.println("CANTIDAD: " + coi.getQtyentered() + " PRECIO: $" + coi.getPriceactual() + " TOTAL: $"
							+ df.format(coi.getPriceactual() * coi.getQtyentered()));
					out.println("</div>");
					out.println("</a>");
				}
				out.println("</li>");
			}
			if (cantidad == 1){
			out.println("<li>");
			co = cox.buscarUno(Integer.parseInt(cOrderID));
			out.println("TOTAL DE FACTURA: $" + df.format(co.getGrandtotal()) );
			out.println("</li>");
			}
			out.println("</ul>");
		} else {
			out.println("<ul selected=\"true\" title=\"Mobile SARAM\">");
			out.println("<li>");
			out.println("<div class=\"secondary\">");
			out.println("SIN PRODUCTOS/FLETE");
			out.println("</div>");
			out.println("</li>");
			out.println("</ul>");
		}
		out.println("<div class=\"footer\">");
		// out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId
		// +"&c_bpartner_id="+ c_bpartner_id +"&c_order_id="+ cOrderID +"\"
		// class=\"blueButton\">Regresar a pedido</a>");
		// out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\"
		// target=\"_self\" class=\"greenButton\">Registrar</a>");
		out.println("<a href=\"busquedaProducto.jsp?c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id
				+ "&ad_user_id=" + adId + "\" target=\"_self\" class=\"greenButton\">REGISTRAR PRODUCTO</a>");
		if (cantidad == 1)
		out.println("<a href=\"busquedaPedidos?c_order_id="+ cOrderID + "&c_bpartner_id="+c_bpartner_id+"&completar=1\" target=\"_self\" class=\"greenButton\">COMPLETAR PEDIDO</a>");
//		out.println("<a "
//				+ "class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\""
//				+ "href=\"busquedaPedidos?c_order_id="+ cOrderID + "&c_bpartner_id="+c_bpartner_id+"\" target=\"_self\" "
//						+ "id=\"completar\" name=\"completar\" class=\"completapedido\"/>");
//		out.println("<input type=\"submit\" class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\" id=\"btnLeft\" />");
		out.println("&nbsp;&nbsp;");
//		if (flete == 0) {
//			out.println("<a href=\"busquedaCargo.jsp?c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id
//					+ "&ad_user_id=" + adId + "\" target=\"_self\" class=\"greenButton\">REGISTRAR FLETE</a>");
//		}
		out.println("</div>");
		out.println("<div class=\"toolbar\">");
		// out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId
		// +"&c_bpartner_id="+ c_bpartner_id +"&c_order_id="+ cOrderID +"\"
		// class=\"blueButton\">Regresar a pedido</a>");
		// out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\"
		// target=\"_self\" class=\"greenButton\">Registrar</a>");
		out.println("<a href=\"pedido_m.jsp?ad_user_id=" + adId + "&c_bpartner_id="+ 
		c_bpartner_id + "&c_order_id="+ cOrderID + "\" class=blueButtonRegresar>Regresar</a>");
		out.println("<h1 id=\"pageTitle\">Lineas de pedido</h1>");
		out.println("</div>");
		// TODO Auto-generated method stub
		return;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

 */







