package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletContext;
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
@WebServlet(name = "pedido_main", description = "Principal de pedidos", urlPatterns = { "/pedido" })
public class busquedaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String usuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public busquedaPedido() {
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
		if (usuario != null) {
			COrder_X cox = new COrder_X();
			AdUser ad = new AdUser();
			ad.setAd_user_id(Integer.parseInt(usuario));
			request.setAttribute("ad_user_id", usuario);
			List<COrder> co = cox.buscarTodos(ad);
			CBPartner cb = new CBPartner();
			CBPartner_X cbx = new CBPartner_X();
			DecimalFormat df = new DecimalFormat("###,###,###.##");

			out.println("<script type='application/x-javascript' src='iui/iui.js'></script>");

			
			out.println("<div class=\"toolbar\">");
			out.println("<h1 id=\"pageTitle\">Pedidos</h1>");
			out.println("</div>");
			if (co!=null){
			if (co.size() != 0) {
				out.println("<ul selected=\"true\" title=\"Pedido\">");
				for (COrder coi : co) {
					cb = cbx.buscarUno(coi.getC_bpartner_id());
					out.println("<li>");
					out.println("<a href=\"pedido_m.jsp?ad_user_id=" + usuario + "&c_bpartner_id="
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
			out.println("<div class=\"footer\">");
//			out.println("<a href=\"busqueda.jsp?ad_user_id=" + usuario
//					+ "\" target=\"_self\" class=\"greenButton\">HACER UN PEDIDO</a>");
			
			out.println("<a href=\"tipo_pedido.jsp?ad_user_id=" + usuario
					+ "\" target=\"_self\" class=\"greenButton\">HACER PEDIDO</a>");
			out.println("<a href=\"login.jsp\" class=\"blueButton\">CERRAR SESION</a>");
			out.println("</div>");
			
			//MENU PRINCIPAL
			out.println("<div class=\"toolbar\">");
			out.println("<a href=\"busquedaPedidoV?ad_user_id=" + usuario
					+ "\" target=\"_self\" class=\"backButton\">POR FACTURAR</a>");
			out.println("<a href=\"busquedaPedidoV_D?ad_user_id=" + usuario
					+ "\" target=\"_self\" class=\"backButton\">POR APROBAR</a>");
			out.println("<a href=\"busquedaPedidoV_G?ad_user_id=" + usuario
					+ "\" target=\"_self\" class=\"backButton\">FACTURADO</a>");
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
