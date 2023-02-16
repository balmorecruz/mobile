package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.AdUser;
import org.saram.modelo.CBPartner;

public class CBPartner_X implements CBPartner_I {
	private static Session sesion;

	@SuppressWarnings("unchecked")
	@Override
	public CBPartner buscarUno(Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
		Criteria crit = sesion.createCriteria(CBPartner.class).add(Restrictions.eq("c_bpartner_id", cb));
		List<CBPartner> lista = crit.list();
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
	@SuppressWarnings("unchecked")
	@Override
	public CBPartner buscarUnoPorAd(Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
		AdUser_X adx = new AdUser_X();
		AdUser ad = new AdUser();
		ad = adx.buscarUno(cb);
		Criteria crit = sesion.createCriteria(CBPartner.class).add(Restrictions.eq("c_bpartner_id", ad.getC_bpartner_id()));
		List<CBPartner> lista = crit.list();
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
	@Override
	public List<CBPartner> buscarTodos(CBPartner cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query=sesion.createQuery(
				"from CBPartner");
		if (cb.getName() != "") {
			query =          sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and upper(name) like upper(:name) and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)");
			query.setParameter("name", "%" + cb.getName() + "%");
		}
		if (cb.getValue() != "") {
			query = sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and value like :value");
			query.setParameter("value", "%" + cb.getValue() + "%");
		}
		if (cb.getTaxid() != "") {
			query = sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and taxid like :taxid");
			query.setParameter("taxid", "%" + cb.getTaxid() + "%");
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<CBPartner> lista = query.list();
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

	@Override
	public List<CBPartner> buscarPorVendedor(CBPartner cb, String adId) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query=sesion.createQuery(
				"from CBPartner");
		
		if (cb.getName() != "") {
			query =          sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and " + cb.getName() + " and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)"
					+ " and salesrep_id =" + adId + " or  isactive = 'Y' and iscustomer='Y' and " + cb.getName() + " and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)"
					+ " and c_bpartner_id = 1003971");
//			query.setParameter("name", "%" + cb.getName() + "%");
		}
		
		if (cb.getValue() != "") {
			query = sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and value like :value");
			query.setParameter("value", "%" + cb.getValue() + "%");
		}
		if (cb.getTaxid() != "") {
			query = sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and taxid like :taxid");
			query.setParameter("taxid", "%" + cb.getTaxid() + "%");
		}
		if (adId.equals("1000601")||adId.equals("1000602")||adId.equals("1001747")){
			query =          sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and " + cb.getName() + " and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)"
					+ " and salesrep_id =" + adId + " or  isactive = 'Y' and iscustomer='Y' and " + cb.getName() + " and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)"
					+ " and c_bpartner_id = 1003971 or  isactive = 'Y' "
					+ "and iscustomer='Y' and " + cb.getName() + 
					" AND c_bpartner_id in (1008795,1009177)");
		}
		if (adId.equals("1000013")||adId.equals("1000049")){
			query =          sesion.createQuery(
					"from CBPartner where isactive = 'Y' and iscustomer='Y' and " + cb.getName() + " and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)"
					+ " and salesrep_id =" + adId + " or  isactive = 'Y' and iscustomer='Y' and " + cb.getName() + " and c_bpartner_id in (SELECT c_bpartner_id FROM CBPartnerLocation)"
					+ " and c_bpartner_id = 1003971 or  isactive = 'Y' "
					+ "and iscustomer='Y' and " + cb.getName() + 
					" AND c_bpartner_id in (1004712)");
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<CBPartner> lista = query.list();
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

	@Override
	public CBPartner buscaVendedor(CBPartner cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(CBPartner.class).add(Restrictions.eq("c_bpartner_id", cb));
		List<CBPartner> lista = crit.list();
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
