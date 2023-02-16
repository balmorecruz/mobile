package org.saram.accesos;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.CBPartnerLocation;
import org.saram.modelo.CLocation;

public class CLocation_X implements CLocation_ID {
	private static Session sesion;
	@Override
	public CLocation buscarUno(Integer cl) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			SessionImpl sessionImpl = (SessionImpl) sesion;
			Connection conn = sessionImpl.connection();
			HibernateUtil.getSessionFactory().getCurrentSession().reconnect(conn);
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(CLocation.class)
				.add(Restrictions.eq("c_location_id", cl));
		try {
			List<CLocation> lista = crit.list();
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
