package org.saram.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.JDBCException;
import org.saram.accesos.AdUser_X;
import org.saram.accesos.COrder_X;
import org.saram.modelo.AdUser;
import org.saram.modelo.COrder;

@SuppressWarnings("serial")
@WebServlet("/acceso")
public class acceso extends HttpServlet {
	public acceso() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			AdUser_X cp = new AdUser_X();
			AdUser ad = new AdUser();
			COrder_X cox = new COrder_X();
			COrder co = new COrder();
			String COrderID = request.getParameter("c_order_id");
			ad.setName(request.getParameter("usuario"));
			ad.setPassword(request.getParameter("password"));
			if (COrderID != null) {
				co = cox.buscarUno(Integer.parseInt(COrderID));
				request.setAttribute("ad_user_id", co.getUpdatedby());
				request.getRequestDispatcher("pedido").forward(request, response);
			} else {
				if ((ad.getName().length() == 0) || (ad.getPassword().length() == 0)) {
					request.getRequestDispatcher("/loginerror.jsp").forward(request, response);
				} else {
					Integer n1 = cp.buscarUno(ad);
					if (n1 != -1) {
						request.setAttribute("ad_user_id", n1);
						request.getRequestDispatcher("pedido").forward(request, response);
					} else {
						request.getRequestDispatcher("/loginerror.jsp").forward(request, response);
					}
				}
			}
			// }
		} catch (JDBCException e) {
			System.out.println(e.toString());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Saram
		 * 
		 **/
	}
}
