package org.saram.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.saram.accesos.AdSequence_X;
import org.saram.accesos.CBPartnerLocation_X;
import org.saram.accesos.CBPartner_X;
import org.saram.accesos.CCharge_X;
import org.saram.accesos.CLocation_X;
import org.saram.accesos.COrderLine_X;
import org.saram.accesos.COrderTax_X;
import org.saram.accesos.COrder_X;
import org.saram.accesos.MPriceList_X;
import org.saram.accesos.MProduct_X;
import org.saram.accesos.MPromoClasificacion_X;
import org.saram.accesos.MPromocionLine_X;
import org.saram.accesos.MPromocionSalesRep_X;
import org.saram.accesos.MPromocion_X;
import org.saram.accesos.sqlHQL_X;
import org.saram.busquedas.utilerias;
import org.saram.modelo.AdSequence;
import org.saram.modelo.CBPartner;
import org.saram.modelo.CBPartnerLocation;
import org.saram.modelo.CCharge;
import org.saram.modelo.CLocation;
import org.saram.modelo.COrder;
import org.saram.modelo.COrderLine;
import org.saram.modelo.COrderTax;
import org.saram.modelo.MPriceList;
import org.saram.modelo.MProduct;
import org.saram.modelo.MPromoClasificacion;
import org.saram.modelo.MPromocion;
import org.saram.modelo.MPromocionLine;

//PARA GUARDAR EN LINEA 452
//PARA BORRAR EN LINEA 223
/**
 * Servlet implementation class pedidoIngresoLinea
 */
