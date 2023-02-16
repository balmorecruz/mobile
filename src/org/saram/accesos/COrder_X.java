package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.AdUser;
import org.saram.modelo.COrder;
import org.saram.modelo.Prepedido;

public class COrder_X implements COrder_I {

	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> buscarTodos(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("createdby", ad.getAd_user_id()))
					.add(Restrictions.eq("issotrx", "Y"))
					.add(Restrictions.eq("esdeudor", "N"))
					.add(Restrictions.eq("updatedby", ad.getAd_user_id()))
					.add(Restrictions.eq("processed", "N"));
			List<COrder> lista = crit.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
//	@SuppressWarnings("unchecked")
//	@Override
//	
//	//Aqui debo revibir el salesrep y listar solo aduser
//	public List<COrder> buscarTodosPorCliente(Integer CBPartner) {
//		Session sesion;
//		try {
//			sesion = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
//		}
//		try {
//			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("c_bpartner_id", ad.getAd_user_id()))
//					.add(Restrictions.eq("issotrx", "Y"))
//					.add(Restrictions.eq("esdeudor", "N"))
////					.add(Restrictions.eq("updatedby", ad.getAd_user_id()))
//					.add(Restrictions.eq("processed", "N"));
//			List<COrder> lista = crit.list();
//			sesion.close();
//			return lista;
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			sesion.close();
//			return null;
//		}
//	}
	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> buscarTodosProcesados(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
//			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("createdby", ad.getAd_user_id()))
//					.add(Restrictions.eq("issotrx", "Y"))
//					.add(Restrictions.eq("processed", "Y"))
//					.add(Subqueries.propertyNotIn("c_order_id", DetachedCriteria.forClass(CInvoice.class)
//							.add(Restrictions.eq("docstatus", "CO"))
//							.add(Restrictions.eq("docaction", "CL"))
//							.setProjection(Property.forName("c_order_id"))
//							));
//			Query query=sesion.createQuery(
//					"from COrder co");
			Query query=          sesion.createQuery(
					"from COrder as co where (co.issotrx = 'Y' and docstatus <> 'CL' and co.processed='Y' and co.createdby = :aduserid and co.c_order_id not in"
					+ " (select b.c_order_id from CInvoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL')) "
					+ "and co.docstatus NOT IN ('IN', 'CO') AND DOCACTION = 'CO' "
					+ " or "
					+ "(co.issotrx = 'Y' and co.processed='N' and iscreditapproved = 'Y' and docstatus <> 'CL' and co.createdby = :aduserid and co.c_order_id not in"
					+ " (select b.c_order_id from CInvoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL'))"
					+ " and co.docstatus <> 'IN' AND DOCACTION ='CO'");
			query.setParameter("aduserid",ad.getAd_user_id());
			List<COrder> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> buscarTodosPorFacturar(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("createdby", ad.getAd_user_id()))
					.add(Restrictions.eq("issotrx", "Y"))
					.add(Restrictions.eq("processed", "Y"));
			List<COrder> lista = crit.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> buscarTodosDetenidos(AdUser ad) {

		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("createdby", ad.getAd_user_id()))
					.add(Restrictions.eq("issotrx", "Y"))
					.add(Restrictions.eq("iscreditapproved", "N"))
					.add(Restrictions.eq("esdeudor", "Y"))
					.add(Restrictions.not((Restrictions.eq("docstatus", "VO"))))
//					.add(Restrictions.ne("updatedby", ad.getAd_user_id()))
					.add(Restrictions.eq("processed", "N"));
			List<COrder> lista = crit.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> buscarUnoDetenido(String adId, String coID) {

		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("createdby", Integer.parseInt(adId)))
					.add(Restrictions.eq("issotrx", "Y"))
					.add(Restrictions.eq("c_order_id", Integer.parseInt(coID)))
					.add(Restrictions.eq("iscreditapproved", "N"))
					.add(Restrictions.eq("esdeudor", "Y"))
//					.add(Restrictions.ne("updatedby", ad.getAd_user_id()))
					.add(Restrictions.eq("processed", "N"));
			List<COrder> lista = crit.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@Override
	public Long salvarOrden(COrder co) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Transaction tx = sesion.beginTransaction();
		try{
			sesion.saveOrUpdate(co);
			tx.commit();
			sesion.close();
		}catch(Exception e){
			System.out.println(e);
			sesion.close();
			sesion.saveOrUpdate(co);
			tx.commit();
		}
		return null;
	}
	
