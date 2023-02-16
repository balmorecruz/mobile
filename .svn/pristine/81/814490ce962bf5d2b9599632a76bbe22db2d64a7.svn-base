package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.MWareHouse;

public class MWareHouse_X implements MWareHouse_I {
	private static Session sesion;
	@SuppressWarnings("unchecked")
	@Override
	public List<MWareHouse> buscarTodos() {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(MWareHouse.class).add(Restrictions.eqOrIsNull("ad_client_id", 1000000));
		try {
			List<MWareHouse> lista = crit.list();
			sesion.close();
//			HibernateUtil.shutdown();
			return lista;
		} catch (Exception e) {
			System.out.println(e);
			sesion.close();
			// HibernateUtil.shutdown();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Long salvar(MWareHouse mw) {
		// TODO Auto-generated method stub
		return null;
	}
}