@WebServlet(name="pedidoIngresoLinea",description = "Ingresa las lineas de las ordenes", urlPatterns = { "/pedidoIngresoLinea" })
public class pedidoIngresoLinea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public pedidoIngresoLinea() {
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
		utilerias dR = new utilerias();
		boolean esMascota, esProductoSinFlete = false;
		Integer COrderID = Integer.parseInt(request.getParameter("c_order_id"));
		String QtyEntered = request.getParameter("QtyEntered");
		Integer ad = Integer.parseInt(request.getParameter("ad_user_id"));
		sqlHQL_X sqlHqlC = new sqlHQL_X();
		List valueCBParnerID = sqlHqlC.buscarUno("SELECT c_bpartner_id from C_Order where C_Order_ID = " + COrderID);
		Integer CBpartnerID = Integer.parseInt(valueCBParnerID.get(0).toString());
		// String MProductID = request.getParameter("m_product_id");
		String MProductID = null;
		String CChargeID = null;
		MProductID = request.getParameter("m_product_id");
		CChargeID = request.getParameter("c_charge_id");
		COrderLine_X colx = new COrderLine_X();
		// Secuencia
		AdSequence_X adsx = new AdSequence_X();
		AdSequence ads = new AdSequence();
		// List max1 = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from
		// c_orderline ");
		String max1S = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from c_orderline ").get(0).toString();
		// String max1S=max1.toString();
		max1S = max1S.replace("]", "").replaceAll("\\[", "");
		Integer max1I = Integer.parseInt(max1S.toString());
		int maxCOrderLineID = max1I;
		// while (maxCOrderLineID==0) {
		// maxCOrderLineID = adsx.obtenerProximaSequencia(233);
		// }
		// adsx.salvarProximaSequencia(233);
		// Continuamos el proceso de salvador
		COrderLine col = new COrderLine();
		COrderLine colf = new COrderLine();
		COrderLine colOld = new COrderLine();
		Integer ad_client_id = 1000000;
		Integer ad_org_id = 1000000;
		Float iva = new Float(1.00);
		MPriceList_X mplx = new MPriceList_X();
		CBPartner_X cbx = new CBPartner_X();
		CBPartner cb = new CBPartner();
		Integer error;
		cb = cbx.buscarUno(CBpartnerID);
		MPriceList mpl = mplx.buscarUno(cb.getM_pricelist_id());
		String c = request.getParameter("c_orderline_id");
		if (c != null && !c.equals("0")) {
			maxCOrderLineID = Integer.parseInt(c);
			col.setC_orderline_id(maxCOrderLineID);
		} else {
			col.setC_orderline_id(maxCOrderLineID);
		}
		colOld = colx.buscarUno(col.getC_orderline_id());
		sqlHQL_X sqlx = new sqlHQL_X();
		col.setAd_client_id(ad_client_id);
		col.setAd_org_id(ad_org_id);
		col.setC_order_id(COrderID);
		col.setIsactive("Y");
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		// you can change format of date
		Date date, created;
		sqlHQL_X sqlHql = new sqlHQL_X();
		List valueDate = sqlHql.buscarUno("SELECT dateordered from C_Order where C_Order_ID = " + COrderID);
		try {
			created = new Date(System.currentTimeMillis());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			date = new Date(System.currentTimeMillis());
			created = new Date(System.currentTimeMillis());
		}
		java.sql.Timestamp createdTS = new Timestamp(created.getTime());
		col.setCreated(createdTS);
		col.setCreatedby(ad);
		col.setUpdated(createdTS);
		col.setUpdatedby(ad);
		List value = sqlHql.buscarUno("SELECT max(line) from C_OrderLine where C_Order_ID = " + COrderID);
		Integer maxLine = 0;
		if (value.get(0) != null) {
			maxLine = Integer.parseInt(value.get(0).toString()) + 10;
		} else {
			maxLine = 10;
		}
		;
		col.setLine(maxLine);
		col.setC_bpartner_id(CBpartnerID);
		List valueLocation = sqlHql
				.buscarUno("SELECT c_bpartner_location_id from C_Order where C_Order_ID = " + COrderID);
		col.setC_bpartner_location_id(Integer.parseInt(valueLocation.get(0).toString()));
		col.setDateordered(createdTS);
		col.setDatepromised(createdTS);
		sqlHQL_X datos = new sqlHQL_X();
		String sql = "";
		List<Object> pp;
		if (MProductID == null) {
			col.setC_charge_id(Integer.parseInt(CChargeID));
			sql = "select chargeamt from c_charge where c_charge_id = " + CChargeID;
			pp = datos.buscarUno(sql);
		} else {
			col.setM_product_id(Integer.parseInt(MProductID));
			sql = "select coalesce(sum(d.pricelist),0) " + "from " + "m_pricelist a " + "left join "
					+ "c_order b " + "on a.m_pricelist_id = b.m_pricelist_id " + " left join "
					+ " m_pricelist_version c " + "on c.m_pricelist_id = a.m_pricelist_id " + "left join "
					+ "m_productprice d " + "on  d.m_pricelist_version_id = c.m_pricelist_version_id "
					+ "where c.isactive = 'Y' and b.c_order_id =" + COrderID + " and d.m_product_id = " + MProductID;
			pp = datos.buscarUno(sql);
		}
		;
		List valueMWareHouse = sqlHql.buscarUno("SELECT m_warehouse_id from C_Order where C_Order_ID = " + COrderID);
		col.setM_warehouse_id(Integer.parseInt(valueMWareHouse.get(0).toString()));
		col.setC_uom_id(1000003);
		if (QtyEntered.length() == 0 || QtyEntered == null) {
			QtyEntered = "0.0";
		}
		col.setQtyordered(Float.parseFloat(QtyEntered));
		col.setQtyreserved(new Float(0.0));
		col.setQtydelivered(new Float(0));
		col.setQtyinvoiced(new Float(0));
		col.setQtypendiente(Float.parseFloat(QtyEntered));
		col.setC_currency_id(100);
		Float PriceEntered;
		if (pp.get(0) == null) {
			PriceEntered = new Float(0.0);
		} else {
			PriceEntered = Float.parseFloat(pp.get(0).toString());
		}
		col.setPriceactual(PriceEntered != null ? new Float(PriceEntered) : 0);
		col.setPricelist(PriceEntered != null ? new Float(PriceEntered) : 0);
		col.setPricelimit(new Float(0));
		if (request.getParameter("Eliminar") != null) {
			col.setLinenetamt(
					(PriceEntered != null ? new Float(PriceEntered) : 0) * (Float.parseFloat(QtyEntered)) * -1);
		} else {
			col.setLinenetamt((PriceEntered != null ? new Float(PriceEntered) : 0) * (Float.parseFloat(QtyEntered)));
		}
		col.setDiscount(new Float(0));
		col.setFreightamt(new Float(0));
		col.setC_tax_id(1000020);
		col.setM_attributesetinstance_id(0);
		col.setIsdescription("N");
		col.setProcessed("N");
		col.setQtyentered(Float.parseFloat(QtyEntered));
		col.setPriceentered((PriceEntered));
		col.setPricecost(new Float(0));
		col.setQtylostsales(new Float(0));
		col.setRramt(new Float(0));
		col.setIsconsumesforecast("N");
		col.setCreatefrom("N");
		col.setCreateshipment("N");
		if (request.getParameter("Eliminar") != null) {
			COrder_X orderDao = new COrder_X();
			COrder orden = new COrder();
			COrderLine linea_descuento_flete = null;
			orden = orderDao.buscarUno(col.getC_order_id());
			linea_descuento_flete = colx.buscarLineaDescuentoFlete(orden.getC_order_id(), col.getC_orderline_id());
			COrderLine lineaRespaldo=new COrderLine();
			lineaRespaldo=col;
			if (linea_descuento_flete != null) {
				colx.eliminarUno(linea_descuento_flete.getC_orderline_id());
			}
			
			
			colx.eliminarUno(Integer.parseInt(c));
			
//			colx.eliminarLineasDescuentoFlete(orden.getC_order_id());
//			colx.eliminarPromosEscala(orden.getC_order_id());
//			colx.eliminarPromosEscalaMix(orden.getC_order_id());
			recalculaPromocionEscala(orden, lineaRespaldo);

			COrder_X cox2 = new COrder_X();
			COrder cou2 = cox2.buscarUno(COrderID);
			Float Amount2 = colx.sumarTodoMonto(cou2.getC_order_id());
			Float AmountGT2 = colx.sumarTodoMontoGrandTotal(cou2.getC_order_id());
			Float AmountGTNoAVE2 = colx.sumarTodoMontoGrandTotalSinAve(cou2.getC_order_id());
			cou2.setGrandtotal(AmountGT2);
			cou2.setTotallines(Amount2);
			cox2.salvarOrden(cou2);
			// Logica de tax id
			String taxSQL;
			String colC = "select count(*) from c_orderline where C_Order_ID=" + COrderID;
			Integer colCX = Integer.parseInt(sqlx.buscarUno(colC).get(0).toString());
			COrderLine cold = colx.buscarUno(Integer.parseInt(c));
			String sqlTax = "select count(*) from C_OrderTax where C_Order_ID=" + COrderID + " and C_Tax_ID=1000020";
			String CTaxID = sqlx.buscarUno(sqlTax).get(0).toString();
			Integer CTaxIDC = 0;
			if (CTaxID != null) {
				CTaxIDC = Integer.parseInt(CTaxID);
			}
			;
			String sqlTaxIn = "";
			COrderTax_X cotx = new COrderTax_X();
			COrderTax cot;
			double finalValue;
			// cou.setGrandtotal(AmountGT);
			// cou.setTotallines(Amount);
			// cox.salvarOrden(cou);
			if (CTaxIDC == 0) {
				cot = new COrderTax();
				cot.setAd_client_id(ad_client_id);
				cot.setAd_org_id(ad_org_id);
				cot.setC_order_id(COrderID);
				cot.setC_tax_id(1000020);
				cot.setCreated(col.getCreated());
				cot.setCreatedby(col.getCreatedby());
				cot.setIsactive("Y");
				cot.setIstaxincluded(mpl.getIstaxincluded());
				cot.setTaxamt(dR.decimalRedondeo(AmountGTNoAVE2 - (AmountGTNoAVE2 / 1.13)));
				cot.setTaxbaseamt(dR.decimalRedondeo(AmountGTNoAVE2 / 1.13));
				cot.setUpdated(col.getCreated());
				cot.setUpdatedby(col.getUpdatedby());
				cotx.salvar(cot);
			} else {
				String colC1 = "select count(*) from c_orderline where C_Order_ID=" + COrderID;
				Integer colCX1 = Integer.parseInt(sqlx.buscarUno(colC1).get(0).toString());
				COrderLine cold1;
				Float vcold = new Float(0.0);
				cot = new COrderTax();
				if (colOld != null) {
					cot = cotx.buscarUno(COrderID, 1000020);
					cot.setTaxamt(dR.decimalRedondeo(AmountGTNoAVE2 - (AmountGTNoAVE2 / 1.13)));
					cot.setTaxbaseamt(dR.decimalRedondeo(AmountGTNoAVE2 / 1.13));
					cot.setUpdated(col.getUpdated());
					cot.setUpdatedby(col.getUpdatedby());
					cotx.salvar(cot);
				} else {
					if (c != null) {
						vcold = colx.buscarUno(Integer.parseInt(c)).getLinenetamt();
					}
					// if (colCX==0){
					cot = cotx.buscarUno(COrderID, 1000020);
					cot.setTaxamt(dR.decimalRedondeo(AmountGTNoAVE2 - (AmountGTNoAVE2 / 1.13)));
					cot.setTaxbaseamt(dR.decimalRedondeo(AmountGTNoAVE2 / 1.13));
					cot.setUpdated(col.getUpdated());
					cot.setUpdatedby(col.getUpdatedby());
					cotx.salvar(cot);
				}
			}
			// if (colCX == 1) {
			// COrderTax_X cotx = new COrderTax_X();
			// Long errorTax = cotx.eliminarTodos(COrderID);
			// } else {
			// if (colCX != 0) {
			// if (cold.getC_charge_id() != null) {
			// if (cold.getC_charge_id() != 1004412) {
			// COrderTax_X cotx = new COrderTax_X();
			// COrderTax cot = new COrderTax();
			// COrderTax cotn = new COrderTax();
			// cot = cotx.buscarUno(COrderID, 1000020);
			// cotn = cot;
			// cotn.setTaxbaseamt(cot.getTaxbaseamt() +
			// dR.decimalRedondeo(col.getLinenetamt() / 1.13));
			// cotn.setTaxamt(cot.getTaxamt()
			// + dR.decimalRedondeo((col.getLinenetamt()) - (col.getLinenetamt() / 1.13)));
			// cotx.salvar(cotn);
			// }
			// } else {
			// COrderTax_X cotx = new COrderTax_X();
			// COrderTax cot = new COrderTax();
			// COrderTax cotn = new COrderTax();
			// cot = cotx.buscarUno(COrderID, 1000020);
			// cotn = cot;
			// if (cot != null) {
			// cotn.setTaxbaseamt(cot.getTaxbaseamt() +
			// dR.decimalRedondeo(col.getLinenetamt() / 1.13));
			// cotn.setTaxamt(cot.getTaxamt()
			// + dR.decimalRedondeo((col.getLinenetamt()) - (col.getLinenetamt() / 1.13)));
			// cotx.salvar(cotn);
			// }
			// }
			// }
			// }
			// Fin de logica

			// Promociones
			COrder_X cox = new COrder_X();
			COrder cou = cox.buscarUno(COrderID);
			cou = cox.buscarUno(COrderID);
			// if (cb.getIspromocion().equals("Y")){
			MPromocion mpro = new MPromocion();
			MPromocion_X mprox = new MPromocion_X();
			COrderLine colp = new COrderLine();
			mpro = mprox.buscarTodos(col.getM_product_id(), col.getC_bpartner_id());
			Integer MProductPromo = 0;
			// MPromocionLine mproline = new MPromocionLine();
			List<MPromocionLine> lineasPromo = null;
			MPromocionLine_X mprolinex = new MPromocionLine_X();
			if (mpro != null) {
				// mproline = mprolinex.buscarTodos(mpro.getM_promocion_id());
				lineasPromo = mprolinex.buscarLineasPromocion(mpro.getM_promocion_id());
			}
			if (lineasPromo != null) {
				for (int i = 0; i < lineasPromo.size(); i++) {

					try {
						colp = colx.buscarPromocion(lineasPromo.get(i).getM_product_id(), col.getC_order_id());
						colx.eliminarUno(colp.getC_orderline_id());
					} catch (Exception e) {
						// No hay promocion
					}
				}
			}
			// Actualizamos el encabezado del pedido
			// Float Amount = colx.sumarTodoMonto(cou.getC_order_id());
			// Float AmountGT = colx.sumarTodoMontoGrandTotal(cou.getC_order_id());
			// cou.setGrandtotal(Amount);
			// cou.setTotallines(AmountGT);
			// cox.salvarOrden(cou);
			// }
			// Flete automatico
			sql = "SELECT C_OrderLine_ID FROM C_ORDERLINE WHERE C_ORDER_ID =" + COrderID
					+ " and c_charge_id not in (1004412,1004577)";
			List<Object> dato = sqlHql.buscarUno(sql);
			if (dato.size() > 0) {
				Integer COrderLineIDFlete = Integer.parseInt(dato.get(0).toString());
				if (COrderLineIDFlete != null) {
					COrderLine coul = colx.buscarUno(COrderLineIDFlete);
					Float qty = colx.sumarTodoQTY(COrderID);
					coul.setQtyordered(qty);
					coul.setQtyentered(qty);
					// coul.setQtypendiente(qty);
					MProduct_X mpx = new MProduct_X();
					MProduct mp = new MProduct();
					mp = mpx.buscarUno(col.getM_product_id());
					esMascota = mpx.buscarProductoEsMascota(col.getM_product_id());
					if (coul.getC_bpartner_id() == 1006989 && ad == 1000013)
						esProductoSinFlete = mpx.buscarProductoMP(col.getM_product_id());
					if (!esMascota && !esProductoSinFlete) {
						coul.setLinenetamt(
								(coul.getPriceactual() != null ? new Float(coul.getPriceactual()) : 0) * (qty));
						colx.salvarOrden(coul);
					} else {
						if (esMascota) {
							coul.setLinenetamt(new Float(0.0));
							colx.salvarOrden(coul);
						}
					}
				}
			}
			// Flete automatico
			// Quota AVE
			List<COrderLine> colTodos = colx.buscarTodos(COrderID);
			MProduct_X mpx = new MProduct_X();
			MProduct mp = new MProduct();
			Float qtyAve = new Float(0.0);
			COrder co = new COrder();
			cox = new COrder_X();
			for (COrderLine colQA : colTodos) {
				if (colQA.getM_product_id() != null) {
					mp = mpx.buscarUno(colQA.getM_product_id());
					if (mp.getM_product_category_id() == 1000005 || mp.getM_product_category_id() == 1000004) {
						qtyAve = colQA.getQtyentered() + qtyAve;
					}
				}
			}
			Integer cChargueID = 1004412;
			COrderLine cOrderLineIDQAve = colx.buscarQuotaAve(COrderID, cChargueID);
			if (cOrderLineIDQAve != null) {
				cOrderLineIDQAve.setQtyordered(qtyAve);
				cOrderLineIDQAve.setQtyentered(qtyAve);
				cOrderLineIDQAve.setLinenetamt(qtyAve * cOrderLineIDQAve.getPriceentered());
				colx.salvarOrden(cOrderLineIDQAve);
			} else {
				co = cox.buscarUno(COrderID);
				quotaAve(co);
				cOrderLineIDQAve = colx.buscarQuotaAve(COrderID, cChargueID);
				cOrderLineIDQAve.setQtyordered(qtyAve);
				cOrderLineIDQAve.setQtyentered(qtyAve);
				cOrderLineIDQAve.setLinenetamt(qtyAve * cOrderLineIDQAve.getPriceentered());
				colx.salvarOrden(cOrderLineIDQAve);
			}
			if (qtyAve < 1) {
				colx.eliminarUno(cOrderLineIDQAve.getC_orderline_id());
			}
			COrder cou1 = cox.buscarUno(COrderID);
			Float Amount1 = colx.sumarTodoMonto(cou.getC_order_id());
			Float AmountGT1 = colx.sumarTodoMontoGrandTotal(cou.getC_order_id());
			Float AmountGTNoAVE = colx.sumarTodoMontoGrandTotalSinAve(cou.getC_order_id());
			cou1.setGrandtotal(AmountGT1);
			cou1.setTotallines(Amount1);
			cox.salvarOrden(cou1);
			request.getRequestDispatcher("busquedaPedidos?c_order_id=" + COrderID + "&c_bpartner_id=" + CBpartnerID)
					.forward(request, response);
			return;
		}
		// Escribimos el TAX
		String sqlTax = "select count(*) from C_OrderTax where C_Order_ID=" + COrderID + " and C_Tax_ID=1000020";
		String CTaxID = sqlx.buscarUno(sqlTax).get(0).toString();
		Integer CTaxIDC = 0;
		if (CTaxID != null) {
			CTaxIDC = Integer.parseInt(CTaxID);
		}
		;
		String sqlTaxIn = "";
		COrderTax_X cotx = new COrderTax_X();
		COrderTax cot;
		double finalValue;
		COrder_X cox = new COrder_X();
		COrder co = new COrder();
		COrder cou = new COrder();

