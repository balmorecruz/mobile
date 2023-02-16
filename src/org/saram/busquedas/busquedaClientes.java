package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

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
@WebServlet("/busqueda")
public class busquedaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busquedaClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CBPartner_X cp = new CBPartner_X();
		CBPartner ad = new CBPartner();
		String name, name2, value, taxid;
		String adId = request.getParameter("ad_user_id");
		String mWareHouseID = "";
		if (request.getParameter("m_warehouse_id") != null) mWareHouseID=request.getParameter("m_warehouse_id");
		if(request.getParameter("name")==null){name="";}else{name=request.getParameter("name");};
		if(request.getParameter("name2")==null){name2="";}else{name2=request.getParameter("name2");};
		if(request.getParameter("value")==null){value="";}else{value=request.getParameter("value");};
		if(request.getParameter("taxid")==null){taxid="";}else{taxid=request.getParameter("taxid");}
		name = name.replace(" ", "%') and upper(translate (name, '·ÈÌÛ˙¡…Õ”⁄‰ÎÔˆ¸ƒÀœ÷‹Ò', 'aeiouAEIOUaeiouAEIOU—')) like upper('%");
		name = "upper(translate (name, '·ÈÌÛ˙¡…Õ”⁄‰ÎÔˆ¸ƒÀœ÷‹Ò', 'aeiouAEIOUaeiouAEIOU—')) like upper('%" + name;
		name = name + "%')";
		ad.setName(name);
		ad.setName2(name2);
		ad.setValue(value);
		ad.setTaxid(taxid);
		List<CBPartner> adl = cp.buscarPorVendedor(ad, adId);
		PrintWriter out = response.getWriter();
		if (adl!=null){
		if (adl.size() != 0) {
			response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
//			out.println("<form selected=\"true\"><fieldset>");
	    	out.println("<div class=\"toolbar\">");
			out.println("<h1 id=\"pageTitle\">Clientes</h1>");
			out.println("<a href=\"busqueda.jsp?ad_user_id="+ adId + "&m_warehouse_id="+ mWareHouseID +"\" class=blueButtonRegresar>Regresar</a>");
//			out.println("	<a id=\"backButton\" type=button value=\"Atrassssssss\" onCLick=\"history.back()\">");
			out.println("</div>)");
			out.println(" <ul selected=\"true\" title=\"Pedido\">");
			String s;
			for (CBPartner coi : adl) {
				 s = new String(coi.getName().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("ISO-8859-1"));
				out.println("<li>");
				out.println("<a "
						+ "href=\"pedido_i.jsp?"
						+ "ad_user_id="+ adId +""
						+ "&m_warehouse_id="+mWareHouseID
						+ "&cb_partner_id="+ coi.getC_bpartner_id() +
								"&nameco="+ s +
								 "\" target=\"_self\"/>");
				out.println(s);
				out.println("<div class=\"secondary\">");
				out.println(coi.getValue());
				out.println("</div>");
				out.println("</li>");
//				out.println("<div class=\"toolbar\">");
//				out.println("<h1 id=\"pageTitle\"></h1>");
//				out.println("<form id=\"screen1\" title=\"Pedido\" class=\"panel\" name=\"formname\"");
//				out.println("action=\"busquedaPedidos");
//				out.println("method=\"post\" selected=\"true\">");
//			
//				out.println("<a id=\"previousButton\" target=\"_self\" href=\"#_self\"");
//				out.println("	class=\"button\" type=\"submit\"> Atrass </a>");
//				out.println("	<a id=\"backButton\" type=button value=\"Atrassssssss\" onCLick=\"history.back()\">");
//				out.println("	</form>");
//				out.println("</div>)");
			}
			out.println("</ul>");
//			out.println("</fieldset></form>");
		
		} else {
//			request.getRequestDispatcher("/busqueda.jsp?"+request.getParameter("ad_user_id")).forward(request, response);
			out.println("<div class=\"toolbar\">");
			out.println("<h1 id=\"pageTitle\">Clientes</h1>");
			out.println("<a href=\"busqueda.jsp?ad_user_id="+ request.getParameter("ad_user_id") + "&m_warehouse_id="+ mWareHouseID +"\" class=blueButtonRegresar>Regresar</a>");
//			out.println("	<a id=\"backButton\" type=button value=\"Atrassssssss\" onCLick=\"history.back()\">");
			out.println("</div>)");
			out.println("<ul selected=\"true\" title=\"Pedido\">");
			out.println("<li>");
			out.println("<div class=\"secondary\">");
			out.println("NO HAY CLIENTES CON LA INFORMACION INGRESADA");
			out.println("</div>");
			out.println("</li>");
			out.println("</ul>");
		}
		}
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
