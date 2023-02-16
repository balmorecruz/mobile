package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.AdUser;
import org.saram.modelo.MPriceList;

public class MPriceList_X implements MPriceList_I{
	private static Session sesion;
	@Override
	public List<MPriceList> buscarTodos(AdUser ad) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MPriceList buscarUno(Integer MPriceListID) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(MPriceList.class).add(Restrictions.eq("m_pricelist_id", MPriceListID))
				.add(Restrictions.eqOrIsNull("isactive", "Y"));
		List<MPriceList> lista = crit.list();
		try {
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
