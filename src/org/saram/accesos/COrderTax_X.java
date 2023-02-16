package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.COrderTax;

public class COrderTax_X implements COrderTax_I {
	public static Session sesion;

	@Override
	public List<COrderTax> buscarTodos(Integer cOrderID) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(COrderTax.class).add(Restrictions.eq("c_order_id", cOrderID));
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<COrderTax> lista = crit.list();
			sesion.close();
			// HibernateUtil.shutdown();
			return lista;
		} catch (Exception e) {
			System.out.println(e);
			// sesion.close();
			// HibernateUtil.shutdown();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public COrderTax buscarUno(Integer cOrderID, Integer cTaxID) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria crit = sesion.createCriteria(COrderTax.class).add(Restrictions.eq("c_order_id", cOrderID))
				.add(Restrictions.eq("c_tax_id", cTaxID));
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<COrderTax> lista = crit.list();
			sesion.close();
			// HibernateUtil.shutdown();
			return lista.get(0);
		} catch (Exception e) {
			System.out.println(e);
			// sesion.close();
			// HibernateUtil.shutdown();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Long salvar(COrderTax col) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Transaction tx = sesion.beginTransaction();
		try {
			sesion.saveOrUpdate(col);
			tx.commit();
			sesion.close();
		} catch (Exception e) {
			System.out.println(e);
			sesion.close();
			sesion.saveOrUpdate(col);
			tx.commit();
		}
		return null;
	}

	@Override
	public Long eliminarTodos(Integer cOrderID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<COrderTax> cot = buscarTodos(cOrderID);
			// for (COrderTax cot )
			if (!cot.isEmpty() || cot != null) {
				session.delete(cot);
			}
			tx.commit();
			session.close();
			return null;
		} catch (Exception e) {
			System.out.println(e.toString());
			session.close();
			return null;
		}
	}

	@Override
	public Long eliminarUno(Integer cOrderID, Integer cTaxID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			COrderTax cot = buscarUno(cOrderID, cTaxID);
			session.delete(cot);
			tx.commit();
			session.close();
			return null;
		} catch (Exception e) {
			System.out.println(e.toString());
			session.close();
			return null;
		}
	}

}
