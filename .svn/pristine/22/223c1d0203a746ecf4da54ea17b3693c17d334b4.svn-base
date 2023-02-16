package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.AdUser;

public class AdUser_X implements AdUser_I {
	public static Session sesion;
	@Override
	public int buscarUno(AdUser ad) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(AdUser.class).add(Restrictions.eq("name", ad.getName()).ignoreCase()).add(Restrictions.eq("password", ad.getPassword()));
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<AdUser> lista = crit.list();
			sesion.close();
//			HibernateUtil.shutdown();
			if (lista.size()==0){
				try {
					sesion = HibernateUtil.getSessionFactory().openSession();
				} catch (Exception e) {
					sesion = HibernateUtil.getSessionFactory().getCurrentSession();
				}
				crit = sesion.createCriteria(AdUser.class).add(Restrictions.eq("value", ad.getName()).ignoreCase()).add(Restrictions.eq("password", ad.getPassword()));	
				@SuppressWarnings("unchecked")
				List<AdUser> lista2 = crit.list();
				sesion.close();
				lista = lista2;
			}
			return lista.get(0).getAd_user_id();
		} catch (Exception e) {
			System.out.println(e);
//			sesion.close();
//			HibernateUtil.shutdown();
			System.out.println(e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdUser> buscarTodos() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria crit = sesion.createCriteria(AdUser.class);
			@SuppressWarnings("rawtypes")
			List lista = crit.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
//			sesion.close();
			return null;
		}
	}

	@Override
	public AdUser buscarUno(int ad) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(AdUser.class).add(Restrictions.eq("id", ad));
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<AdUser> lista = crit.list();
			sesion.close();
//			HibernateUtil.shutdown();
			if (lista.size()>=0){
				return lista.get(0);
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
//			sesion.close();
//			HibernateUtil.shutdown();
			System.out.println(e);
			return null;
		}
	}

}