	@Override
	public Integer buscarMaximo() {
		Session sesion;
		Integer value = 0;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrder.class).setProjection(Projections.max("c_order_id"));
			@SuppressWarnings("rawtypes")
			List lista = crit.list();
			sesion.close();
			if (lista.get(0)!=null){
				value = Integer.parseInt(lista.get(0).toString()) + 1;
			}
			return value;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}

	@Override
	public COrder buscarUno(Integer id) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("c_order_id", id));
			@SuppressWarnings("unchecked")
			List<COrder> lista = crit.list();
			sesion.close();
			return lista.get(0);
		} catch (Exception e) {
			sesion.close();
			return null;
		}
	}
	
	public Long eliminarUno(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			COrder col = buscarUno(id);
			session.delete(col);
			tx.commit();
			session.close();
			return null;
		}catch (Exception e){
			System.out.println(e.toString());
			session.close();
			return null;
		}
	}
	@Override
	public List<COrder> buscarTodosEnSaram(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
//			Query query=sesion.createQuery(
//					"from COrder as co where (co.issotrx = 'Y' and co.processed='Y' and co.createdby = :aduserid and co.c_order_id in"
//					+ " (select b.c_order_id from CInvoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL')) or "
//					+ "(co.issotrx = 'Y' and co.processed='N' and iscreditapproved = 'Y' and co.createdby = :aduserid and co.c_order_id in"
//					+ " (select b.c_order_id from CInvoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL')) order by co.created desc");
			Query query=sesion.createQuery(
					"from COrder as co where co.createdby = :aduserid order by co.created desc");
			query.setMaxResults(30);
			query.setParameter("aduserid",ad.getAd_user_id());
			
//			List<COrder> prepedidos=(List<COrder>)sesion.createSQLQuery("select a.documentno as pedido, a.created,   a.c_order_id, a.grandtotal as totalpre \r\n" + 
//					"\r\n" + 
//					"from (\r\n" + 
//					"select a.c_order_id, a.documentno, a.created, b.c_orderline_id, a.grandtotal from c_order a \r\n" + 
//					"left join c_orderline b on a.c_order_id = b.c_order_id where a.c_doctype_id = 1000026 and a.createdby =1000023 and b.m_product_id >0\r\n" + 
//					"order by a.created desc  )a\r\n" + 
//					"\r\n" + 
//					"inner  join  (\r\n" + 
//					"\r\n" + 
//					"SELECT * from c_order as co\r\n" + 
//					"left join c_orderline col on co.c_order_id = col.c_order_id \r\n" + 
//					"\r\n" + 
//					" where (co.issotrx = 'Y' and co.processed='Y'   and co.c_order_id in\r\n" + 
//					"(select b.c_order_id from c_invoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL')) or \r\n" + 
//					"(co.issotrx = 'Y' and co.processed='N' and iscreditapproved = 'Y'  and co.c_order_id in\r\n" + 
//					"(select b.c_order_id from c_invoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL'))\r\n" + 
//					"order by co.created desc)b\r\n" + 
//					"\r\n" + 
//					"on a.c_orderline_id = b.c_orderlinep_id\r\n" + 
//					"group by a.documentno, a.created,   a.c_order_id , a.grandtotal\r\n" + 
//					"order by a.documentno desc limit 20").addEntity("c_order",Prepedido.class).list();
//			
//			for (int i = 0; i < prepedidos.size(); i++) {
//				System.out.println(prepedidos.get(i).getDocumentno());
//			}
//			
//			
			
			List<COrder> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@Override
	public List<COrder>buscarTodosEnSaramPrePedidos(AdUser ad)  {
		// TODO Auto-generated method stub
		List<COrder> pedidos=null;
		sqlHQL_X mp = new sqlHQL_X();
        String m = "SELECT distinct(co.c_order_id) from c_order as co \n"
                + "inner join c_orderline as cl on\n"
                + "co.c_order_id = cl.c_order_id\n"
                + "where cl.qtypendiente>0 and co.createdby = "+ad.getAd_user_id()+"";
		try {
			pedidos=mp.sqlIds(m);
		} catch (Exception e) {
			System.out.println(e);
		}
		return pedidos;
	}
	
//	@Override
//	public Long salvarMaximo() {
//		Session sesion;
//		try {
//			sesion = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
//		}
//		try {
//			Criteria crit = sesion.createCriteria(COrder.class).add(Restrictions.eq("createdby", ad.getAd_user_id()))
//					.add(Restrictions.eq("issotrx", "Y"));
//			List<COrder> lista = crit.list();
//			sesion.close();
//			// HibernateUtil.shutdown();
//			return lista;
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			sesion.close();
//			HibernateUtil.shutdown();
//			return null;
//		}
//	}
	
	@Override
	public List<COrder> buscarTodosConProforma(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Query query=sesion.createQuery(
					"from COrder as co where co.createdby = :aduserid and co.c_doctype_id=1000026 and co.isproforma='Y' order by co.created desc");
			query.setMaxResults(30);
			query.setParameter("aduserid",ad.getAd_user_id());
			
			List<COrder> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> buscarProformas(String adId, String coID) {
        System.out.println("USUARIO PARAMETRO:"+adId);
        System.out.println("ORDEN PARAMETRO:"+coID);

		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Query query=sesion.createQuery(
					"from COrder as co where  co.c_orderp_id = :corderpid order by co.created desc");
			query.setParameter("corderpid", Integer.parseInt(coID));
			query.setMaxResults(30);

			List<COrder> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	
	
	@Override
	public List<COrder> buscarProformasconfacturas(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {

			Query query=sesion.createQuery(
					"SELECT co from COrder co inner join co.C_invoice ci where co.createdby = :aduserid  order by co.created desc");
			query.setMaxResults(30);
			query.setParameter("aduserid",ad.getAd_user_id());
			
			
			List<COrder> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@Override
	public List<COrder> buscarConFacturas(AdUser ad) {
		// TODO Auto-generated method stub
		List<COrder> pedidos=null;
		sqlHQL_X mp = new sqlHQL_X();
		try {
			pedidos=mp.sqlIds("select a.c_order_id from (select a.c_order_id, a.documentno, a.created, b.c_orderline_id, a.grandtotal from c_order a \n"
	                + "left join c_orderline b on a.c_order_id = b.c_order_id where a.c_doctype_id = 1000026 and a.createdby = "+ad.getAd_user_id()+" and b.m_product_id >0\n"
	                + "order by a.created desc  )a\n"
	                + "inner  join  (\n"
	                + "SELECT * from c_order as co\n"
	                + "left join c_orderline col on co.c_order_id = col.c_order_id  where (co.issotrx = 'Y' and co.processed='Y'  and co.docstatus = 'CO'   and co.c_order_id in\n"
	                + "(select b.c_order_id from c_invoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL')) or \n"
	                + "(co.issotrx = 'Y' and co.processed='Y' and iscreditapproved = 'Y'  and co.docstatus = 'CO' and co.c_order_id in\n"
	                + "(select b.c_order_id from c_invoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL'))\n"
	                + "order by co.created desc)b on a.c_orderline_id = b.c_orderlinep_id\n"
	                + "group by a.documentno, b.documentno, a.created, b.grandtotal,  b.dateacct, b.totallines,  a.c_order_id , a.grandtotal\n"
	                + "order by a.created desc limit 20");
			
			System.out.println("TAMAÑO DE PEDIDOS:::"+pedidos.size());
		} catch (Exception e) {
			System.out.println(e);
		}
		return pedidos;
	}
	@Override
	public List<COrder> buscarTodosConProformaSinFactura(AdUser ad) {
		// TODO Auto-generated method stub
		List<COrder> pedidos=null;
		sqlHQL_X mp = new sqlHQL_X();
		try {
			pedidos=mp.sqlIds("select a.c_order_id from (select a.c_order_id, a.documentno, a.created, b.c_orderline_id, a.grandtotal from c_order a \n"
	                + "left join c_orderline b on a.c_order_id = b.c_order_id where a.c_doctype_id = 1000026 and a.createdby = "+ad.getAd_user_id()+" and b.m_product_id >0\n"
	                + "order by a.created desc  )a\n"
	                + "inner  join  (\n"
	                + "SELECT * from c_order as co\n"
	                + "left join c_orderline col on co.c_order_id = col.c_order_id  where (co.issotrx = 'Y' and co.processed='Y' and co.docstatus='IN'   and co.c_order_id not in\n"
	                + "(select b.c_order_id from c_invoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL')) or \n"
	                + "(co.issotrx = 'Y' and co.processed='N' and co.docstatus='IN'   and co.c_order_id not in\n"
	                + "(select b.c_order_id from c_invoice b where co.c_order_id = b.c_order_id and b.docstatus='CO' and docaction='CL'))\n"
	                + "order by co.created desc)b on a.c_orderline_id = b.c_orderlinep_id\n"
	                + "group by a.documentno, b.documentno, a.created, b.grandtotal,  b.dateacct, b.totallines,  a.c_order_id , a.grandtotal\n"
	                + "order by a.created desc limit 20");
			
			System.out.println("TAMAÑO DE PEDIDOS:::"+pedidos.size());
		} catch (Exception e) {
			System.out.println(e);
		}
		return pedidos;
	}
}
