package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import org.saram.accesos.COrderTax_X;
import org.saram.accesos.COrder_X;
import org.saram.accesos.MProduct_X;
import org.saram.accesos.MPromocionLine_X;
import org.saram.accesos.MPromocion_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.CBPartner;
import org.saram.modelo.COrder;
import org.saram.modelo.COrderLine;
import org.saram.modelo.MProduct;
import org.saram.modelo.MPromocion;
import org.saram.modelo.MPromocionLine;;

/**
 * Servlet implementation class pedidoIngreso
 */
@WebServlet("/busquedaPedidos")
public class busquedaPedidosLinea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public busquedaPedidosLinea() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	protected void service(HttpServletRequest request, HttpServletResponse response)
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
		}
		;
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
			// co = cox.buscarUno(Integer.parseInt(cOrderID));
			String dropShipLocationID = request.getParameter("dropship_location_id");
			if (!dropShipLocationID.equals("999999")) {
				co.setDropship_location_id(Integer.parseInt(dropShipLocationID));
				co.setC_bpartner_location_id(Integer.parseInt(dropShipLocationID));
				co.getDescription().replaceAll(" SELECCIONADO COMO ENTREGA EN SARAM", "");
				co.setEsentregalocal("N");
			} else {
				co.setDropship_location_id(co.getC_bpartner_location_id());
				co.setC_bpartner_location_id(co.getC_bpartner_location_id());
				if (!co.getDescription().contains("SELECCIONADO COMO ENTREGA EN SARAM"))
					co.setDescription(" SELECCIONADO COMO ENTREGA EN SARAM");
				co.setEsentregalocal("S");
			}
			cox.salvarOrden(co);
		}
		PrintWriter out = response.getWriter();
		if (request.getParameter("completar") != null) {
			// verificapromociones}
			List<String> productosv = new ArrayList<String>();
			MPromocion mpro = new MPromocion();
			COrderLine mproIngresada = new COrderLine();
			MPromocion_X mprox = new MPromocion_X();
			COrderLine colp = new COrderLine();
			COrderLine_X colx = new COrderLine_X();
			List<COrderLine> lineas = colx.buscarTodos(co.getC_order_id());
			// por favor valide este pedido que no tiene promocion.6
			boolean fueingresado = true;
			int v1=0,v2=0;
			for (COrderLine l : lineas) {
				if (l.getM_product_id() != null) {
					if (l.getEspromocion() == null) {
						mpro = mprox.buscarTodos(l.getM_product_id(), co.getC_bpartner_id());
						Integer MProductPromo = 0;
						MPromocionLine mproline = new MPromocionLine();
						MPromocionLine_X mprolinex = new MPromocionLine_X();
						if (mpro != null) {
							// Verificamos la clasificacion
							CBPartner_X cbpx = new CBPartner_X();
							CBPartner cbp = new CBPartner();
							
							try {
						
							if (cbp.getT_clasificacion_id()!=null) {
								
							cbp = cbpx.buscarUno(c_bpartner_id != null ? Integer.parseInt(c_bpartner_id) : 0);
							
							if (mpro.getT_clasificacion_id() != null && mpro.getT_clasificacion_id() > 0) {
								if (cbp.getIspromocion().equals("N")) {
									v1=mpro.getT_clasificacion_id();
									v2=cbp.getT_clasificacion_id();
									if (v1==v2) {
										if (l.getQtyentered() > mpro.getQtyPromocion()) {
											// for (COrderLine l2 : lineas) {
											mproIngresada = mprox.buscarPromocionNoIngresada(mpro.getM_product_id(),
													co.getC_order_id());
											System.out.println(mpro.getM_product_id() + " == " + l.getM_product_id());
											MPromocionLine_X mplx = new MPromocionLine_X();
											MPromocionLine mpl = new MPromocionLine();
											mpl = mplx.buscarTodos(mpro.getM_promocion_id());
											if (mpl!=null) {
												fueingresado = false;	
												for (COrderLine l2 : lineas) {
													if (l2.getEspromocion()!=null) {
														if (mpl.getM_product_id().toString().equals(l2.getM_product_id().toString())) {
																fueingresado = true;
														} else
															fueingresado = false;	
													}
												}
											} else {
												if (mpro.getM_product_id().toString().equals(l.getM_product_id().toString())) {
													if (mproIngresada != null) {
														fueingresado = true;
													} else
														fueingresado = false;
													// break;
												} else
													fueingresado = false;
											}
											if (!fueingresado) {
												MProduct p = new MProduct();
												MProduct_X px = new MProduct_X();
												p = px.buscarUno(l.getM_product_id());
												productosv.add(p.getName());
											}
										}
									}
								}
								
							} else {
								if (cbp.getIspromocion().equals("N")) {
									if (l.getQtyentered() >= mpro.getQtyPromocion()) {
										// for (COrderLine l2 : lineas) {
										mproIngresada = mprox.buscarPromocionNoIngresada(l.getM_product_id(),
												co.getC_order_id());
										System.out.println(mpro.getM_product_id() + " == " + l.getM_product_id());
										if (mpro.getM_product_id().toString().equals(l.getM_product_id().toString())) {
											if (mproIngresada != null) {
												fueingresado = true;
											} else
												fueingresado = false;
											// break;
										} else
											fueingresado = false;
										if (!fueingresado) {
											MProduct p = new MProduct();
											MProduct_X px = new MProduct_X();
											p = px.buscarUno(l.getM_product_id());
											productosv.add(p.getName());
										}
									}
								}
								
							}
						}
						}
							catch(Exception e){
								
							}
					}
					}
				}
			}
			if (productosv.size() > 0) {
				// String mensaje ="";
				// for (String l3:productosv) {
				// System.out.println("******" + l3);
				// }
				// mensaje = mensaje + "</li></ul>";
				request.setAttribute("product", productosv);
				request.setAttribute("ad_user_id", adId);
				request.setAttribute("c_bpartner_id", c_bpartner_id);
				request.setAttribute("c_order_id", cOrderID);
				request.getRequestDispatcher("/vuelvaingresarpedido.jsp").forward(request, response);
				// response.sendRedirect("vuelvaingresarpedido.jsp");
				return;
			} else {
				request.getRequestDispatcher("/espera.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id
						+ "&c_order_id=" + cOrderID + "&solicitud=0").forward(request, response);
				return;
			}
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
			CBPartner_X cbx = new CBPartner_X();
			CBPartner cb = new CBPartner();
			cb = cbx.buscarUno(c_bpartner_id != null ? Integer.parseInt(c_bpartner_id) : 0);
			// Mail pedidos
			String mensaje;
			mensaje = "SE ACCEDIO A LA OPCION SOLICITAR " + cb.getName() + " en el pedido " + co.getDocumentno();
			enviarCorreos(mensaje, "informatica1@mor.com.sv");
			// Mail
			request.getRequestDispatcher("/pedido_c.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id
					+ "&c_order_id=" + cOrderID + "&solicitud=1").forward(request, response);
			return;
		}
		Integer flete = 0;
		Integer cantidad = 0;
		// out.println("<html>");
		// out.println("<head>");
		// out.println("<script type='text/javascript'>");
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("/js/procesando.js");
		// out.println("</script>");
		// out.println("<style>");
		// RequestDispatcher dispatcher2 =
		// request.getRequestDispatcher("/css/procesando.css");
		// out.println("</style>");
		// out.println("</head>");
		// out.println("<body>");

		List<COrder> coDetenido = cox.buscarUnoDetenido(adId, cOrderID);
		if (coDetenido.size() > 0) {
			request.getRequestDispatcher("/error.html").forward(request, response);
		} else {
			if (adl.size() != 0) {
				List s;
				List mst;
				sqlHQL_X mp = new sqlHQL_X();
				CBPartner_X cbx = new CBPartner_X();
				CBPartner cb = new CBPartner();
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				DecimalFormat df = new DecimalFormat("###,###,##0.00");
				out.println(
						"<div class=\"table-responsive\"><table class=\"table table-hover\" style=\"table-layout: fixed;\">");
				out.println(
						"<thead class=\"thead-inverse\"><tr><th width=\"50%\">Productos</th><th width=\"15%\">Cant</th><th width=\"15%\">Precio ($)</th><th width=\"20%\">Subtotal ($)</th></tr></thead><tbody>");

				for (COrderLine coi : adl) {
					s = mp.buscarUno("SELECT name FROM m_product WHERE m_product_id =" + "" + coi.getM_product_id());
					mst = mp.buscarUno(
							"SELECT sum(qtyonhand) FROM m_storage WHERE m_locator_id = 1000023 and m_product_id =" + ""
									+ coi.getM_product_id());
					// out.println("<li>");
					if (coi.getPriceentered() != null & coi.getM_product_id() != null) {
						if (coi.getPriceactual() <= 0.03) {
							out.println("<tr><th scope=\"row\">");
							out.println("PROMOCION " + s.get(0).toString());
							// out.println(s.get(0).toString());
							out.println("</th><td>");
							out.println(coi.getQtyentered() + "</td><td>" + df.format(coi.getPriceactual())
									+ "</td><td>" + df.format(coi.getPriceactual() * coi.getQtyentered()));
							// out.println("CANTIDAD EN INVENTARIO:" +
							// mst.get(0).toString());
							out.println("</td>");
							// out.println("</a>");
						} else if (coi.getM_product_id() != null) {
							cantidad = 1;
							out.println("<tr><th scope=\"row\"><a " + "href=\"pedido_linea_m.jsp?" + "ad_user_id="
									+ adId + "" + "&c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id
									+ "&c_orderline_id=" + coi.getC_orderline_id() + "&m_product_id="
									+ coi.getM_product_id() + "\" target=\"_self\"/>");
							try {
								out.println(s.get(0).toString());
							} catch(Exception e) {
								
							}
							out.println("</a></th><td>");
							out.println(coi.getQtyentered() + "</td><td>" + df.format(coi.getPriceactual())
									+ "</td><td>" + df.format(coi.getPriceactual() * coi.getQtyentered()));
							// out.println("<br><FONT SIZE=4 COLOR=BLUE><b>CANTIDAD
							// EN INVENTARIO:" + mst.get(0).toString());
							out.println("</td>");
						}
					} else {
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
								+ df.format(coi.getLinenetamt()));
						out.println("</td>");
						// out.println("</a>");
					}
					// out.println("</li>");
				}
				if (cantidad == 1) {
					out.println("</tbody><tfoot><tr><th colspan=\"4\">");
					co = cox.buscarUno(Integer.parseInt(cOrderID));
					// out.println("</li>");
					out.println("IVA DE FACTURA: $" + df.format(co.getGrandtotal() - co.getTotallines()));
					out.println("</tr></th><tr><th colspan=\"4\">");
					out.println("TOTAL DE LINEAS: $" + df.format(co.getTotallines()));
					cb = cbx.buscarUno(c_bpartner_id != null ? Integer.parseInt(c_bpartner_id) : 0);
					if (cb.getLco_isic_id() == 1000417 && cb.getLco_taxpayertype_id() == 1000006
							&& co.getTotallines() > 100
							|| cb.getLco_isic_id() == 1000417 && cb.getLco_taxpayertype_id() == 1000007
									&& co.getTotallines() > 100) {
						out.println("</tr></th><tr><th colspan=\"4\">");
						out.println("TOTAL DE PERCEPCION: $" + df.format(co.getTotallines() * 0.01));
						out.println("</tr></th><tr><th colspan=\"4\">");
						out.println("TOTAL DE FACTURA: $" + df.format(co.getGrandtotal()+(co.getTotallines() * 0.01)));

					} else {
						out.println("</tr></th><tr><th colspan=\"4\">");
						out.println("TOTAL DE FACTURA: $" + df.format(co.getGrandtotal()));
					}
					s = mp.buscarUno(
							"SELECT sum(qtyentered) FROM c_orderline WHERE m_product_id is not null and c_order_id ="
									+ co.getC_order_id());
					String ss = s.toString();
					ss = ss.replace("[", "");
					ss = ss.replace("]", "");
					out.println("</tr></th><tr><th colspan=\"4\">");
					out.println("TOTAL DE CANTIDAD: " + ss);
					out.println("</tr></th><tr>");
					s = mp.buscarUno(
							"SELECT count(c_orderline_id) FROM c_orderline WHERE c_order_id =" + co.getC_order_id());
					ss = s.toString();
					ss = ss.replace("[", "");
					ss = ss.replace("]", "");
					out.println("</tr></th><tr><th colspan=\"4\">");
					out.println("TOTAL DE LINEAS DE PEDIDO: " + ss);
					out.println("</th><tr/>");
					// out.println("PERSEPCION: $" + df.format(co.getGrandtotal())
					// );
				}
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
			out.println("<a href=\"busquedaProducto.jsp?c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id
					+ "&ad_user_id=" + adId + "\" target=\"_self\" class=\"greenButton\">REGISTRAR PRODUCTO</a>");
			if (cantidad == 1)
				out.println("<a href=\"busquedaPedidos?c_order_id=" + cOrderID + "&ad_user_id=" + adId
						+ "&c_bpartner_id=" + c_bpartner_id
						+ "&completar=1\" target=\"_self\" class=\"blueButton\">COMPLETAR PEDIDO</a>");
			// out.println("<a "
			// + "class=\"greenButton\" id=\"completar\" name=\"completar\"
			// value=\"COMPLETAR PEDIDO\""
			// + "href=\"busquedaPedidos?c_order_id="+ cOrderID +
			// "&c_bpartner_id="+c_bpartner_id+"\" target=\"_self\" "
			// + "id=\"completar\" name=\"completar\" class=\"completapedido\"/>");
			// out.println("<input type=\"submit\" class=\"greenButton\"
			// id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\"
			// id=\"btnLeft\" />");
			out.println("&nbsp;&nbsp;");
			// if (flete == 0) {
			// out.println("<a href=\"busquedaCargo.jsp?c_order_id=" + cOrderID +
			// "&c_bpartner_id=" + c_bpartner_id
			// + "&ad_user_id=" + adId + "\" target=\"_self\"
			// class=\"greenButton\">REGISTRAR FLETE</a>");
			// }
			out.println("</div>");
			out.println("<div class=\"toolbar\">");
			// out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId
			// +"&c_bpartner_id="+ c_bpartner_id +"&c_order_id="+ cOrderID +"\"
			// class=\"blueButton\">Regresar a pedido</a>");
			// out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\"
			// target=\"_self\" class=\"greenButton\">Registrar</a>");
			out.println("<a href=\"pedido_m.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id + "&c_order_id="
					+ cOrderID + "\" class=blueButtonRegresar>Regresar</a>");
			out.println("<h1 id=\"pageTitle\">Lineas</h1>");
			out.println("</div>");
			// TODO Auto-generated method stub
		}
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

	public boolean enviarCorreos2(String texto, String correo) {
		return true;
	}

	public boolean enviarCorreos(String texto, String correo) {
		String para = correo;
		String de = "informatica1@mor.com.sv";
		String host = "mor.com.sv";
		Properties propiedades = System.getProperties();
		propiedades.setProperty("mail.smtp.host", host);
		propiedades.setProperty("mail.user", "informatica1@mor.com.sv");
		propiedades.setProperty("mail.password", "j0se.castaneda");
		propiedades.setProperty("mail.port", "26");
		Session sesion = Session.getDefaultInstance(propiedades);
		try {
			// Creamos un objeto mensaje tipo MimeMessage por
			// defecto.
			MimeMessage mensaje = new MimeMessage(sesion);
			// Asignamos el “de o from” al header del correo.
			mensaje.setFrom(new InternetAddress(de));

			// Asignamos el “para o to” al header del correo.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
			mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress("ingenieria2@mor.com.sv"));

			// Asignamos el asunto
			// mensaje.setSubject("**PRUEBA DE PEDIDOS++ NOTIFICACION CLIENTE EN
			// ESPERA");
			// \u00f1 -> ñ

			// Asignamos el contenido HTML, tan grande como
			// nosotros queramos
			// first part (the html)
			// This mail has 2 part, the BODY and the embedded
			// image
			// MimeMultipart multipart = new MimeMultipart("related");
			// // first part (the html)
			// BodyPart messageBodyPart = new MimeBodyPart();
			// String htmlText = "<img src=\"cid:image\">";
			// messageBodyPart.setContent(htmlText, "text/html");
			// // add it
			// multipart.addBodyPart(messageBodyPart);
			// // second part (the image)
			// messageBodyPart = new MimeBodyPart();
			// DataSource fds = new FileDataSource("img/tarjetas/fc_02.jpg");
			// messageBodyPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setHeader("Content-ID", "<image>");
			// // add image to the multipart
			// multipart.addBodyPart(messageBodyPart);
			// multipart.addBodyPart(messageBodyPart);
			// mensaje.setContent(multipart);
			// Create a default MimeMessage object.
			// MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			mensaje.setFrom(new InternetAddress(de));

			// Set To: header field of the header.
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

			// Set Subject: header field
			mensaje.setSubject("**PEDIDO GENERADO POR EL SISTEMA MOVIL** NOTIFICACION DE PEDIDOS");

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

// otra clase
/*
 * package org.saram.busquedas;
 * 
 * import java.io.IOException; import java.io.PrintWriter; import
 * java.text.DecimalFormat; import java.util.List;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.saram.accesos.CBPartner_X; import org.saram.accesos.COrderLine_X;
 * import org.saram.accesos.COrderTax_X; import org.saram.accesos.COrder_X;
 * import org.saram.accesos.sqlHQL_X; import org.saram.modelo.CBPartner; import
 * org.saram.modelo.COrder; import org.saram.modelo.COrderLine;;
 * 
 * @WebServlet("/busquedaPedidos") public class busquedaPedidosLinea extends
 * HttpServlet { private static final long serialVersionUID = 1L; public
 * busquedaPedidosLinea() { super(); // TODO Auto-generated constructor stub }
 * 
 * @SuppressWarnings("rawtypes") protected void service(HttpServletRequest
 * request, HttpServletResponse response) throws ServletException, IOException {
 * COrderLine_X cp = new COrderLine_X(); COrder_X cx = new COrder_X(); String
 * cOrderID; String adId = request.getParameter("ad_user_id"); String
 * c_bpartner_id = request.getParameter("c_bpartner_id"); COrder_X cox = new
 * COrder_X(); COrder co = new COrder(); if (request.getParameter("c_order_id")
 * == null) { cOrderID = ""; } else { cOrderID =
 * request.getParameter("c_order_id"); } ; List<COrderLine> adl =
 * cp.buscarTodos(Integer.parseInt(cOrderID)); if
 * (request.getParameter("EliminarPedido") != null) { COrderTax_X cot = new
 * COrderTax_X(); COrderLine_X col = new COrderLine_X(); Long error; error =
 * cot.eliminarTodos(Integer.parseInt(cOrderID)); error =
 * col.eliminarTodos(Integer.parseInt(cOrderID)); error =
 * cox.eliminarUno(Integer.parseInt(cOrderID));
 * request.getRequestDispatcher("pedido?ad_user_id=" + adId).forward(request,
 * response); return; } if (request.getParameter("guardarPedido") != null) {
 * request.getRequestDispatcher("pedidoIngreso?ad_user_id=" +
 * adId).forward(request, response); return; } PrintWriter out =
 * response.getWriter(); if (request.getParameter("completar") != null) {
 * sqlHQL_X sqlx = new sqlHQL_X(); CBPartner_X cbx = new CBPartner_X();
 * CBPartner cb = new CBPartner(); cb =
 * cbx.buscarUno(c_bpartner_id!=null?Integer.parseInt(c_bpartner_id):0); co =
 * cox.buscarUno(cOrderID == null ? 0 : Integer.parseInt(cOrderID));
 * List<Object> mLocatorObject = sqlx
 * .buscarUno("select m_locator_id from m_locator where isactive='Y' and M_warehouse_id = "
 * + co.getM_warehouse_id()); Integer mLocatorID = (mLocatorObject == null ? 0 :
 * Integer.parseInt(mLocatorObject.get(0).toString())); for (COrderLine coi :
 * adl) { if (coi.getM_product_id() != null) {
 * sqlx.sql("UPDATE m_storage SET qtyreserved = qtyreserved + " +
 * coi.getQtyentered() + " WHERE m_product_id = " + coi.getM_product_id() + " "
 * + "AND m_attributesetinstance_id = 0 AND m_locator_id = " + mLocatorID); }
 * coi.setProcessed("Y"); cp.salvarOrden(coi); } co.setDocstatus("CO");
 * co.setDocaction("CL"); co.setProcessed("Y"); cx.salvarOrden(co); Float value
 * = new Float(1); if (cb.getSo_creditlimit()!=0){ value =
 * cb.getSo_creditlimit()-cb.getSo_creditused() - co.getGrandtotal(); }
 * System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE"); if (value>0){
 * request.getRequestDispatcher( "/pedido_c.jsp?ad_user_id=" + adId +
 * "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=0")
 * .forward(request, response); } else { request.getRequestDispatcher(
 * "/pedido_n.jsp?ad_user_id=" + adId + "&c_bpartner_id=" + c_bpartner_id +
 * "&c_order_id=" + cOrderID+"&solicitud=0") .forward(request, response); }
 * return; } if (request.getParameter("solicitar") != null) { sqlHQL_X sqlx =
 * new sqlHQL_X(); co = cox.buscarUno(cOrderID == null ? 0 :
 * Integer.parseInt(cOrderID)); List<Object> mLocatorObject = sqlx
 * .buscarUno("select m_locator_id from m_ocator where isactive='Y' and m_warehourse = "
 * + co.getM_warehouse_id()); Integer mLocatorID = (mLocatorObject == null ? 0 :
 * Integer.parseInt(mLocatorObject.get(0).toString())); for (COrderLine coi :
 * adl) { if (coi.getM_product_id() != null) {
 * sqlx.sql("UPDATE m_storage SET qtyreserved = qtyreserved + " +
 * coi.getQtyentered() + " WHERE m_product_id = " + coi.getM_product_id() + " "
 * + "AND m_attributesetinstance_id = 0 AND m_locator_id = " + mLocatorID); }
 * coi.setProcessed("Y"); cp.salvarOrden(coi); } co.setDocstatus("CO");
 * co.setDocaction("CL"); co.setProcessed("Y"); cx.salvarOrden(co);
 * System.out.println("ALERTA: CANTIDAD RESERVADA EXITOSAMENTE");
 * 
 * request.getRequestDispatcher( "/pedido_c.jsp?ad_user_id=" + adId +
 * "&c_bpartner_id=" + c_bpartner_id + "&c_order_id=" + cOrderID+"&solicitud=1")
 * .forward(request, response); return; } Integer flete = 0; Integer cantidad =
 * 0; if (adl.size() != 0) {
 * 
 * out.println("<ul selected=\"true\" title=\"Pedido\">"); List s; List mst;
 * sqlHQL_X mp = new sqlHQL_X();
 * response.setContentType("text/html;charset=UTF-8");
 * request.setCharacterEncoding("UTF-8"); DecimalFormat df = new
 * DecimalFormat("###,###,###.00"); for (COrderLine coi : adl) { s =
 * mp.buscarUno("SELECT name FROM m_product WHERE m_product_id =" + "" +
 * coi.getM_product_id()); mst = mp.
 * buscarUno("SELECT sum(qtyonhand) FROM m_storage WHERE m_locator_id = 1000023 and m_product_id ="
 * + "" + coi.getM_product_id()); out.println("<li>"); if (coi.getPriceentered()
 * != null & coi.getM_product_id()!=null) { if (coi.getPriceactual() <=0.03){
 * out.println( "<a " + "href=\"pedido_linea_m.jsp?" + "ad_user_id=" + adId + ""
 * + "&c_order_id=" + cOrderID + "&c_bpartner_id=" + c_bpartner_id +
 * "&c_orderline_id=" + coi.getC_orderline_id() + "&m_product_id=" +
 * coi.getM_product_id() + "\" target=\"_self\"/>");
 * out.println("PRODUCTO PROMOCIONAL - " + s.get(0).toString()); //
 * out.println(s.get(0).toString()); out.println("<div class=\"secondary\">");
 * out.println("CANTIDAD: " + coi.getQtyentered() + " PRECIO: $" +
 * df.format(coi.getPriceactual()) + " TOTAL: $" +
 * df.format(coi.getPriceactual() * coi.getQtyentered()));
 * out.println("CANTIDAD EN INVENTARIO:" + mst.get(0).toString());
 * out.println("</div>"); out.println("</a>"); } else if (coi.getM_product_id()
 * != null) { cantidad = 1; out.println( "<a " + "href=\"pedido_linea_m.jsp?" +
 * "ad_user_id=" + adId + "" + "&c_order_id=" + cOrderID + "&c_bpartner_id=" +
 * c_bpartner_id + "&c_orderline_id=" + coi.getC_orderline_id() +
 * "&m_product_id=" + coi.getM_product_id() + "\" target=\"_self\"/>");
 * out.println(s.get(0).toString()); out.println("<div class=\"secondary\">");
 * out.println("CANTIDAD: " + coi.getQtyentered() + " PRECIO: $" +
 * df.format(coi.getPriceactual()) + " TOTAL: $" +
 * df.format(coi.getPriceactual() * coi.getQtyentered()));
 * out.println("<br><FONT SIZE=4 COLOR=BLUE><b>CANTIDAD EN INVENTARIO:" +
 * mst.get(0).toString()); out.println("</b></font></div>");
 * out.println("</a>"); }} else { flete = 1; s =
 * mp.buscarUno("SELECT name FROM c_charge WHERE c_charge_id =" +
 * coi.getC_charge_id()); out.println("<a " + "href=\"pedido_linea_f_m.jsp?" +
 * "ad_user_id=" + adId + "" + "&c_order_id=" + cOrderID + "&c_bpartner_id=" +
 * c_bpartner_id + "&c_orderline_id=" + coi.getC_orderline_id() +
 * "&c_charge_id=" + coi.getC_charge_id() + "\" target=\"_self\"/>"); if
 * (coi.getC_charge_id() == 1004412) { out.println("CUOTA AVE "); } else {
 * out.println("FLETE - " + s); } out.println("<div class=\"secondary\">");
 * out.println("CANTIDAD: " + coi.getQtyentered() + " PRECIO: $" +
 * coi.getPriceactual() + " TOTAL: $" + df.format(coi.getPriceactual() *
 * coi.getQtyentered())); out.println("</div>"); out.println("</a>"); }
 * out.println("</li>"); } if (cantidad == 1){ out.println("<li>"); co =
 * cox.buscarUno(Integer.parseInt(cOrderID)); out.println("TOTAL DE FACTURA: $"
 * + df.format(co.getGrandtotal()) ); out.println("</li>"); }
 * out.println("</ul>"); } else {
 * out.println("<ul selected=\"true\" title=\"Mobile SARAM\">");
 * out.println("<li>"); out.println("<div class=\"secondary\">");
 * out.println("SIN PRODUCTOS/FLETE"); out.println("</div>");
 * out.println("</li>"); out.println("</ul>"); }
 * out.println("<div class=\"footer\">"); //
 * out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId // +"&c_bpartner_id="+
 * c_bpartner_id +"&c_order_id="+ cOrderID +"\" //
 * class=\"blueButton\">Regresar a pedido</a>"); //
 * out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\" //
 * target=\"_self\" class=\"greenButton\">Registrar</a>");
 * out.println("<a href=\"busquedaProducto.jsp?c_order_id=" + cOrderID +
 * "&c_bpartner_id=" + c_bpartner_id + "&ad_user_id=" + adId +
 * "\" target=\"_self\" class=\"greenButton\">REGISTRAR PRODUCTO</a>"); if
 * (cantidad == 1) out.println("<a href=\"busquedaPedidos?c_order_id="+ cOrderID
 * + "&c_bpartner_id="+
 * c_bpartner_id+"&completar=1\" target=\"_self\" class=\"greenButton\">COMPLETAR PEDIDO</a>"
 * ); // out.println("<a " // +
 * "class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\""
 * // + "href=\"busquedaPedidos?c_order_id="+ cOrderID +
 * "&c_bpartner_id="+c_bpartner_id+"\" target=\"_self\" " // +
 * "id=\"completar\" name=\"completar\" class=\"completapedido\"/>"); // out.
 * println("<input type=\"submit\" class=\"greenButton\" id=\"completar\" name=\"completar\" value=\"COMPLETAR PEDIDO\" id=\"btnLeft\" />"
 * ); out.println("&nbsp;&nbsp;"); // if (flete == 0) { //
 * out.println("<a href=\"busquedaCargo.jsp?c_order_id=" + cOrderID +
 * "&c_bpartner_id=" + c_bpartner_id // + "&ad_user_id=" + adId +
 * "\" target=\"_self\" class=\"greenButton\">REGISTRAR FLETE</a>"); // }
 * out.println("</div>"); out.println("<div class=\"toolbar\">"); //
 * out.println("<a href=\"pedido_m.jsp?ad_user_id="+ adId // +"&c_bpartner_id="+
 * c_bpartner_id +"&c_order_id="+ cOrderID +"\" //
 * class=\"blueButton\">Regresar a pedido</a>"); //
 * out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\" //
 * target=\"_self\" class=\"greenButton\">Registrar</a>");
 * out.println("<a href=\"pedido_m.jsp?ad_user_id=" + adId + "&c_bpartner_id="+
 * c_bpartner_id + "&c_order_id="+ cOrderID +
 * "\" class=blueButtonRegresar>Regresar</a>");
 * out.println("<h1 id=\"pageTitle\">Lineas de pedido</h1>");
 * out.println("</div>"); // TODO Auto-generated method stub return; }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // TODO Auto-generated
 * method stub
 * response.getWriter().append("Served at: ").append(request.getContextPath());
 * }
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // TODO Auto-generated
 * method stub doGet(request, response); }
 * 
 * }
 * 
 */