		COrder_X orderDao = new COrder_X();
		COrder order = orderDao.buscarUno(COrderID);
		// Actualizamos el encabezado del pedido
		// cou = cox.buscarUno(COrderID);
		// Float Amount = colx.sumarTodoMonto(cou.getC_order_id());
		// cou.setGrandtotal(Amount);
		// cou.setTotallines(Amount);
		// cox.salvarOrden(cou);

		// cox.salvarOrden(cou);
		try {

			// GUARDA LA LINEA
			colx.salvarOrden(col);

			// DESCUENTO FLETE

			CBPartner_X partnerDao = new CBPartner_X();
			final CBPartner partner;
			partner = partnerDao.buscarUno(order.getC_bpartner_id());
//			&& !COrder.get(0).getC_BPartner().IsPromocion()
			int[] pricelistids = { 1000259, 1000264, 1000266, 1000265,1000094 };
			if (partner.getMas_pricelist_id()==null) {
				partner.setMas_pricelist_id(0);
			}
			boolean aplica_cliente = IntStream.of(pricelistids).anyMatch(x -> x == partner.getMas_pricelist_id());
			if (aplica_cliente && order.getEsentregalocal().equals("Y")) {
				descuentoFlete(order, col);
			}

		} catch (Exception e) {
			throw new ServletException("Ocurrio un error." + e.toString());
		}

		MProduct_X productDao = new MProduct_X();
		COrderLine_X lineDao = new COrderLine_X();
		final MProduct producto;
		producto = productDao.buscarUno(col.getM_product_id());

