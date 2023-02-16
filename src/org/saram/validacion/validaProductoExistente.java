package org.saram.validacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.COrderLine_X;
import org.saram.accesos.MProduct_X;
import org.saram.accesos.MPromocionLine_X;
import org.saram.accesos.MPromocion_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.COrderLine;
import org.saram.modelo.MProduct;
import org.saram.modelo.MPromocion;
import org.saram.modelo.MPromocionLine;

@WebServlet("/validaproducto")
public class validaProductoExistente extends HttpServlet {

	
	public validaProductoExistente() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adUserId = request.getParameter("ad_user_id");
		Integer cOrderID = Integer.parseInt(request.getParameter("c_order_id"));
		String CBPartnerID = request.getParameter("c_bpartner_id");
		sqlHQL_X mp = new sqlHQL_X();
		Integer mProductID = Integer.parseInt(request.getParameter("m_product_id"));
		String mProductValue = request.getParameter("m_product_value");
		String mProductName = request.getParameter("m_product_name");
		Integer modo = Integer.parseInt(request.getParameter("modo"));
		COrderLine_X colx = new COrderLine_X();
		Boolean estaIngresado = colx.buscarProductoIngresado(mProductID, cOrderID);
		COrderLine_X colx2 = new COrderLine_X();
		Boolean estaIngresado2 = false;
		List s = mp.buscarUno("SELECT count(c_orderline_id) FROM c_orderline WHERE c_order_id =" + cOrderID);
		MPromocion mpro = new MPromocion();
		MPromocion_X mprox = new MPromocion_X();
		mpro = mprox.buscarTodos(mProductID, Integer.parseInt(CBPartnerID));
		Integer MProductPromo = 0;
		MPromocionLine mproline = new MPromocionLine();
		MPromocionLine_X mprolinex = new MPromocionLine_X();
		Integer noProducto = 0;
		boolean esPromocion = false;
		String razon = "La suma de sus productos, promociones y cargos ya excedieron las 9 lineas permitidas";
		if (mpro != null) {
			// mproline = mprolinex.buscarTodos(mpro.getM_promocion_id());
			// noProducto = noProducto + 1;
			// razon = razon + ", <b>el producto seleccionado tiene promocion</b>";
		}
		MProduct_X mpx = new MProduct_X();
		MProduct p = mpx.buscarUno(mProductID);
		if (p.getM_product_category_id() == 1000005 || p.getM_product_category_id() == 1000004) {
			Integer cChargueID = 1004412;
			COrderLine cOrderLineIDQAve = colx.buscarQuotaAve(cOrderID, cChargueID);
			if (cOrderLineIDQAve == null) {
				noProducto = noProducto + 1;
				razon = razon + ", <b>el producto seleccionado generara linea de Quota AVE</b>";
			}
		}
		noProducto = Integer.parseInt(s.get(0).toString().replace("[", "").replace("]", "")) + noProducto;
		if (noProducto >= 9) {
			estaIngresado2 = true;
		}
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (modo == 1) {
			if (estaIngresado2) {
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/busquedaProductoVal13.jsp?c_order_id=" + cOrderID + "&c_bpartner_id="
								+ CBPartnerID + "&ad_user_id=" + adUserId + "&razonable=" + razon);
				requestDispatcher.forward(request, response);
			} else if (estaIngresado) {
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/busquedaProductoVal.jsp?c_order_id=" + cOrderID + "&c_bpartner_id="
								+ CBPartnerID + "&ad_user_id=" + adUserId);
				requestDispatcher.forward(request, response);
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pedido_linea_i.jsp?ad_user_id="
						+ adUserId + "" + "&c_bpartner_id=" + CBPartnerID + "&c_order_id=" + cOrderID + "&m_product_id="
						+ mProductID + "&m_product_value=" + mProductValue + "&m_product_name=" + mProductName);
				requestDispatcher.forward(request, response);
			}

		} else {
			if (modo == 2) {
				String COrderLineID = request.getParameter("c_orderline_id");
				String QtyEntered = request.getParameter("QtyEntered");
				if (request.getParameter("Eliminar") != null) {
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("/pedidoIngresoLinea?c_order_id=" + cOrderID + "&c_bpartner_id="
									+ CBPartnerID + "m_product_id=" + mProductID + "QtyEntered=" + QtyEntered);
					requestDispatcher.forward(request, response);
				} else {
					if (mpro != null) {
						if (Integer.parseInt(QtyEntered) >= mpro.getQtyPromocion())
							esPromocion = true;
					}
					if (esPromocion) {

						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/pedidoIngresoLinea?c_order_id=" + cOrderID + "&c_bpartner_id="
										+ CBPartnerID + "m_product_id=" + mProductID + "QtyEntered=" + QtyEntered);
						requestDispatcher.forward(request, response);
					} else {
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/pedidoIngresoLinea?c_order_id=" + cOrderID + "&c_bpartner_id="
										+ CBPartnerID + "m_product_id=" + mProductID + "QtyEntered=" + QtyEntered);
						requestDispatcher.forward(request, response);
					}

				}

			} else {

				if (modo == 3) {
					String QtyEntered = request.getParameter("QtyEntered");
					noProducto = 0;
					if (mpro != null) {
						if (Integer.parseInt(QtyEntered) >= mpro.getQtyPromocion()) {
							esPromocion = false;
							noProducto = noProducto + 1;
						}
					}
					noProducto = noProducto + 1;
					noProducto = Integer.parseInt(s.get(0).toString().replace("[", "").replace("]", "")) + noProducto;
					if (noProducto > 9) {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher(
								"/pedido_linea_i_val13.jsp?ad_user_id=" + adUserId + "&c_order_id=" + cOrderID
										+ "&c_bpartner_id=" + CBPartnerID + "&m_product_id=" + mProductID);
						requestDispatcher.forward(request, response);
					} else {
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/pedidoIngresoLinea?c_order_id=" + cOrderID + "&c_bpartner_id="
										+ CBPartnerID + "m_product_id=" + mProductID + "QtyEntered=" + QtyEntered);
						requestDispatcher.forward(request, response);
					}
				}
			}
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
