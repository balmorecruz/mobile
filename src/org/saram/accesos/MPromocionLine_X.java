package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.MPromocionLine;

public class MPromocionLine_X implements MPromocionLine_I {
	@SuppressWarnings("unused")
	private static Session sesion;

	@SuppressWarnings("unchecked")
	@Override
	public MPromocionLine buscarTodos(Integer mpromoid) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MPromocionLine.class).add(Restrictions.eq("m_promocion_id", mpromoid))
					.add(Restrictions.eq("isactive", "Y"));
			List<MPromocionLine> lista = crit.list();
			sesion.close();
			return lista.size() > 0 ? lista.get(0) : null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MPromocionLine> buscarLineasPromocion(Integer mpromoid) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MPromocionLine.class).add(Restrictions.eq("m_promocion_id", mpromoid))
					.add(Restrictions.eq("isactive", "Y"));
			List<MPromocionLine> lista = crit.list();
			sesion.close();
			return lista.size() > 0 ? lista : null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
}
