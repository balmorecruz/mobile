package org.saram.accesos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.COrder;

public class sqlHQL_X implements sqlHQL_I {
	public static Session sesion;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> buscarUno(String sql) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		// Query query = sesion.createQuery("from Stock where stockCode = :code ");
		Query query = sesion.createSQLQuery(sql);
		try {
			List<Object> list = query.list();
			sesion.close();
			return list;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public int sql(String sql) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		// Query query = sesion.createQuery("from Stock where stockCode = :code ");
		Query query = sesion.createSQLQuery(sql);
		try {
			query.executeUpdate();
			sesion.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public int sqlInsert(String sql) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		// Query query = sesion.createQuery("from Stock where stockCode = :code ");
		Query query = sesion.createSQLQuery(sql);
		try {
			query.executeUpdate();
			sesion.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<COrder> sqlIds(String sql) {
		List<Object> rows = null;
		List<COrder> pedidos = new ArrayList<COrder>();
		COrder_X cox = new COrder_X();

		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		// Query query = sesion.createQuery("from Stock where stockCode = :code ");
		Query query = sesion.createSQLQuery(sql);
		try {

			rows = query.list();
			// for(Integer row : rows){
			//// COrder c= new COrder();
			//// c=cox.buscarUno(Integer.parseInt(row[0].toString()));
			//// pedidos.add(c);
			// }
			for (int i = 0; i < rows.size(); i++) {
				java.math.BigDecimal bigDecimalValue = (java.math.BigDecimal) rows.get(i);
				int x = bigDecimalValue.intValue();
				COrder c= new COrder();
				c=cox.buscarUno(x);
				pedidos.add(c);
			}
			sesion.close();
			return pedidos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
