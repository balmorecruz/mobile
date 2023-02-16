package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.AdUser;
import org.saram.modelo.CInvoice;

public class CInvoice_X implements CInvoice_I {
	@Override
	public List<CInvoice> buscarFacturado(AdUser ad) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(CInvoice.class).add(Restrictions.eq("createdby", ad.getAd_user_id()))
					.add(Restrictions.eq("docstatus", "CO"))
					.add(Restrictions.eq("docaction", "CL"));
			@SuppressWarnings("unchecked")
			List<CInvoice> lista = crit.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
	
	@Override
	public CInvoice buscarFacturadoPorOrden(Integer co) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(CInvoice.class).add(Restrictions.eq("c_order_id", co))
					.add(Restrictions.eq("docstatus", "CO"))
					.add(Restrictions.eq("docaction", "CL"));
			@SuppressWarnings("unchecked")
			List<CInvoice> lista = crit.list();
			sesion.close();
			return lista.get(0);
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
}
