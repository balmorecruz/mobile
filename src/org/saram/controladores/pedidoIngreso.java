package org.saram.controladores;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.AdSequence_X;
import org.saram.accesos.CBPartnerLocation_X;
import org.saram.accesos.CBPartner_X;
import org.saram.accesos.COrder_X;
import org.saram.accesos.MPriceList_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.modelo.CBPartner;
import org.saram.modelo.CBPartnerLocation;
import org.saram.modelo.COrder;
import org.saram.modelo.MPriceList;;

/**
 * Servlet implementation class pedidoIngreso
 */
@WebServlet(name="/pedidoIngreso",urlPatterns = {"/pedidoIngreso"})
public class pedidoIngreso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public pedidoIngreso() {
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
		String valor = request.getParameter("C_BPartner_ID");
		if (valor != null) {
			String CBPartnerID = valor;
			Integer c_order_id;
			String description, ad_client_id, documentno, ad_org_id, createdby, updatedby, docstatus, docaction,
					c_doctype_id, c_doctypetarget_id, dateordered, dateacct, c_bpartner_id, c_bpartner_location_id,
					c_currency_id, paymentrule, c_paymentterm_id, invoicerule, deliveryrule, freightcostrule,
					datePromissed, deliveryviarule, priorityrule, m_warehouse_id, m_warehouse_id_m = "0",
					m_pricelist_id, salesrep_id, C_PaymentTerm_ID = null;
			COrder_X co = new COrder_X();
			COrder o = new COrder();
			CBPartner_X cb = new CBPartner_X();
			CBPartner c = new CBPartner();
			c = cb.buscarUno(Integer.parseInt(CBPartnerID));
			if (request.getParameter("c_order_id") != null) {
				c_order_id = Integer.parseInt(request.getParameter("c_order_id"));
				dateordered = request.getParameter("DateOrdered");
				dateacct = request.getParameter("DateOrdered");
				datePromissed = request.getParameter("DateOrdered");
				c_bpartner_location_id = request.getParameter("c_bpartner_location_id");
				m_warehouse_id = request.getParameter("m_warehouse_id");
				C_PaymentTerm_ID = request.getParameter("cpaymentterm");

				// esEntregaLocal = request.getParameter("esentregalocal");
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				// you can change format of date
				Date date, created;
				try {
					date = df.parse(datePromissed);
					created = df.parse(df.format(new Date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					date = new Date(System.currentTimeMillis());
					created = new Date(System.currentTimeMillis());
				}
				java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
				java.sql.Timestamp createdTS = new Timestamp(created.getTime());

				o.setC_order_id(c_order_id);
				o.setC_bpartner_location_id(Integer.parseInt(c_bpartner_location_id));
				o.setDateordered(createdTS);
				sqlHQL_X cox = new sqlHQL_X();
				String sql = "update c_order set m_warehouse_id=" + m_warehouse_id + ",c_bpartner_location_id="
						+ c_bpartner_location_id + ", " + "dateordered='" + createdTS + "' where c_order_id = "
						+ c_order_id;
				cox.sql(sql);
				CBPartnerLocation cbl = new CBPartnerLocation();
				CBPartnerLocation_X cblc = new CBPartnerLocation_X();
				cbl = cblc.buscarUno(Integer.parseInt(c_bpartner_location_id));
				createdby = request.getParameter("ad_user_id");
				o.setBill_location_id(Integer.parseInt(c_bpartner_location_id));
				o.setBill_bpartner_id(Integer.parseInt(CBPartnerID));
				o.setBill_user_id(Integer.parseInt(createdby));
				o.setC_conversiontype_id(114);
				o.setIsdelivered("N");
				request.getRequestDispatcher("pedido?ad_user_id=" + createdby);
			} else {
				String dropShipLocationID = request.getParameter("dropship_location_id");
				// esEntregaLocal = request.getParameter("esentregalocal");
				AdSequence_X adsx = new AdSequence_X();
				sqlHQL_X sqlHqlC = new sqlHQL_X();
				// List max4 = sqlHqlC.buscarUno("SELECT max(c_order_id) + 1 from c_order ");
				// String max4S=max4.toString();
				String max4S = sqlHqlC.buscarUno("SELECT max(c_order_id) + 1 from c_order ").get(0).toString();
				max4S = max4S.replace("]", "").replaceAll("\\[", "");
				Integer max4I = Integer.parseInt(max4S);
				c_order_id = max4I;
				m_warehouse_id = request.getParameter("m_warehouse_id");
				m_warehouse_id_m = m_warehouse_id;
				C_PaymentTerm_ID = request.getParameter("cpaymentterm");
				String tipopedido = "";
				description = "CREADO DESDE EL SISTEMA MOVIL";
				if (m_warehouse_id.equals("10000071")) {
					description = "CREADO DESDE EL SISTEMA MOVIL PECUARIO";
					m_warehouse_id = "1000007";
				} else if (m_warehouse_id.equals("10000072")) {
					description = "CREADO DESDE EL SISTEMA MOVIL MASCOTAS";
				} else if (m_warehouse_id.equals("10000073")) {
					description = "CREADO DESDE EL SISTEMA MOVIL TROTEMOR";
					m_warehouse_id = "1000007";
				} else if (m_warehouse_id.equals("10000074")) {
					description = "CREADO DESDE EL SISTEMA MOVIL EXTRANJERO";
					m_warehouse_id = "1000007";
				} else if (m_warehouse_id.equals("1000044")) {
					description = "CREADO DESDE EL SISTEMA MOVIL OPERACION INDUSTRIAL";
					m_warehouse_id = "1000044";
				}
				try {
					if (request.getParameter("description").toString().trim().length() > 0) {
						String item = request.getParameter("description");
						item = URLDecoder.decode(item, "UTF-8");
						description = remove1(request.getParameter("description")) + ", " + remove1(description);
					}
				} catch (Exception e) {

				}
				o.setM_warehouse_id(Integer.parseInt(m_warehouse_id));
				ad_client_id = "1000000";
				ad_org_id = "1000000";
				createdby = request.getParameter("ad_user_id");
				updatedby = request.getParameter("ad_user_id");

				documentno = "P-MOVIL-0" + c_order_id;
				docstatus = "DR";
				docaction = "CO";
				c_doctype_id = "1000026";
				c_doctypetarget_id = "1000026";
				dateordered = request.getParameter("DateOrdered");
				dateacct = request.getParameter("DateOrdered");
				c_bpartner_id = request.getParameter("C_BPartner_ID");
				c_bpartner_location_id = request.getParameter("c_bpartner_location_id");
				c_currency_id = "100";
				paymentrule = "P";
				c_paymentterm_id = C_PaymentTerm_ID.toString();
				// c.getC_paymentterm_id().toString();
				invoicerule = "I";
				deliveryrule = "A";
				m_pricelist_id = (m_warehouse_id.equals("10000072"))?c.getMas_pricelist_id().toString():c.getM_pricelist_id().toString();
				salesrep_id = createdby;
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				// you can change format of date
				Date date, created;
				try {
					date = df.parse(dateordered);
					created = df.parse(df.format(new Date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					date = new Date(System.currentTimeMillis());
					created = new Date(System.currentTimeMillis());
				}
				java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
				java.sql.Timestamp createdTS = new Timestamp(created.getTime());
				o.setC_order_id(c_order_id);
				o.setDescription(remove1(description));
				o.setAd_client_id(Integer.parseInt(ad_client_id));
				o.setAd_org_id(Integer.parseInt(ad_org_id));
				o.setAd_user_id(Integer.parseInt(createdby));
				o.setCreatedby(Integer.parseInt(createdby));
				o.setUpdatedby(Integer.parseInt(updatedby));
				o.setDocumentno(documentno);
				o.setDocstatus(docstatus);
				o.setDocaction(docaction);
				o.setIssotrx("Y");
				o.setProcessed("N");
				o.setIsapproved("Y");
				o.setIscreditapproved("N");
				o.setIsdelivered("Y");
				o.setIsselected("N");
				o.setIsdiscountprinted("N");
				o.setFreightcostrule("I");
				o.setDeliveryrule("A");
				o.setPriorityrule("5");
				o.setDeliveryviarule("P");
				o.setTotallines(new Float(0.00));
				o.setC_doctype_id(Integer.parseInt(c_doctype_id));
				o.setGrandtotal(new Float(0.00));
				MPriceList mpl = new MPriceList();
				MPriceList_X mplx = new MPriceList_X();
				mpl = mplx.buscarUno(Integer.parseInt(m_pricelist_id));
				if (m_warehouse_id.equals("10000074")) {
					o.setIstaxincluded("N");
				} else
					o.setIstaxincluded(mpl.getIstaxincluded());
				o.setPosted("N");
				o.setSendemail("N");
				o.setIsselfservice("N");
				o.setIsdropship("N");
				o.setC_doctypetarget_id(Integer.parseInt(c_doctypetarget_id));
				o.setDateordered(createdTS);
				o.setDateacct(createdTS);
				o.setIsactive("Y");
				o.setCreated(createdTS);
				o.setIsinvoiced("N");
				o.setIssotrx("Y");
				o.setIsprinted("N");
				o.setIstransferred("N");
				o.setUpdated(createdTS);
				// ATENCION: validar que haya seleccionado
				o.setDatepromised(timeStampDate);
				if (!dropShipLocationID.equals("999999")) {
					o.setDropship_location_id(Integer.parseInt(dropShipLocationID));
					o.setC_bpartner_location_id(Integer.parseInt(dropShipLocationID));
				} else {
					o.setDropship_location_id(Integer.parseInt(c_bpartner_location_id));
					o.setC_bpartner_location_id(Integer.parseInt(c_bpartner_location_id));
					o.setDescription(remove1(description) + " SELECCIONADO COMO ENTREGA EN SARAM");
				}
				o.setDatepromised(createdTS);
				o.setDateordered(createdTS);
				o.setC_bpartner_id(Integer.parseInt(c_bpartner_id));
				// o.setC_bpartner_location_id(Integer.parseInt(c_bpartner_location_id));
				o.setC_currency_id(Integer.parseInt(c_currency_id));
				o.setPaymentrule(paymentrule);
				//CAMBIO DE PAYMENTTERM SEGUN CBPARTNERID
				if (m_warehouse_id.equals("10000072")) {
					if (!(C_PaymentTerm_ID.equals("1000001")) && !(o.getC_bpartner_id() ==1004006) && 
							!(o.getC_bpartner_id() ==1007517))
						o.setC_paymentterm_id(1000007);
					else
						o.setC_paymentterm_id(Integer.parseInt(C_PaymentTerm_ID));
					m_warehouse_id = "1000007";
				} else {
					o.setC_paymentterm_id(Integer.parseInt(c_paymentterm_id));
				}
				o.setInvoicerule(invoicerule);
				o.setIsdelivered("N");
				o.setBill_location_id(Integer.parseInt(c_bpartner_location_id));
				o.setBill_bpartner_id(Integer.parseInt(CBPartnerID));
				o.setBill_user_id(Integer.parseInt(createdby));
				o.setC_conversiontype_id(114);
				// System.out.println(C_PaymentTerm_ID);
				if (dropShipLocationID.equals("999999"))
					o.setEsentregalocal("Y");
				else
					o.setEsentregalocal("N");
				o.setDeliveryrule(deliveryrule);
				o.setM_warehouse_id(Integer.parseInt(m_warehouse_id));
				o.setM_pricelist_id(Integer.parseInt(m_pricelist_id));
				o.setSalesrep_id(Integer.parseInt(salesrep_id));
				o.setEsdeudor("N");
				co.salvarOrden(o);
				CBPartnerLocation cbl = new CBPartnerLocation();
				CBPartnerLocation_X cblc = new CBPartnerLocation_X();
				cbl = cblc.buscarUno(Integer.parseInt(c_bpartner_location_id));
				pedidoIngresoLinea pi = new pedidoIngresoLinea();
				if (o.getEsentregalocal().equals("N"))
					pi.fleteAutomatico(o, m_warehouse_id_m);

//				request.getRequestDispatcher("pedido_m.jsp?DateOrdered=" + createdTS + "&documentno=" + documentno
//						+ "&ad_user_id=" + createdby + "&c_bpartner_id=" + c_bpartner_id + "&direccion=" + cbl.getName()
//						+ "&c_order_id=" + c_order_id).forward(request, response);
				 RequestDispatcher dispatcher = 
						   getServletContext().getRequestDispatcher("/pedido_m.jsp?DateOrdered=" + createdTS + "&documentno=" + documentno
									+ "&ad_user_id=" + createdby + "&c_bpartner_id=" + c_bpartner_id + "&direccion=" + cbl.getName()
									+ "&c_order_id=" + c_order_id);
						 dispatcher.forward(request, response);
			}
		}
		// TODO Auto-generated method stub
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

	private String remove1(String texto) {

		String original = "AÃŅǇɉˋ͍ϏёӓՕטښܜޟᢢ䥥稨ꫫ?౱򳴵??󼽿";
		// Cadena de caracteres ASCII que reemplazar⮠los originales.
		String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
		String output = texto;
		for (int i = 0; i < original.length(); i++) {
			// Reemplazamos los caracteres especiales.

			output = output.replace(original.charAt(i), ascii.charAt(i));

		} // for i

		// FORMATEO EXTRA
		output = cleanTextContent(output);
		return output;
	}

	private static String cleanTextContent(String text) {
		// strips off all non-ASCII characters
		text = text.replaceAll("[^\\x00-\\x7F]", "");

		// erases all the ASCII control characters
		text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

		// removes non-printable characters from Unicode
		text = text.replaceAll("\\p{C}", "");

		return text.trim();
	}
}
