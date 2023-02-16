package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.MPromoClasificacion;

public class MPromoClasificacion_X implements MPromoclasificacion_I {
	@SuppressWarnings("unused")
	private static Session sesion;

	@SuppressWarnings("unchecked")
	@Override
	public List<MPromoClasificacion> buscarTodos(Integer mpromoid) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MPromoClasificacion.class).add(Restrictions.eq("m_promocion_id", mpromoid))
					.add(Restrictions.eq("isactive", "Y"));
			List<MPromoClasificacion> lista = crit.list();
			sesion.close();
			return lista.size() > 0 ? lista : null;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return null;
		}
	}
}
