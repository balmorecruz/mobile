package org.saram.busquedas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.COrder_X;
import org.saram.accesos.MProduct_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.COrder;
import org.saram.modelo.MProduct;;

/**
 * Servlet implementation class pedidoIngreso
 */
@WebServlet("/busquedaProductos")
public class busquedaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private float precio;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public busquedaProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MProduct_X cp = new MProduct_X();
		String adId = request.getParameter("ad_user_id");
		String cOrderID = request.getParameter("c_order_id");
		String c_bpartner_id = request.getParameter("c_bpartner_id");
		MProduct mProductID = new MProduct();
		String name = null;
		if (request.getParameter("mProductID") != null) {
			mProductID.setValue(request.getParameter("mProductID"));
		}
		;
		if (request.getParameter("name") != null) {
			mProductID.setName(request.getParameter("name"));
		}
		;
		List<MProduct> adl = null;
		if (cOrderID != null) {
			COrder co = new COrder();
			COrder_X cox = new COrder_X();
			co = cox.buscarUno(Integer.parseInt(cOrderID));
			
			if (co.getM_warehouse_id() == 1000005) {
				adl = cp.buscarTodosPorClienteMateriaPrima(mProductID, Integer.parseInt(c_bpartner_id));
			} else if (co.getM_warehouse_id() == 1000006) {
				adl = cp.buscarTodosPorClienteProductoProceso(mProductID, Integer.parseInt(c_bpartner_id));
			} else if (co.getM_warehouse_id() == 1000019) {
				adl = cp.buscarTodosPorClienteInsumo(mProductID, Integer.parseInt(c_bpartner_id));
			} else if (co.getM_warehouse_id() == 1000014) {
				adl = cp.buscarTodosPorClienteHuevo(mProductID, Integer.parseInt(c_bpartner_id));
			} else {
				if (co.getDescription().indexOf("PECUARIO") > -1) {
					adl = cp.buscarTodosPorClientePecuario(mProductID, Integer.parseInt(c_bpartner_id));
				} else if (co.getDescription().indexOf("MASCOTA") > -1) {
					adl = cp.buscarTodosPorClienteMascota(mProductID, Integer.parseInt(c_bpartner_id));
				} else if (co.getDescription().indexOf("TROTEMOR") > -1) {
					adl = cp.buscarTodosPorClienteTrotemor(mProductID, Integer.parseInt(c_bpartner_id));
				} else if (co.getDescription().indexOf("EXTRANJERO") > -1) {
					adl = cp.buscarTodosPorClienteExtranjero(mProductID, Integer.parseInt(c_bpartner_id));
				}
			}
		}
		PrintWriter out = response.getWriter();
		if (adl.size() != 0) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out.println("<div class=\"toolbar\">");
			out.println("<h1 id=\"pageTitle\">Productos</h1>");
			out.println("<a href=\"busquedaProducto.jsp?ad_user_id=" + adId + "&c_order_id=" + cOrderID
					+ "&c_bpartner_id=" + c_bpartner_id + " \" class=blueButtonRegresar>Regresar</a>");
			// out.println(" <a id=\"backButton\" type=button
			// value=\"Atrassssssss\" onCLick=\"history.back()\">");
			out.println("</div>)");
			out.println("<ul selected=\"true\" title=\"Pedido\">");
			// out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			// out.println("<head>");
			// out.println("<title>Mobile SARAM</title>");
			// out.println("<meta http-equiv=\"Content-Type\"
			// content=\"text/html; charset=UTF-8\" />");
			// out.println("<meta name=\"viewport\"
			// content=\"width=device-width, initial-scale=1.0,
			// maximum-scale=1.0, user-scalable=0\" />");
			// out.println("<link rel=\"stylesheet\" href=\"" +
			// request.getContextPath() +"/iui/iui.css\" type=\"text/css\" />");
			// out.println("<link rel=\"stylesheet\" href=\"" +
			// request.getContextPath() +"/recursos/saram.css\"
			// type=\"text/css\" />");
			// out.println("<link rel=\"stylesheet\" href=\"" +
			// request.getContextPath() +"/iui/t/default/default-theme.css\"
			// type=\"text/css\" />");
			// out.println("<script type=\"application/x-javascript\" src=\"" +
			// request.getContextPath() +"/iui/iui.js\"></script>");
			// out.println("<script type=\"text/javascript\" src=\"" +
			// request.getContextPath() +"/js/calendar.js\"
			// language=\"JavaScript1.2\"></script>");
			// out.println("<link href=\"" + request.getContextPath()
			// +"/css/calendar-blue.css\" type=\"text/css\" rel=\"stylesheet\"
			// />");
			// out.println("<meta name=\"viewport\"
			// content=\"width=device-width; initial-scale=1.0;
			// minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
			// out.println("</head><body>");
			// out.println("<ul selected=\"true\" title=\"Pedido\">");
			// out.println("<form id=\"screen1\" title=\"Mobile SARAM\"
			// class=\"panel\" name=\"formname\" "
			// + "action=\"busquedaPedidos method=\"post\" selected=\"true\">");
			List s;
			sqlHQL_X mp = new sqlHQL_X();
			System.out.println("LISTA DE PRODUCTOS:"+adl.size());
			for (MProduct mp1 : adl) {
				if (precioProducto(c_bpartner_id, mp1.getM_product_id().toString()) >= 0) {
//					s = mp.buscarUno(
//							"SELECT sum(qtyonhand)-COALESCE(adempiere.bomqtyreserved("+ mp1.getM_product_id() +", 1000007, 0), 0)"
//							+ " FROM m_storage WHERE (m_locator_id = 1000023 or m_locator_id = 1000157) and m_product_id =" + ""
//									+ mp1.getM_product_id());
//				    s = mp.buscarUno("select (existencia-coalesce(ordenado,0)) as cantidad from (Select a.m_product_id, \n"
//				            + "									sum(a.qtyonhand) as existencia, \n"
//				            + "									sum(a.qtyreserved) as reservada,\n"
//				            + "									(sum(a.qtyonhand)-sum(a.qtyreserved)) as disponible,\n"
//				            + "									b.value,\n"
//				            + "									b.name,\n"
//				            + "									c.currentcostprice,\n"
//				            + "									E.M_WAREHOUSE_ID, \n"
//				            + "									f.value\n"
//				            + "									from m_storage a\n"
//				            + "									inner join m_locator e on a.m_locator_id=e.m_locator_id\n"
//				            + "									inner join m_product b on a.m_product_id=b.m_product_id\n"
//				            + "									inner join M_WAREHOUSE f on f.m_warehouse_id = e.m_warehouse_id \n"
//				            + "									inner join m_cost c on a.m_product_id=c.m_product_id\n"
//				            + "									where c.m_costelement_id=1000001 and f.m_warehouse_id = 1000007 and b.m_product_id = "+mp1.getM_product_id()+"\n"
//				            + "									group by a.m_product_id, b.value, b.name, c.currentcostprice,E.M_WAREHOUSE_ID, f.value) a\n"
//				            + "									left join \n"
//				            + "									(SELECT a.m_product_id, \n"
//				            + "									sum(qtyreserved) as ordenado,\n"
//				            + "									c.value,\n"
//				            + "									a.m_warehouse_id,\n"
//				            + "									d.value\n"
//				            + "									FROM C_ORDERLINE A INNER JOIN C_ORDER B \n"
//				            + "									ON A.C_ORDER_ID=B.C_ORDER_ID \n"
//				            + "									inner join m_product c on c.m_product_id=a.m_product_id\n"
//				            + "									inner join m_warehouse d on a.m_warehouse_id = d.m_warehouse_id\n"
//				            + "									WHERE ISSOTRX='Y' \n"
//				            + "									AND QTYRESERVED<>0 \n"
//				            + "									 and a.m_warehouse_id = 1000007 and a.m_product_id = "+mp1.getM_product_id()+"\n"
//				            + "									group by a.m_product_id, c.value, a.m_warehouse_id, d.value  )b \n"
//				            + "									on a.m_product_id = b.m_product_id and a.m_warehouse_id = b.m_warehouse_id");
					
					
					out.println("<li>");
					// out.println("<a " + "href=\"pedido_linea_i.jsp?" +
					// "ad_user_id=" + adId + "" + "&c_bpartner_id="
					// + c_bpartner_id + "&c_order_id=" + cOrderID +
					// "&m_product_id=" + mp1.getM_product_id()
					// + "&m_product_value=" + mp1.getValue() +
					// "&m_product_name=" + mp1.getName()
					// + "\" target=\"_self\"/>");
					out.println("<a " + "href=\"validaproducto?" + "ad_user_id=" + adId + "" + "&c_bpartner_id="
							+ c_bpartner_id + "&c_order_id=" + cOrderID + "&m_product_id=" + mp1.getM_product_id()
							+ "&m_product_value=" + mp1.getValue() + "&m_product_name=" + mp1.getName()+"&modo=1"
							+ "\" target=\"_self\"/>");
					out.println(mp1.getValue());
					out.println("<div class=\"secondary\">");
					out.println(mp1.getName());
//					out.println(s.get(0));
//					+ " Disponible: " + (s.get(0) == null ? "[0.00]" : s));
//					out.println(mp1.getName() );

					out.println("</div>");
					out.println("</a>");
					out.println("</li>");
				}
			}
			out.println("</ul>");
		} else {
			out.println("<div class=\"toolbar\">");
			out.println("<h1 id=\"pageTitle\">Clientes</h1>");
			out.println("<a href=\"busquedaProducto.jsp?ad_user_id=" + adId + "&c_order_id=" + cOrderID
					+ "&c_bpartner_id=" + c_bpartner_id + " \" class=blueButtonRegresar>Regresar</a>");
			// out.println(" <a id=\"backButton\" type=button
			// value=\"Atrassssssss\" onCLick=\"history.back()\">");
			out.println("</div>)");
			out.println("<ul selected=\"true\" title=\"Pedido\">");
			out.println("<li>");
			out.println("<div class=\"secondary\">");
			out.println("NO HAY PRODUCTOS");
			out.println("</div>");
			out.println("</li>");
			out.println("</ul>");
		}
		// out.println("</form>");
		out.println("<div class=\"footer\">");

		// out.println("<a href=\"pedido_linea_i.jsp?ad_user_id="+ adId +"\"
		// target=\"_self\" class=\"greenButton\">Registrar</a>");
		// out.println("<a href=\"busquedaProducto.jsp?ad_user_id="+ adId +"\"
		// target=\"_self\" class=\"greenButton\">Registrar</a>");
		out.println("</div>");
		// out.println("</body>");
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

	private float precioProducto(String cb, String mp) {
		try {
			sqlHQL_X datos = new sqlHQL_X();
			String sql = "select coalesce(sum(d.pricelist),0) " + "from " + "m_pricelist a " + "left join "
					+ "c_bpartner b " + "on a.m_pricelist_id = b.m_pricelist_id " + " left join "
					+ " m_pricelist_version c " + "on c.m_pricelist_id = a.m_pricelist_id " + "left join "
					+ "m_productprice d " + "on  d.m_pricelist_version_id = c.m_pricelist_version_id "
					+ "where c.isactive = 'Y' and b.c_bpartner_id =" + cb + " and d.m_product_id = " + mp;
			List<Object> d = datos.buscarUno(sql);
			if (d.get(0) == null) {
				precio = new Float(0.0);
			} else {
				precio = Float.parseFloat(d.get(0).toString());
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return precio;
	}
}