		int[] productos_promoescala = { 1001569, 1002867, 100151, 1000680 };

		boolean aplicaPromoEscala = IntStream.of(productos_promoescala).anyMatch(x -> x == producto.getM_product_id());

		MProduct mp = new MProduct();
		MProduct_X mpx = new MProduct_X();

		
		CBPartner_X partnerDao = new CBPartner_X();
		final CBPartner partner;
		partner = partnerDao.buscarUno(order.getC_bpartner_id());

		int[] pricelistids = { 1000259, 1000264, 1000266, 1000265,1000094 };
		boolean aplica_cliente = IntStream.of(pricelistids).anyMatch(x -> x == partner.getM_pricelist_id());
		
		
		//PROMOCIONES NORMALES, SE AGREGO EXCEPCION PARA CLIENTE JALEAS DEL PINO
		//CON PRODUCTO DOGSTAR DE 44
		if (!aplicaPromoEscala || partner.getC_bpartner_id()==1011583) {

			// Promociones de productos
			MPromocion mpro = new MPromocion();
			MPromocion_X mprox = new MPromocion_X();
			MPromoClasificacion_X mproxc = new MPromoClasificacion_X();
			MPromocionLine mproline = new MPromocionLine();
			List<MPromocionLine> lineasPromo = null;
			MPromocionLine_X mprolinex = new MPromocionLine_X();
			adsx = new AdSequence_X();
			Integer promocionQTY = 0;
			// 1:macotas 2:pecuario 3:eq 4:materiaprima
			Integer tipoDePromocion;
			mp = mpx.buscarUno(col.getM_product_id());
			mpro = (partner.getC_bpartner_id()==1011583)?mprox.buscarPorCliente(col.getM_product_id(), partner.getC_bpartner_id()): mprox.buscarUno(col.getM_product_id());
			Integer MProductPromo = 0;
			List<Integer> clasi = null;
			cbx = new CBPartner_X();
			cb = cbx.buscarUno(col.getC_bpartner_id());
			// System.out.println("MPROMOCIONID***************************:" +
			// mpro.getM_promocion_id());

			if (mpro != null) {

				// if (cb.getT_clasificacion_id() == null) {
				// mpro = null;
				// } else {
				List<MPromoClasificacion> clasificaciones = mproxc.buscarTodos(mpro.getM_promocion_id());
				if (clasificaciones != null) {
					clasi = new ArrayList<Integer>();
					for (int i = 0; i < clasificaciones.size(); i++) {
						clasi.add(clasificaciones.get(i).getT_clasificacion_id());
					}
				}
				if (clasi != null) {

					if (clasi.contains(cb.getT_clasificacion_id())) {

						if (mpro != null) {

							// mproline = mprolinex.buscarTodos(mpro.getM_promocion_id());
							lineasPromo = mprolinex.buscarLineasPromocion(mpro.getM_promocion_id());

							if (lineasPromo != null) {
								// MProductPromo = mproline.getM_product_id();
								promocionQTY = (int) (col.getQtyentered() / mpro.getQtyPromocion());
							}
							// else
							// MProductPromo = mpro.getM_product_id();
						}

					} else if (!clasi.contains(cb.getT_clasificacion_id())) {
						mpro = null;
					}
				} else if (clasi == null) {
					if (mpro != null) {

						// mproline = mprolinex.buscarTodos(mpro.getM_promocion_id());
						lineasPromo = mprolinex.buscarLineasPromocion(mpro.getM_promocion_id());

						if (lineasPromo != null) {
							// MProductPromo = mproline.getM_product_id();
							promocionQTY = (int) (col.getQtyentered() / mpro.getQtyPromocion());
						}
						// else
						// MProductPromo = mpro.getM_product_id();
					}

				}
			}

			if (lineasPromo != null) {

				for (int i = 0; i < lineasPromo.size(); i++) {

					if (promocionQTY > 0) {
						COrderLine colp = new COrderLine();
						colp = colx.buscarPromocion(col.getM_product_id(), col.getC_order_id());
						// if (colp == null) {
						// Secuencia
						// ads = new AdSequence();
						// List max2 = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from
						// c_orderline ");
						String max2S = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from c_orderline ").get(0)
								.toString();
						// String max2S=max2.toString();
						max2S = max2S.replace("]", "").replaceAll("\\[", "");
						Integer max2I = Integer.parseInt(max2S);
						maxCOrderLineID = max2I;
						colp = col;
						colp.setC_orderline_id(maxCOrderLineID);
						// }
						// colp.setM_product_id(MProductPromo);
						colp.setM_product_id(lineasPromo.get(i).getM_product_id());
						// Integer promocionCalculada = promocionQTY * mproline.getQtyPromocion();
						Integer promocionCalculada = promocionQTY * lineasPromo.get(i).getQtyPromocion();
						colp.setQtyentered(Float.parseFloat(promocionCalculada.toString()));
						colp.setQtyordered(Float.parseFloat(promocionCalculada.toString()));
						colp.setQtyreserved(Float.parseFloat("0.0"));
						colp.setQtypendiente(Float.parseFloat(promocionCalculada.toString()));
						value = sqlHql.buscarUno("SELECT max(line) from C_OrderLine where C_Order_ID = " + COrderID);
						colp.setEspromocion("Y");
						maxLine = 0;
						if (value.get(0) != null) {
							maxLine = Integer.parseInt(value.get(0).toString()) + 10;
						} else {
							maxLine = 10;
						}
						;
						colp.setLine(maxLine);
						colp.setPriceactual(new Float(0.01));
						colp.setPricelist(new Float(0.01));
						colp.setPriceentered(new Float(0.01));
						colp.setLinenetamt(new Float(0.01) * promocionQTY);
						colp.setEspromocion("Y");
						MPromocionSalesRep_X mps = new MPromocionSalesRep_X();
						if (cb.getIspromocion().equals("N")  || cb.getC_bpartner_id()==1011583) {
							if (mps.buscarAdUserCBPartner(ad, mpro.getM_promocion_id()))
								colx.salvarOrden(colp);
						}
					} else {
						COrderLine colp = new COrderLine();
						colp = colx.buscarPromocion(col.getM_product_id(), col.getC_order_id());
						if (colp != null)
							colx.eliminarUno(colp.getC_orderline_id());
					}
				}
			}
		} else if(!aplica_cliente) {
			// PROMOCION ESCALA
			calculaPromocionEscala(order, col);
		}
		// Flete automatico
		sql = "SELECT C_OrderLine_ID FROM C_ORDERLINE WHERE C_ORDER_ID =" + COrderID
				+ " and c_charge_id not in (1004412,1004577,1009274)";
		List<Object> sqlX = sqlHql.buscarUno(sql);
		mpx = new MProduct_X();
		if (sqlX.size() > 0) {
			Integer COrderLineIDFlete = Integer.parseInt(sqlX.get(0).toString());

			COrderLine coul = colx.buscarUno(COrderLineIDFlete);
			Float qty = colx.sumarTodoQTY(COrderID);
			coul.setQtyordered(qty);
			coul.setQtyentered(qty);
			// coul.setEsgeneradoauto("Y");
			esMascota = mpx.buscarProductoEsMascota(col.getM_product_id());
			if (coul.getC_bpartner_id() == 1006989 && ad == 1000013)
				esProductoSinFlete = mpx.buscarProductoMP(col.getM_product_id());
			if (!esMascota && !esProductoSinFlete) {
				coul.setLinenetamt(coul.getPriceactual() * (qty));
				colx.salvarOrden(coul);
			}

			// if (!esMascota) {
			// colx.salvarOrden(coul);
			// }
			else {
				if (esMascota) {
					coul.setLinenetamt(new Float(0.0));
					colx.salvarOrden(coul);
				}
			}
		}
		// Flete automatico}

