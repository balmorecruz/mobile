package org.saram.accesos;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.COrder;
import org.saram.modelo.COrderLine;
import org.saram.modelo.MPromocion;
public class MPromocion_X implements MPromocion_I {
	@SuppressWarnings("unused")
	private static Session sesion;
	@SuppressWarnings("unchecked")
	@Override
	public MPromocion buscarTodos(Integer mp,Integer cbpartner) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MPromocion.class).add(Restrictions.eq("m_product_id", mp))
					.add(Restrictions.eq("isactive", "Y"))
					.add(Subqueries.propertyNotIn("c_bpartner_id", DetachedCriteria.forClass(MPromocion.class)
							.add(Restrictions.eq("c_bpartner_id", cbpartner))
							.setProjection(Property.forName("c_bpartner_id"))
							));
			List<MPromocion> lista = crit.list();
			sesion.close();
			return lista.size()>0?lista.get(0):null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@Override
	public COrderLine buscarPromocionNoIngresada(Integer mp, Integer COrderID) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(COrderLine.class).add(Restrictions.eq("c_order_id", COrderID))
					.add(Restrictions.eq("isactive", "Y"))
					.add(Restrictions.eq("m_product_id", mp))
					.add(Restrictions.eq("espromocion", "Y"));
			List<COrderLine> lista = crit.list();
			sesion.close();
			return lista.size()>0?lista.get(0):null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MPromocion buscarUno(Integer mp) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MPromocion.class).add(Restrictions.eq("m_product_id", mp))
					.add(Restrictions.eq("isactive", "Y"));
//					.add(Subqueries.propertyNotIn("c_bpartner_id", DetachedCriteria.forClass(MPromocion.class)
//							.add(Restrictions.eq("c_bpartner_id", cbpartner))
//							.setProjection(Property.forName("c_bpartner_id"))
//							));
			List<MPromocion> lista = crit.list();
			sesion.close();
			return lista.size()>0?lista.get(0):null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	@Override
	public MPromocion buscarPorCliente(Integer mp, Integer cb) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MPromocion.class).add(Restrictions.eq("m_product_id", mp))
					.add(Restrictions.eq("isactive", "Y")).add(Restrictions.eq("c_bpartner_id", cb));
//					.add(Subqueries.propertyNotIn("c_bpartner_id", DetachedCriteria.forClass(MPromocion.class)
//							.add(Restrictions.eq("c_bpartner_id", cbpartner))
//							.setProjection(Property.forName("c_bpartner_id"))
//							));
			List<MPromocion> lista = crit.list();
			sesion.close();
			return lista.size()>0?lista.get(0):null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	

}
