package org.saram.accesos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.CCharge;

public class CCharge_X implements CCharge_I {
	private static Session sesion;

	@Override
	public List<CCharge> buscarTodos(CCharge ad) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CCharge> buscarUno(String name) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from CCharge");
		query = sesion.createQuery("from CCharge where description like 'FL-%' and upper(name) like upper(:name) ");
		query.setParameter("name", "%" + name + "%");
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<CCharge> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	public List<CCharge> buscarUnoByID(String id) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from CCharge");
		query = sesion.createQuery("from CCharge where c_charge_id = :id ");
		query.setParameter("id",Integer.parseInt(id));
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<CCharge> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}
	
	public CCharge buscarUnoByCity(Integer ccity) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from CCharge");
		query = sesion.createQuery("from CCharge where c_city_id = :ccity ");
		query.setParameter("ccity",ccity);
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<CCharge> lista = query.list();
			sesion.close();
			return lista.get(0);
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}
	
	public CCharge buscarUnoByCityTax(Integer city, String tax) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from CCharge");
		query = sesion.createQuery("from CCharge where c_city_id = :ccity and istaxincluded = :tax");
		query.setParameter("ccity",city);
		query.setParameter("tax", tax);
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<CCharge> lista = query.list();
			sesion.close();
			return lista.get(0);
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public Long salvar(CCharge cbp) {
		// TODO Auto-generated method stub
		return null;
	}

}
