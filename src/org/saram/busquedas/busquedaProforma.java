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
import org.saram.accesos.COrder_X;
import org.saram.modelo.AdUser;
import org.saram.modelo.CBPartner;
import org.saram.modelo.COrder;

/**
 * Servlet implementation class pedido
 */
@WebServlet(name = "busquedaProforma", description = "Principal de pedidos", urlPatterns = { "/busquedaProforma" })
public class busquedaProforma extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String usuario;
    private String c_order_id;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public busquedaProforma() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#HttpServlet()
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("ad_user_id") == null) {
			if (request.getAttribute("ad_user_id") == null) {
				usuario = request.getParameter("usuario");
			} else {
				usuario = request.getAttribute("ad_user_id").toString();
			}
		} else {
			usuario = request.getParameter("ad_user_id");
		}
//		String c_bpartner_id = request.getParameter("c_bpartner_id");
//		COrder_X cox1 = new COrder_X();
//		COrder co1 = new COrder();
		if (request.getParameter("c_order_id") == null) {
			c_order_id = "";
		} else {
			c_order_id = request.getParameter("c_order_id");
		}
		;
//		co1 = cox1.buscarUno(c_order_id == null ? 0 : Integer.parseInt(c_order_id));
		if (usuario != null) {
			COrder_X cox = new COrder_X();
			AdUser ad = new AdUser();
			ad.setAd_user_id(Integer.parseInt(usuario));
			request.setAttribute("ad_user_id", usuario);
			List<COrder> co = cox.buscarProformas(usuario, c_order_id);
			System.out.println("TAMA�O PROFORMAS:"+co.size());
			CBPartner cb = new CBPartner();
			CBPartner_X cbx = new CBPartner_X();
			DecimalFormat df = new DecimalFormat("###,###,###.##");
			out.println("<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"/>"
					+ "<link rel=\"stylesheet\" href=\"/mobile/iui/iui.css\" type=\"text/css\" />"
					+ "<link rel=\"stylesheet\" href=\"/mobile/recursos/saram.css\" type=\"text/css\" />"
					+ "<link rel=\"stylesheet\" href=\"/mobile/iui/t/default/default-theme.css\"  type=\"text/css\"/>"
					+ "<script type=\"application/x-javascript\" src=\"/mobile/iui/iui.js\"></script><body>");
			out.println("<div class=\"toolbar\">");
			out.println("<h1 id=\"pageTitle\">Proformas generadas</h1>");
			out.println("</div>");
			if (co!=null){
			if (co.size() != 0) {
				out.println("<ul selected=\"true\" title=\"Proforma\">");
				for (COrder coi : co) {
					cb = cbx.buscarUno(coi.getC_bpartner_id());
					out.println("<li>");
					out.println("<a href=\"pedido_m_v.jsp?ad_user_id=" + ad.getAd_user_id() + "&c_bpartner_id="
							+ coi.getC_bpartner_id() + "&c_order_id=" + coi.getC_order_id() + "\" target=\"_self\">");
					out.println(coi.getDocumentno());
					out.println("<div class=\"secondary\">");
					out.println("Cliente: " + cb.getName());
					out.print("<br/>CREADO: " + coi.getCreated());
					out.println("</div>");
					out.println("<div class=\"secondary\">");
					out.println("<b>GRAN TOTAL: $" + df.format(coi.getGrandtotal()));
					out.println("TOTAL DE LINEA: $" + df.format(coi.getTotallines()));
					out.println("</b></div>");
					out.println("</a>");
					out.println("</li>");
				}
				out.println("</ul>");
			} else {
				out.println("<ul selected=\"true\" title=\"Mobile SARAM\">");
				out.println("<li>");
				out.println("SIN PEDIDOS EN PROCESO");
				out.println("</a>");
				out.println("</li>");
				out.println("</ul>");
			}
			} else { 
				out.println("<ul selected=\"true\" title=\"Mobile SARAM\">");
				out.println("<li>");
				out.println("SIN PEDIDOS EN PROCESO");
				out.println("</a>");
				out.println("</li>");
				out.println("</ul>");
			}
//			out.println("<div class=\"footer\">");
//			out.println("<a href=\"login.jsp\" class=\"blueButton\">CERRAR SESION</a>");
//
//			out.println("<a href=\"tipo_pedido.jsp?ad_user_id=" + usuario
//					+ "\" target=\"_self\" class=\"greenButton\">HACER PEDIDO</a>");
//			out.println("</div>");
			out.println("<div class=\"toolbar\">");
			out.println("<a href=\"pedido?ad_user_id="+ usuario +"\" class=blueButtonRegresar>Regresar</a><h1 id=\"pageTitle\">Por Aprobar</h1>");
			out.println("</div>");
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setAttribute("ad_user_id", usuario);
		// request.getRequestDispatcher("pedidoIngreso").forward(request,
		// response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// request.setAttribute("ad_user_id", usuario);
		// request.getRequestDispatcher("pedidoIngreso").forward(request,
		// response);
		// doGet(request, response);
	}

}
