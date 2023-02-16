package org.saram.controladores;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.CBPartner_X;
import org.saram.modelo.CBPartner;;

/**
 * Servlet implementation class pedidoIngreso
 */
@WebServlet(name = "antesDePedido", description = "Principal de pedidos", urlPatterns = { "/antesDePedido" })
public class antesDePedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public antesDePedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	String valor;
	String m_warehouse_id;
	String nameco;
	String CBPartnerID;
	@SuppressWarnings("unused")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			valor = request.getParameter("ad_user_id").toString();
			m_warehouse_id = request.getParameter("m_warehouse_id").toString();
			CBPartner_X cb = new CBPartner_X();
			CBPartner c = new CBPartner();
			c = cb.buscarUnoPorAd(Integer.parseInt(valor));
			String s = (String)new String(c.getName().getBytes(Charset.forName("ISO-8859-1")), 
					Charset.forName("ISO-8859-1"));
			nameco =   s;
			CBPartnerID = c.getC_bpartner_id().toString();
			request.getRequestDispatcher("/pedido_i.jsp?ad_user_id=" + valor + 
					"&cb_partner_id="+ CBPartnerID +""
							+ "&m_warehouse_id="+ m_warehouse_id 
							+"&nameco="+ nameco).forward(request, response);
		} catch(Exception e) {
			
		}
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
