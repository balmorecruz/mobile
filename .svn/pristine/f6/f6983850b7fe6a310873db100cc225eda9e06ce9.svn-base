package org.saram.accesos;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.CBPartnerLocation;

public class CBPartnerLocation_X implements CBPartnerLocation_I {
	private static Session sesion;

	@Override
	public Long salvar(CBPartnerLocation cbp) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CBPartnerLocation> buscarTodos(Integer ad) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			SessionImpl sessionImpl = (SessionImpl) sesion;
			Connection conn = sessionImpl.connection();
			HibernateUtil.getSessionFactory().getCurrentSession().reconnect(conn);
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(CBPartnerLocation.class).add(Restrictions.eq("c_bpartner_id", ad)).add(Restrictions.eq("isactive", "Y"));
		try {
			List<CBPartnerLocation> lista = crit.list();
			sesion.close();
			// HibernateUtil.shutdown();
			return lista;
		} catch (Exception e) {
			System.out.println(e);
			sesion.close();
			// HibernateUtil.shutdown();
			System.out.println(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public CBPartnerLocation buscarUno(Integer cbl) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			SessionImpl sessionImpl = (SessionImpl) sesion;
			Connection conn = sessionImpl.connection();
			HibernateUtil.getSessionFactory().getCurrentSession().reconnect(conn);
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(CBPartnerLocation.class)
				.add(Restrictions.eq("c_bpartner_location_id", cbl));
		try {
			List<CBPartnerLocation> lista = crit.list();
			sesion.close();
			// HibernateUtil.shutdown();
			return lista.get(0);
		} catch (Exception e) {
			System.out.println(e);
			sesion.close();
			// HibernateUtil.shutdown();
			System.out.println(e);
			return null;
		}
	}
}
