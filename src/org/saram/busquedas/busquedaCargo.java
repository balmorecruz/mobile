package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.CCharge_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.CCharge;
import org.saram.modelo.MProduct;;

/**
 * Servlet implementation class pedidoIngreso
 */
@WebServlet("/busquedaCargos")
public class busquedaCargo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busquedaCargo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CCharge_X cp = new CCharge_X();
		String adId = request.getParameter("ad_user_id");
		String co = request.getParameter("c_order_id");
		String c_bpartner_id = request.getParameter("c_bpartner_id");
		CCharge cCharge = new CCharge();
		String name = "";
//		if(request.getParameter("mProductID")!=null){mProductID.setValue(request.getParameter("mProductID"));};
		if(request.getParameter("description")!=null){name=(request.getParameter("description"));}
		List<CCharge> adl = cp.buscarUno(name);
		PrintWriter out = response.getWriter();
		if (adl.size() != 0) {
			response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
			out.println("<ul selected=\"true\" title=\"Pedido\">");
//	        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
//	        out.println("<head>");
//	        out.println("<title>Mobile SARAM</title>");
//	        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
//	        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\" />");
//	        out.println("<link rel=\"stylesheet\" href=\"" +  request.getContextPath() +"/iui/iui.css\" type=\"text/css\" />");
//	        out.println("<link rel=\"stylesheet\" href=\"" +  request.getContextPath() +"/recursos/saram.css\" type=\"text/css\" />");
//	        out.println("<link rel=\"stylesheet\" href=\"" +  request.getContextPath() +"/iui/t/default/default-theme.css\" type=\"text/css\" />");
//	        out.println("<script type=\"application/x-javascript\" src=\"" +  request.getContextPath() +"/iui/iui.js\"></script>");
//	        out.println("<script type=\"text/javascript\" src=\"" +  request.getContextPath() +"/js/calendar.js\" language=\"JavaScript1.2\"></script>");
//	        out.println("<link href=\"" +  request.getContextPath() +"/css/calendar-blue.css\" type=\"text/css\" rel=\"stylesheet\" />");
//	        out.println("<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
//			out.println("</head><body>");
//	        out.println("<ul selected=\"true\" title=\"Pedido\">");
//	        out.println("<form id=\"screen1\" title=\"Mobile SARAM\" class=\"panel\" name=\"formname\" "
//	        		+ "action=\"busquedaPedidos method=\"post\" selected=\"true\">");
			List s;
			sqlHQL_X mp = new sqlHQL_X();
			for (CCharge mp1 : adl) {
				out.println("<li>");
				out.println("<a "
						+ "href=\"pedido_linea_f_i.jsp?"
						+ "ad_user_id="+ adId +""
						+ "&c_bpartner_id="+ c_bpartner_id 
						+ "&c_order_id=" +co
								+ "&c_charge_id="+ mp1.getC_charge_id()
								+ "&value="+ mp1.getDescription() +
								"&m_product_name="+ mp1.getName() +
								 "\" target=\"_self\"/>");
				out.println(mp1.getName());
				out.println("<div class=\"secondary\">");
				out.println(mp1.getDescription() + " Precio: "+ mp1.getChargeamt());
				out.println("</div>");
				out.println("</a>");
				out.println("</li>");
			}
			out.println("</ul>");
		} else {
			out.println("<ul selected=\"true\" title=\"Mobile SARAM\">");
			out.println("<li>");
			out.println("<div class=\"secondary\">");
			out.println("NO HAY DESTINOS");
			out.println("</div>");
			out.println("</li>");
			out.println("</ul>");
		}
//		out.println("</form>");
		out.println("<div class=\"footer\">");
		out.println("<a href=\"WFindAdv\" class=\"blueButton\">Buscar</a>");
//		out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\" target=\"_self\" class=\"greenButton\">Registrar</a>");
//		out.println("<a href=\"busquedaProducto.jsp?ad_user_id="+ adId +"\" target=\"_self\" class=\"greenButton\">Registrar</a>");
		out.println("</div>");
//		out.println("</body>");
		// TODO Auto-generated method stub
		return;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
