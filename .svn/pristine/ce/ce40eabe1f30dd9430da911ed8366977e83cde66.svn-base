package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.MPromocionSalesRep;

public class MPromocionSalesRep_X implements MPromocionSalesRep_I {

	@Override
	public boolean buscarAdUserCBPartner(Integer ad, Integer pr) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			boolean retorno = true;
			Criteria crit = sesion.createCriteria(MPromocionSalesRep.class).add(Restrictions.eq("m_promocion_id", pr));
			@SuppressWarnings("unchecked")
			List<MPromocionSalesRep>  lista = crit.list();
			if(lista.size()>0) {
			Query query=sesion.createSQLQuery(
					"SELECT created FROM M_PromocionSalesRep WHERE C_BPartner_ID = (SELECT C_BPartner_ID as cb FROM Ad_User WHERE Ad_User_ID = "+ ad +")"
					+ " AND M_Promocion_ID = " + pr);
			List<?> q = query.list();
			if (q.size()>0) retorno = true; else retorno= false;
			} else retorno = true;
			sesion.close();
			return retorno;
		} catch (Exception e) {
			System.out.println(e.toString());
			sesion.close();
			return false;
		}
	}
}