		// Quota AVE
		List<COrderLine> colTodos = colx.buscarTodos(COrderID);
		mp = new MProduct();
		Float qtyAve = new Float(0.0);
		for (COrderLine colQA : colTodos) {
			if (colQA.getM_product_id() != null) {
				mp = mpx.buscarUno(colQA.getM_product_id());
				if (mp.getM_product_category_id() == 1000005 || mp.getM_product_category_id() == 1000004) {
					qtyAve = colQA.getQtyentered() + qtyAve;
				}
			}
		}
		Integer cChargueID = 1004412;
		COrderLine cOrderLineIDQAve = colx.buscarQuotaAve(COrderID, cChargueID);
		if (cOrderLineIDQAve != null) {
			cOrderLineIDQAve.setQtyordered(qtyAve);
			cOrderLineIDQAve.setQtyentered(qtyAve);
			Float linenetamt = (qtyAve * cOrderLineIDQAve.getPriceentered());
			cOrderLineIDQAve.setLinenetamt(redondearFloat(linenetamt, 2));
			colx.salvarOrden(cOrderLineIDQAve);
		} else {
			co = cox.buscarUno(COrderID);
			quotaAve(co);
			cOrderLineIDQAve = colx.buscarQuotaAve(COrderID, cChargueID);
			cOrderLineIDQAve.setQtyordered(qtyAve);
			cOrderLineIDQAve.setQtyentered(qtyAve);
			Float linenetamt = (qtyAve * cOrderLineIDQAve.getPriceentered());
			cOrderLineIDQAve.setLinenetamt(redondearFloat(linenetamt, 2));
			colx.salvarOrden(cOrderLineIDQAve);
		}
		if (qtyAve < 1) {
			colx.eliminarUno(cOrderLineIDQAve.getC_orderline_id());
		}
		// Actualizamos el encabezado del pedido
		cou = cox.buscarUno(COrderID);
		Float Amount = colx.sumarTodoMonto(cou.getC_order_id());
		Float AmountGT = colx.sumarTodoMontoGrandTotal(cou.getC_order_id());
		Float AmountGTNoAVE = colx.sumarTodoMontoGrandTotalSinAve(cou.getC_order_id());
		cou.setGrandtotal(AmountGT);
		cou.setTotallines(Amount);
		cox.salvarOrden(cou);
		if (CTaxIDC == 0) {
			cot = new COrderTax();
			cot.setAd_client_id(ad_client_id);
			cot.setAd_org_id(ad_org_id);
			cot.setC_order_id(COrderID);
			cot.setC_tax_id(1000020);
			cot.setCreated(col.getCreated());
			cot.setCreatedby(col.getCreatedby());
			cot.setIsactive("Y");
			cot.setIstaxincluded(mpl.getIstaxincluded());
			cot.setTaxamt(dR.decimalRedondeo(AmountGTNoAVE - (AmountGTNoAVE / 1.13)));
			cot.setTaxbaseamt(dR.decimalRedondeo(AmountGTNoAVE / 1.13));
			cot.setUpdated(col.getCreated());
			cot.setUpdatedby(col.getUpdatedby());
			cotx.salvar(cot);
		} else {
			String colC = "select count(*) from c_orderline where C_Order_ID=" + COrderID;
			Integer colCX = Integer.parseInt(sqlx.buscarUno(colC).get(0).toString());
			COrderLine cold;
			Float vcold = new Float(0.0);
			cot = new COrderTax();
			if (colOld != null) {
				cot = cotx.buscarUno(COrderID, 1000020);
				cot.setTaxamt(dR.decimalRedondeo(AmountGTNoAVE - (AmountGTNoAVE / 1.13)));
				cot.setTaxbaseamt(dR.decimalRedondeo(AmountGTNoAVE / 1.13));
				cot.setUpdated(col.getUpdated());
				cot.setUpdatedby(col.getUpdatedby());
				cotx.salvar(cot);
			} else {
				if (c != null) {
					vcold = colx.buscarUno(Integer.parseInt(c)).getLinenetamt();
				}
				// if (colCX==0){
				cot = cotx.buscarUno(COrderID, 1000020);
				cot.setTaxamt(dR.decimalRedondeo(AmountGTNoAVE - (AmountGTNoAVE / 1.13)));
				cot.setTaxbaseamt(dR.decimalRedondeo(AmountGTNoAVE / 1.13));
				cot.setUpdated(col.getUpdated());
				cot.setUpdatedby(col.getUpdatedby());
				cotx.salvar(cot);
			}
		}
		// Fin del TAX
		request.getRequestDispatcher("busquedaPedidos?c_order_id=" + COrderID + "&c_bpartner_id=" + CBpartnerID)
				.forward(request, response);
	}

	/**
	 * FLETE AUTOMATICO
	 */
	@SuppressWarnings("rawtypes")
	public boolean fleteAutomatico(COrder COrderP, String tipoPedido) {
		Integer COrderID = COrderP.getC_order_id();
		String QtyEntered = "0";
		Integer ad = COrderP.getAd_user_id();
		Integer CBpartnerID = COrderP.getC_bpartner_id();
		CCharge cc = new CCharge();
		CCharge_X ccx = new CCharge_X();
		CLocation cl = new CLocation();
		CLocation_X clx = new CLocation_X();
		CBPartnerLocation cbpl = new CBPartnerLocation();
		CBPartnerLocation_X cbplx = new CBPartnerLocation_X();
		cbpl = cbplx.buscarUno(COrderP.getDropship_location_id());
		cl = clx.buscarUno(cbpl.getC_location_id());
		COrderLine_X colx = new COrderLine_X();
		// AdSequence_X adsx = new AdSequence_X();
		// AdSequence ads = new AdSequence();
		sqlHQL_X sqlHqlC = new sqlHQL_X();
		// List max3 = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from
		// c_orderline ");
		String max3S = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from c_orderline ").get(0).toString();
		// String max3S=max3.toString();
		max3S = max3S.replace("]", "").replaceAll("\\[", "");
		Integer max3I = Integer.parseInt(max3S);
		int maxCOrderLineID = max3I;
		// adsx.salvarProximaSequencia(233);
		COrderLine col = new COrderLine();
		Integer ad_client_id = 1000000;
		Integer ad_org_id = 1000000;
		MPriceList_X mplx = new MPriceList_X();
		CBPartner_X cbx = new CBPartner_X();
		CBPartner cb = new CBPartner();
		cb = cbx.buscarUno(CBpartnerID);
		MPriceList mpl = mplx.buscarUno(cb.getM_pricelist_id());
		try {
			cc = ccx.buscarUnoByCityTax(cl.getC_city_id(), mpl.getIstaxincluded());
		} catch (Exception e) {
			return false;
		}
		col.setC_orderline_id(maxCOrderLineID);
		col.setAd_client_id(ad_client_id);
		col.setAd_org_id(ad_org_id);
		col.setC_order_id(COrderID);
		col.setIsactive("Y");
		Date created;
		sqlHQL_X sqlHql = new sqlHQL_X();
		created = new Date(System.currentTimeMillis());
		java.sql.Timestamp createdTS = new Timestamp(created.getTime());
		col.setCreated(createdTS);
		col.setCreatedby(ad);
		col.setUpdated(createdTS);
		col.setUpdatedby(ad);
		List value = sqlHql.buscarUno("SELECT max(line) from C_OrderLine where C_Order_ID = " + COrderID);
		Integer maxLine = 0;
		if (value.get(0) != null) {
			maxLine = Integer.parseInt(value.get(0).toString()) + 10;
		} else {
			maxLine = 10;
		}
		;
		col.setLine(maxLine);
		col.setC_bpartner_id(CBpartnerID);
		List valueLocation = sqlHql
				.buscarUno("SELECT c_bpartner_location_id from C_Order where C_Order_ID = " + COrderID);
		col.setC_bpartner_location_id(Integer.parseInt(valueLocation.get(0).toString()));
		col.setDateordered(createdTS);
		col.setDatepromised(createdTS);
		sqlHQL_X datos = new sqlHQL_X();
		String sql = "";
		List<Object> pp;
		col.setC_charge_id(cc.getC_charge_id());
		sql = "select chargeamt from c_charge where c_charge_id = " + cc.getC_charge_id();
		pp = datos.buscarUno(sql);
		col.setM_warehouse_id(COrderP.getM_warehouse_id());
		col.setC_uom_id(1000003);
		col.setQtyordered(Float.parseFloat(QtyEntered));
		col.setQtyreserved(new Float(0.0));
		col.setQtydelivered(new Float(0));
		col.setQtyinvoiced(new Float(0));
		col.setC_currency_id(100);
		Float PriceEntered;
		if (pp.get(0) == null || tipoPedido.equals("10000072") || tipoPedido.equals("10000073")) {
			PriceEntered = new Float(0.0);
		} else {
			PriceEntered = Float.parseFloat(pp.get(0).toString());
		}
		col.setPriceactual(PriceEntered != null ? new Float(PriceEntered) : 0);
		col.setPricelist(PriceEntered != null ? new Float(PriceEntered) : 0);
		col.setPricelimit(new Float(0));
		col.setLinenetamt(((PriceEntered != null ? new Float(PriceEntered) : 0) * (Float.parseFloat(QtyEntered))));
		col.setDiscount(new Float(0));
		col.setFreightamt(new Float(0));
		col.setC_tax_id(1000020);
		col.setM_attributesetinstance_id(0);
		col.setIsdescription("N");
		col.setProcessed("N");
		col.setQtyentered(Float.parseFloat(QtyEntered));
		col.setPriceentered((PriceEntered));
		col.setPricecost(new Float(0));
		col.setQtylostsales(new Float(0));
		col.setRramt(new Float(0));
		col.setIsconsumesforecast("N");
		col.setCreatefrom("N");
		col.setCreateshipment("N");
		col.setM_product_id(null);
		col.setEsgeneradoauto("Y");
		colx.salvarOrden(col);
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public boolean quotaAve(COrder COrderP) {
		CCharge_X ccx = new CCharge_X();
		CCharge ccAve = new CCharge();
		Integer cChargueID = 1004412;
		ccAve = ccx.buscarUnoByID(cChargueID.toString()).get(0);
		COrderLine_X colx = new COrderLine_X();
		Float qty = new Float(0.0);
		Integer COrderID = COrderP.getC_order_id();
		String QtyEntered = qty.toString();
		Integer ad = COrderP.getAd_user_id();
		Integer CBpartnerID = COrderP.getC_bpartner_id();
		CCharge cc = ccAve;
		// CLocation cl = new CLocation();
		CLocation_X clx = new CLocation_X();
		COrderLine cOrderLineIDQAve = colx.buscarQuotaAve(COrderP.getC_order_id(), cChargueID);
		if (cOrderLineIDQAve == null) {
			sqlHQL_X sqlHqlC = new sqlHQL_X();
			// List max4 = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from
			// c_orderline ").get(0).toString();
			String max4S = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from c_orderline ").get(0).toString();
			max4S = max4S.replace("]", "").replaceAll("\\[", "");
			Integer max4I = Integer.parseInt(max4S);
			int maxCOrderLineID = max4I;
			// adsx.salvarProximaSequencia(233);
			COrderLine col = new COrderLine();
			// cl = clx.buscarUno(COrderP.getDropship_location_id());
			Integer ad_client_id = 1000000;
			Integer ad_org_id = 1000000;
			Float iva = new Float(1.00);
			CBPartner_X cbx = new CBPartner_X();
			CBPartner cb = new CBPartner();
			cb = cbx.buscarUno(CBpartnerID);
			col.setC_orderline_id(maxCOrderLineID);
			col.setAd_client_id(ad_client_id);
			col.setAd_org_id(ad_org_id);
			col.setC_order_id(COrderID);
			col.setIsactive("Y");
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Date date, created;
			sqlHQL_X sqlHql = new sqlHQL_X();
			date = new Date(System.currentTimeMillis());
			created = new Date(System.currentTimeMillis());
			java.sql.Timestamp createdTS = new Timestamp(created.getTime());
			col.setCreated(createdTS);
			col.setCreatedby(ad);
			col.setUpdated(createdTS);
			col.setUpdatedby(ad);
			List value = sqlHql.buscarUno("SELECT max(line) from C_OrderLine where C_Order_ID = " + COrderID);
			Integer maxLine = 0;
			if (value.get(0) != null) {
				maxLine = Integer.parseInt(value.get(0).toString()) + 10;
			} else {
				maxLine = 10;
			}
			;
			col.setLine(maxLine);
			col.setC_bpartner_id(CBpartnerID);
			List valueLocation = sqlHql
					.buscarUno("SELECT c_bpartner_location_id from C_Order where C_Order_ID = " + COrderID);
			col.setC_bpartner_location_id(Integer.parseInt(valueLocation.get(0).toString()));
			col.setDateordered(createdTS);
			col.setDatepromised(createdTS);
			sqlHQL_X datos = new sqlHQL_X();
			String sql = "";
			List<Object> pp;
			col.setC_charge_id(cc.getC_charge_id());
			sql = "select chargeamt from c_charge where c_charge_id = " + cc.getC_charge_id();
			pp = datos.buscarUno(sql);
			// List valueMWareHouse = sqlHql.buscarUno("SELECT m_warehouse_id
			// from C_Order where C_Order_ID = " + COrderID);
			col.setM_warehouse_id(COrderP.getM_warehouse_id());
			col.setC_uom_id(1000003);
			col.setQtyordered(Float.parseFloat(QtyEntered));
			col.setQtyreserved(new Float(0.0));
			col.setQtydelivered(new Float(0));
			col.setQtyinvoiced(new Float(0));
			col.setC_currency_id(100);
			Float PriceEntered;
			if (pp.get(0) == null) {
				PriceEntered = new Float(0.0);
			} else {
				PriceEntered = Float.parseFloat(pp.get(0).toString());
			}
			col.setPriceactual(PriceEntered != null ? new Float(PriceEntered) : 0);
			col.setPricelist(PriceEntered != null ? new Float(PriceEntered) : 0);
			col.setPricelimit(new Float(0));
			Float linenetamt = (PriceEntered != null ? new Float(PriceEntered) : 0) * (Float.parseFloat(QtyEntered));
			col.setLinenetamt(redondearFloat(linenetamt, 2));
			col.setDiscount(new Float(0));
			col.setFreightamt(new Float(0));
			// col.setC_tax_id(cChargueID);
			col.setM_attributesetinstance_id(0);
			col.setIsdescription("N");
			col.setCreatedby(COrderP.getAd_user_id());
			col.setProcessed("N");
			col.setQtyentered(Float.parseFloat(QtyEntered));
			col.setPriceentered((PriceEntered));
			col.setPricecost(new Float(0));
			// 1000015
			col.setC_tax_id(1000015);
			col.setQtylostsales(new Float(0));
			col.setRramt(new Float(0));
			col.setIsconsumesforecast("N");
			col.setCreatefrom("N");
			col.setCreateshipment("N");
			colx.salvarOrden(col);
		}

		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * DESCUENTO FLETE
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public boolean descuentoFlete(COrder COrderP, COrderLine orderline) {
		CCharge_X ccx = new CCharge_X();
		MProduct_X productDao = new MProduct_X();
		MProduct producto = new MProduct();

		COrderLine currentline = new COrderLine();

		COrderLine_X lineDao = new COrderLine_X();
		currentline = lineDao.buscarUno(orderline.getC_orderline_id());

		producto = productDao.buscarUno(currentline.getM_product_id());
		if (producto.getApply_discount().equals("Y")) {

			CCharge cuentaDescuentoFlete = new CCharge();
			Integer cChargueID = 1004577;
			cuentaDescuentoFlete = ccx.buscarUnoByID(cChargueID.toString()).get(0);
			COrderLine_X colx = new COrderLine_X();
			Float qty = new Float(0.0);
			qty = currentline.getQtyentered();
			Integer COrderID = COrderP.getC_order_id();
			String QtyEntered = qty.toString();
			Integer ad = COrderP.getAd_user_id();
			CCharge cc = cuentaDescuentoFlete;
			// CLocation cl = new CLocation();
			CLocation_X clx = new CLocation_X();
			COrderLine lineaDescuentoFlete = colx.buscarLineaDescuentoFlete(COrderP.getC_order_id(),
					currentline.getC_orderline_id());

			if (lineaDescuentoFlete == null) {
				sqlHQL_X sqlHqlC = new sqlHQL_X();
				// List max4 = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from
				// c_orderline ").get(0).toString();
				String max4S = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from c_orderline ").get(0).toString();
				max4S = max4S.replace("]", "").replaceAll("\\[", "");
				Integer max4I = Integer.parseInt(max4S);
				int maxCOrderLineID = max4I;
				// adsx.salvarProximaSequencia(233);
				COrderLine col = new COrderLine();
				// cl = clx.buscarUno(COrderP.getDropship_location_id());
				Integer ad_client_id = 1000000;
				Integer ad_org_id = 1000000;

				col.setC_orderline_id(maxCOrderLineID);
				col.setAd_client_id(ad_client_id);
				col.setAd_org_id(ad_org_id);
				col.setC_order_id(COrderID);
				col.setIsactive("Y");
				col.setC_orderline_id_discount(currentline.getC_orderline_id());
				col.setDescription("DESCUENTO FLETE (" + producto.getValue() + "-" + producto.getName() + ")");
				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				Date date, created;
				sqlHQL_X sqlHql = new sqlHQL_X();
				date = new Date(System.currentTimeMillis());
				created = new Date(System.currentTimeMillis());
				java.sql.Timestamp createdTS = new Timestamp(created.getTime());
				col.setCreated(createdTS);
				col.setCreatedby(ad);
				col.setUpdated(createdTS);
				col.setUpdatedby(ad);
				List value = sqlHql.buscarUno("SELECT max(line) from C_OrderLine where C_Order_ID = " + COrderID);
				Integer maxLine = 0;
				if (value.get(0) != null) {
					maxLine = Integer.parseInt(value.get(0).toString()) + 10;
				} else {
					maxLine = 10;
				}
				;
				col.setLine(maxLine);
				// col.setC_bpartner_id(CBpartnerID);
				// List valueLocation = sqlHql
				// .buscarUno("SELECT c_bpartner_location_id from C_Order where C_Order_ID = " +
				// COrderID);
				// col.setC_bpartner_location_id(Integer.parseInt(valueLocation.get(0).toString()));
				col.setDateordered(createdTS);
				// col.setDatepromised(createdTS);
				// sqlHQL_X datos = new sqlHQL_X();
				// String sql = "";
				// List<Object> pp;
				col.setC_charge_id(cc.getC_charge_id());
				// sql = "select chargeamt from c_charge where c_charge_id = " +
				// cc.getC_charge_id();
				// pp = datos.buscarUno(sql);
				// List valueMWareHouse = sqlHql.buscarUno("SELECT m_warehouse_id
				// from C_Order where C_Order_ID = " + COrderID);
				col.setM_warehouse_id(COrderP.getM_warehouse_id());
				col.setC_uom_id(1000003);
				col.setQtyordered(Float.parseFloat(QtyEntered));
				col.setQtyreserved(Float.parseFloat(QtyEntered));
				col.setQtydelivered(new Float(0));
				col.setQtyinvoiced(new Float(0));
				col.setC_currency_id(100);
				Float PriceEntered;
				// if (pp.get(0) == null) {
				// PriceEntered = new Float(0.0);
				// } else {
				// PriceEntered = Float.parseFloat(pp.get(0).toString());
				// }
				col.setPriceactual(
						producto.getFreight_discount() != null ? new Float(-producto.getFreight_discount()) : 0);
				col.setPricelist(
						producto.getFreight_discount() != null ? new Float(-producto.getFreight_discount()) : 0);
				col.setPricelimit(new Float(0));
				Float linenetamt = (producto.getFreight_discount() != null ? new Float(producto.getFreight_discount())
						: 0) * (Float.parseFloat(QtyEntered));
				col.setLinenetamt(redondearFloat(-linenetamt, 2));
				col.setDiscount(new Float(0));
				col.setFreightamt(new Float(0));
				// col.setC_tax_id(cChargueID);
				col.setM_attributesetinstance_id(0);
				col.setIsdescription("N");
				col.setCreatedby(COrderP.getAd_user_id());
				col.setProcessed("N");
				col.setQtyentered(Float.parseFloat(QtyEntered));
				col.setPriceentered((-producto.getFreight_discount()));
				col.setPricecost(new Float(0));
				// 1000015
				col.setC_tax_id(1000015);
				col.setQtylostsales(new Float(0));
				col.setRramt(new Float(0));
				col.setIsconsumesforecast("N");
				col.setCreatefrom("N");
				col.setCreateshipment("N");
				colx.salvarOrden(col);
			}
			return true;
		} else {
			return false;
		}

	}

	private void calculaPromocionEscala(COrder COrderP, COrderLine orderline) {

		MProduct_X productDao = new MProduct_X();
		COrderLine_X lineDao = new COrderLine_X();
		final MProduct producto;
		producto = productDao.buscarUno(orderline.getM_product_id());
		CBPartner_X partnerDao = new CBPartner_X();
		final CBPartner partner;
		partner = partnerDao.buscarUno(COrderP.getC_bpartner_id());
		int[] pricelistids = { 1000259, 1000264, 1000266, 1000265,1000094 };
		boolean aplica_cliente = IntStream.of(pricelistids).anyMatch(x -> x == partner.getM_pricelist_id());
		
		int[] productos_promoescala = { 1001569, 1002867, 100151, 1000680 };

		boolean aplicaPromoEscala = IntStream.of(productos_promoescala).anyMatch(x -> x == producto.getM_product_id());

		if (aplicaPromoEscala && !aplica_cliente && !partner.getIspromocion().equals("Y")) {
			try {
				List<COrderLine> lista = lineDao.buscarlineasPromoEscala(COrderP.getC_order_id());
				if (lista != null && lista.size() > 0) {

					Double total = lineDao.getSumProductosEscala(COrderP.getC_order_id());

					if (lista.size() == 1) {
						if (total < 100) {
							lineDao.eliminarPromosEscalaMix(COrderP.getC_order_id());
						} else {
							// AGREGA LINEA PROMO ESCALA PARA 1
							if (!partner.getIspromocion().equals("Y")) {
								if (total >= 100 && total < 250) {
									agregaPromoEscala(orderline, producto, new Float(2.0), false);
								} else if (total >= 250 && total < 500) {
									agregaPromoEscala(orderline, producto, new Float(7.0), false);
								} else if (total >= 500) {
									agregaPromoEscala(orderline, producto, new Float(20.0), false);
								}
							}
							

							if (COrderP.getEsentregalocal().equals("Y")) {
								descuentoFlete(COrderP, orderline);
							}
						}
					} else if (lista.size() > 1) {
						MProduct productoPromoMix = productDao.buscarUno(100151);
						lineDao.eliminarPromosEscala(COrderP.getC_order_id());
						lineDao.eliminarLineasDescuentoFlete(COrderP.getC_order_id());

						if (total < 100) {
							lineDao.eliminarPromosEscalaMix(COrderP.getC_order_id());
						}
						
						if (!partner.getIspromocion().equals("Y")) {
						if (total >= 100 && total < 250) {
							agregaPromoEscala(orderline, productoPromoMix, new Float(2.0), true);
						} else if (total >= 250 && total < 500) {
							agregaPromoEscala(orderline, productoPromoMix, new Float(7.0), true);
						} else if (total >= 500) {
							agregaPromoEscala(orderline, productoPromoMix, new Float(20.0), true);
						}
						}
						if (COrderP.getEsentregalocal().equals("Y")) {
							if (total>=100) {
								lineDao.eliminarLineasDescuentoFlete(COrderP.getC_order_id());
								
								for (COrderLine lineapromo : lista) {
										descuentoFlete(COrderP, lineapromo);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	private void recalculaPromocionEscala(COrder COrderP, COrderLine orderline) {

		MProduct_X productDao = new MProduct_X();
		COrderLine_X lineDao = new COrderLine_X();
		final MProduct producto;
		producto = productDao.buscarUno(orderline.getM_product_id());
		
		CBPartner_X partnerDao = new CBPartner_X();
		final CBPartner partner;
		partner = partnerDao.buscarUno(COrderP.getC_bpartner_id());
		
		int[] pricelistids = { 1000259, 1000264, 1000266, 1000265,1000094 };
		boolean aplica_cliente = IntStream.of(pricelistids).anyMatch(x -> x == partner.getM_pricelist_id());
		
		int[] productos_promoescala = { 1001569, 1002867, 100151, 1000680 };

		boolean aplicaPromoEscala = IntStream.of(productos_promoescala).anyMatch(x -> x == producto.getM_product_id());

		if (aplicaPromoEscala && !aplica_cliente && !partner.getIspromocion().equals("Y")) {
			try {
				List<COrderLine> lista = lineDao.buscarlineasPromoEscala(COrderP.getC_order_id());
				
				if (lista != null && lista.size() > 0) {

					Double total = lineDao.getSumProductosEscala(COrderP.getC_order_id());

					if (lista.size() == 1) {
						
							lineDao.eliminarPromosEscala(COrderP.getC_order_id());
							if (total < 100) {
								lineDao.eliminarPromosEscalaMix(COrderP.getC_order_id());
								lineDao.eliminarLineasDescuentoFlete(COrderP.getC_order_id());
							}
							// AGREGA LINEA PROMO ESCALA PARA 1
							if (total >= 100 && total < 250) {
								agregaPromoEscala(lista.get(0), producto, new Float(2.0), false);
							} else if (total >= 250 && total < 500) {
								agregaPromoEscala(lista.get(0), producto, new Float(7.0), false);
							} else if (total >= 500) {
								agregaPromoEscala(lista.get(0), producto, new Float(20.0), false);
							}

							if (COrderP.getEsentregalocal().equals("Y")) {
								descuentoFlete(COrderP, lista.get(0));
							}
						
					} else if (lista.size() > 1) {
						MProduct productoPromoMix = productDao.buscarUno(100151);
						lineDao.eliminarPromosEscala(COrderP.getC_order_id());
						if (total < 100) {
							lineDao.eliminarPromosEscalaMix(COrderP.getC_order_id());
							lineDao.eliminarLineasDescuentoFlete(COrderP.getC_order_id());
						}
						List<COrderLine> promomix = lineDao.buscarlineaPromoMix(lista.get(0).getC_order_id());

						if (total >= 100 && total < 250) {
							agregaPromoEscala(promomix.get(0), productoPromoMix, new Float(2.0), true);
						} else if (total >= 250 && total < 500) {
							agregaPromoEscala(promomix.get(0), productoPromoMix, new Float(7.0), true);
						} else if (total >= 500) {
							agregaPromoEscala(promomix.get(0), productoPromoMix, new Float(20.0), true);
						}

						if (COrderP.getEsentregalocal().equals("Y")) {
							if (total>=100) {
								lineDao.eliminarLineasDescuentoFlete(COrderP.getC_order_id());
								
								for (COrderLine lineapromo : lista) {
										descuentoFlete(COrderP, lineapromo);
								}
							}
						}
					}else if(lista.size()==0){
						try {
							lineDao.eliminarPromosEscalaMix(COrderP.getC_order_id());
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			} catch (Exception e) {
System.out.println(e);
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

	public void agregaPromoEscala(COrderLine linea, MProduct productoPromo, Float qty, boolean ispromomix) {
		COrderLine currentline = new COrderLine();

		COrderLine_X colx = new COrderLine_X();
		currentline = colx.buscarUno(linea.getC_orderline_id());
		sqlHQL_X sqlHqlC = new sqlHQL_X();
		sqlHQL_X sqlHql = new sqlHQL_X();
		List value;
		Integer maxCOrderLineID;
		COrderLine colp = new COrderLine();
		Integer maxLine = 0;

		String max2S = sqlHqlC.buscarUno("SELECT max(c_orderline_id) + 1 from c_orderline ").get(0).toString();
		max2S = max2S.replace("]", "").replaceAll("\\[", "");
		Integer max2I = Integer.parseInt(max2S);
		maxCOrderLineID = max2I;
		colp = currentline;

		if (ispromomix) {
			List<COrderLine> promomix = colx.buscarlineaPromoMix(linea.getC_order_id());
			if (promomix != null) {
//				colp=new COrderLine();
				colp.setC_orderline_id(promomix.get(0).getC_orderline_id());
			} else {
				colp.setC_orderline_id(maxCOrderLineID);
			}
		} else {
			colp.setC_orderline_id(maxCOrderLineID);
		}

		colp.setM_product_id(productoPromo.getM_product_id());
		colp.setQtyentered(qty);
		colp.setQtyordered(qty);
		colp.setQtyreserved(Float.parseFloat("0.0"));
		colp.setQtypendiente(qty);
		value = sqlHql.buscarUno("SELECT max(line) from C_OrderLine where C_Order_ID = " + linea.getC_order_id());
		colp.setEspromocion("Y");
//		colp.setIspromomix("Y");
		maxLine = 0;
		if (value != null) {
			if (value.get(0) != null) {
				maxLine = Integer.parseInt(value.get(0).toString()) + 10;
			} else {
				maxLine = 10;
			}
		}
		colp.setLine(maxLine);
		colp.setPriceactual(new Float(0.01));
		colp.setPricelist(new Float(0.01));
		colp.setPriceentered(new Float(0.01));
		colp.setLinenetamt(new Float(0.01) * qty);
		colp.setEspromocion("Y");
		if (ispromomix) {
			colp.setIspromomix("Y");
		} else {
			colp.setLink_orderline_id(linea.getC_orderline_id());
		}
		colx.salvarOrden(colp);

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

	public static Float redondearFloat(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return new Float(bd.doubleValue());
	}

}
