package org.saram.controladores;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.CBPartner_X;
import org.saram.accesos.COrderLine_X;
import org.saram.accesos.COrder_X;
import org.saram.accesos.MProduct_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.CBPartner;
import org.saram.modelo.COrder;
import org.saram.modelo.COrderLine;
import org.saram.modelo.MProduct;
@SuppressWarnings("serial")
@WebServlet("/completarPedidoLinea")
public class completarPedidoLinea extends HttpServlet {

	public completarPedidoLinea() {
		super();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		COrderLine_X cp = new COrderLine_X();
		COrder_X cx = new COrder_X();
		String cOrderID;
		String adId = request.getParameter("ad_user_id");
		String c_bpartner_id = request.getParameter("c_bpartner_id");
		COrder_X cox = new COrder_X();
		COrder co = new COrder();
		if (request.getParameter("c_order_id") == null) {
			cOrderID = "";
		} else {
			cOrderID = request.getParameter("c_order_id");
		};
		co = cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
		List<COrderLine> adl = cp.buscarTodos(Integer.parseInt(cOrderID));
		sqlHQL_X sqlx = new sqlHQL_X();
		CBPartner_X cbx = new CBPartner_X();
		CBPartner cb = new CBPartner();
		cb = cbx.buscarUno(c_bpartner_id != null ? Integer.parseInt(c_bpartner_id) : 0);
		Float value = new Float(0);
		// Validacion sin credito
		sqlHQL_X saldos = new sqlHQL_X();
		String sql = "select sum(openamt) from (select * from (SELECT i.ad_org_id," + "i.ad_client_id,"
				+ "i.documentno," + "i.c_invoice_id," + "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx,"
				+ "i.dateinvoiced," + "i.dateacct," + "p.netdays,"
				+ "adempiere.paymenttermduedate(i.c_paymentterm_id, i.dateinvoiced) AS duedate,"
				+ "adempiere.paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced, adempiere.getdate()) AS daysdue,"
				+ "adempiere.adddays(i.dateinvoiced, p.discountdays) AS discountdate,"
				+ "round(i.grandtotal * p.discount / 100, 2) AS discountamt," + "i.grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, 0) AS openamt," + "i.c_currency_id,"
				+ "i.c_conversiontype_id," + "i.c_paymentterm_id," + "i.ispayschedulevalid,"
				+ "NULL AS c_invoicepayschedule_id," + "i.invoicecollectiontype," + "i.c_campaign_id,"
				+ "i.c_project_id," + "i.c_activity_id" + "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_paymentterm p ON i.c_paymentterm_id = p.c_paymentterm_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, 0) <> 0 " + "	AND i.ispayschedulevalid <> 'Y' "
				+ "	AND (i.docstatus = ANY (ARRAY['CO', 'CL']))" + "	and i.c_bpartner_id="
				+ cb.getC_bpartner_id() + "UNION" + " SELECT i.ad_org_id," + "i.ad_client_id," + "i.documentno,"
				+ "i.c_invoice_id," + "i.c_order_id," + "i.c_bpartner_id," + "i.issotrx," + "i.dateinvoiced,"
				+ "i.dateacct," + "adempiere.daysbetween(ips.duedate, i.dateinvoiced) AS netdays," + "ips.duedate,"
				+ "adempiere.daysbetween(adempiere.getdate(), ips.duedate) AS daysdue," + "ips.discountdate,"
				+ "ips.discountamt," + "ips.dueamt AS grandtotal,"
				+ "adempiere.invoicepaid(i.c_invoice_id, i.c_currency_id, 1) AS paidamt,"
				+ "adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) AS openamt,"
				+ "i.c_currency_id," + "i.c_conversiontype_id," + "i.c_paymentterm_id," + "i.ispayschedulevalid,"
				+ "ips.c_invoicepayschedule_id," + "i.invoicecollectiontype," + "i.c_campaign_id,"
				+ "i.c_project_id," + "i.c_activity_id" + "   FROM adempiere.rv_c_invoice i"
				+ " JOIN adempiere.c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id"
				+ "  WHERE adempiere.invoiceopen(i.c_invoice_id, ips.c_invoicepayschedule_id) <> 0 "
				+ "	AND i.ispayschedulevalid = 'Y' AND (i.docstatus = ANY (ARRAY['CO', 'CL'])) "
				+ "	AND ips.isvalid = 'Y'" + "	and i.c_bpartner_id= " + cb.getC_bpartner_id()
				+ ") a where daysdue>0 and issotrx='Y') a";
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
		List<Object> saldo = saldos.buscarUno(sql);
		List<Object> saldoF = saldos.buscarUno(sql2);
		Float saldoFactura = new Float(0.0);
		if (saldo.size() > 0) {
			try {
				saldoFactura = Float.parseFloat(saldo.get(0).toString().replace("[", "").replace("]", ""));
			} catch (Exception e) {
				saldoFactura = new Float(0.0);
			}
		}
		value = saldoFactura;
		Float montoExcedido = new Float(0.0);
		Float montoVencido = new Float(0.0);
		if (cb.getSo_creditlimit() != 0) {

			List<Object> pedidos = saldos
					.buscarUno("select coalesce(sum(grandtotal),0.0) from c_order " + "where c_bpartner_id = "
							+ cb.getC_bpartner_id() + " and docstatus = 'CO' and docaction = 'CL' and "
							+ "c_order_id not in (select c_order_id from c_invoice " + "where c_bpartner_id = "
							+ cb.getC_bpartner_id()
							+ " and c_order_id is not null and docstatus = 'CO' and docaction = 'CL' )");

			// System.out.println(saldo.get(0).toString().replace("[","").replace("]",""));
			// System.out.println(saldo.get(0).toString().replace("[","").replace("]",""));

			if (saldo.size() > 0)
				try {
					saldoFactura = Float.parseFloat(saldo.get(0).toString().replace("[", "").replace("]", ""));
				} catch (Exception e) {
					saldoFactura = new Float(0.0);
				}
			if (saldoF.size() > 0)
				try {
					montoExcedido =  Float.parseFloat(saldoF.get(0).toString().replace("[", "").replace("]", ""));;
					montoExcedido = montoExcedido - cb.getSo_creditlimit() + (co.getGrandtotal());
				} catch (Exception e) {
					montoExcedido = new Float(0.0);
				}
			value = saldoFactura;
		}
		if ((co.getGrandtotal() > cb.getSo_creditlimit()) && cb.getSo_creditlimit() > 0 || montoExcedido > 0) {
			request.getRequestDispatcher("/pedido_n.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id
					+ "&c_order_id=" + cOrderID + "&solicitud=0").forward(request, response);
		}
		if (value <= 0) {
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
//			System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE");
			co.setDocstatus("CO");
			co.setDocaction("CL");
			co.setProcessed("Y");
			cx.salvarOrden(co);
			try {
				/**
				 * ENVIO DE CORREOS
				 **/
				// Mail de nuevo pedido
				String mensaje = "Por favor verificar el pedido del cliente " + cb.getName() + " en el pedido "
						+ co.getDocumentno();
//				 enviarCorreos(mensaje, "pedidos@mor.com.sv");
//				 enviarCorreos(mensaje, "ingenieria2@mor.com.sv");
//				 enviarCorreos(mensaje, "informatica1@mor.com.sv");
				// Mail a administracion de la demanda si es necesario
				MProduct mp = new MProduct();
				MProduct_X mpx = new MProduct_X();
				for (COrderLine coi : adl) {
					List<Object> qtyDisponibles;
					Double qtyDisponible;
					String stringQtyDisponible;
					if (coi.getM_product_id() != null) {
						qtyDisponibles = sqlx.buscarUno(
								"select sum(qtyonhand) - sum(qtyreserved) from m_storage WHERE m_product_id ="
										+ coi.getM_product_id());
						stringQtyDisponible = qtyDisponibles.get(0).toString().replace("[", "").replace("]", "");
						if (stringQtyDisponible != null) {
							qtyDisponible = new Double(stringQtyDisponible);
						} else {
							qtyDisponible = new Double(1.0);
						}
						if (qtyDisponible <= 0) {
							mp = mpx.buscarUno(coi.getM_product_id());
							String mensajeDisponibilidad = "Para el pedido " + co.getDocumentno()
									+ " no se cuenta con suficiente disponibilidad para el " + "producto "
									+ mp.getName() + " con codigo " + mp.getValue();
//							 enviarCorreos(mensajeDisponibilidad,
//							 "planeacion@mor.com.sv");
//							 enviarCorreos(mensajeDisponibilidad,
//							 "ingenieria2@mor.com.sv");
							enviarCorreos(mensajeDisponibilidad, "informatica1@mor.com.sv");
						}
					}
				}
				/**
				 * FIN ENVIO DE CORREOS
				 **/
				// Redireccionamos pagina
				request.getRequestDispatcher("/pedido_c.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id
						+ "&c_order_id=" + cOrderID + "&solicitud=0").forward(request, response);
			} catch (Exception e) {
				// Te cache
			}
		} else {
			request.getRequestDispatcher("/pedido_n.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id
					+ "&c_order_id=" + cOrderID + "&solicitud=0").forward(request, response);
		}
		return;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*** Saram ***/
	}
	public boolean enviarCorreos(String texto, String correo){
		String para = correo;
		String de="notificaciones.saram@gmail.com";
		String host = "smtp.gmail.com";
		Properties propiedades = System.getProperties();
		propiedades.setProperty("mail.smtp.host", host);
		propiedades.setProperty("mail.user", "notificaciones.saram@gmail.com");
		propiedades.setProperty("mail.password", "wrsetoelfcklybpl");
		propiedades.setProperty("mail.port", "587");
		Session sesion = Session.getDefaultInstance(propiedades);
		try {
			// Creamos un objeto mensaje tipo MimeMessage por
			// defecto.
			MimeMessage mensaje = new MimeMessage(sesion);
			// Asignamos el “de o from” al header del correo.
			mensaje.setFrom(new InternetAddress(de));

			// Asignamos el “para o to” al header del correo.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
//			mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress("ingenieria2@mor.com.sv"));

			// Asignamos el asunto
//			mensaje.setSubject("**PRUEBA DE PEDIDOS++ NOTIFICACION CLIENTE EN ESPERA");
			// \u00f1 -> ñ

			// Asignamos el contenido HTML, tan grande como
			// nosotros queramos
			// first part (the html)
			// This mail has 2 part, the BODY and the embedded
			// image
//			MimeMultipart multipart = new MimeMultipart("related");
//			// first part (the html)
//			BodyPart messageBodyPart = new MimeBodyPart();
//			String htmlText = "<img src=\"cid:image\">";
//			messageBodyPart.setContent(htmlText, "text/html");
//			// add it
//			multipart.addBodyPart(messageBodyPart);
//			// second part (the image)
//			messageBodyPart = new MimeBodyPart();
//			DataSource fds = new FileDataSource("img/tarjetas/fc_02.jpg");
//			messageBodyPart.setDataHandler(new DataHandler(fds));
//			messageBodyPart.setHeader("Content-ID", "<image>");
//			// add image to the multipart
//			multipart.addBodyPart(messageBodyPart);
//			multipart.addBodyPart(messageBodyPart);
//			mensaje.setContent(multipart);
			 // Create a default MimeMessage object.
//	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
			mensaje.setFrom(new InternetAddress(de));

	         // Set To: header field of the header.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

	         // Set Subject: header field
			mensaje.setSubject("**PEDIDO GENERADO POR EL SISTEMA MOVIL** SOLICITUD DE APROBACION NOTIFICACION DE PEDIDOS CLIENTE EN ESPERA");

	         // Now set the actual message
			mensaje.setText(texto);
			// mensaje.setContentLanguage(languages);
			// Enviamos el correo
			Transport.send(mensaje);
			System.out.println("Mensaje enviado");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
