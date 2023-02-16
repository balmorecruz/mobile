package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.AdSequence;

public class AdSequence_X implements AdSequence_I {
//	private Session sesion;
//
//	public AdSequence buscarUno(Integer id) {
//		try {
//			sesion = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
//		}
//		// Crear un documento especial para pedidos online
//		Criteria crit = sesion.createCriteria(AdSequence.class).add(Restrictions.eq("ad_sequence_id", id));
//		try {
//			@SuppressWarnings("unchecked")
//			List<AdSequence> lista = crit.list();
//			sesion.close();
//			return lista.get(0);
//		} catch (Exception e) {
//
//			return null;
//		}
//	}
//
//	@Override
//	public Long salvar(AdSequence ads) {
//		Session sesion;
//		try {
//			sesion = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
//		}
//		Transaction tx = sesion.beginTransaction();
//		try {
//			sesion.saveOrUpdate(ads);
//			tx.commit();
//			sesion.close();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			sesion.close();
//			sesion.saveOrUpdate(ads);
//			tx.commit();
//		}
//		return null;
//	}
//
//	@Override
//	public Long salvarProximaSequencia(int sad) {
//		try {
////			sqlHQL_X sql = new sqlHQL_X();
////			int secuenciaNo = sad;
////			String updateSQLNextSys = ("UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + 1, CurrentNext = CurrentNext + 1  WHERE AD_Sequence_ID = "
////					+ secuenciaNo);
////			sql.sql(updateSQLNextSys);
////			return (long) 1;
//			AdSequence ads = new AdSequence();
//			AdSequence_X adsx = new AdSequence_X();
//			ads = adsx.buscarUno(sad);
//			ads.setCurrentnext(ads.getCurrentnext() + 1);
//			ads.setCurrentnextsys(ads.getCurrentnextsys() + 1);
//			salvar(ads);
//			return (long) 1;
//		} catch (Exception e) {
//			return (long) 0;
//		}
//	}
//	
//	
//	public int obtenerYSalvarProximaSequencia(int sad) {
//		try {
//			int seq = 0;
//			AdSequence ads = new AdSequence();
//			AdSequence_X adsx = new AdSequence_X();
//			ads = adsx.buscarUno(sad);
//			seq =ads.getCurrentnext() + 1; 
//			ads.setCurrentnext(ads.getCurrentnext() + 1);
//			ads.setCurrentnextsys(ads.getCurrentnextsys() + 1);
//			salvar(ads);
//			return seq;
//		} catch (Exception e) {
//			return -1;
//		}
//	}
//	
//	
//	
//	@Override
//	public int obtenerProximaSequencia(int sad) {
//		try {
//			sqlHQL_X sql = new sqlHQL_X();
//			List secuencia;
//			int secuenciaNo = sad;
//			String selectSQL = ("update ad_sequence set CurrentNext = CurrentNext+1 where ad_sequence_id = "+  secuenciaNo +" returning CurrentNext - 1 ");
//			secuencia = sql.buscarUno(selectSQL);
//			if (secuencia!=null){
//				return Integer.parseInt(secuencia.get(0).toString());
//			} else 
//				return 0;
//		} catch (Exception e) {
//				return 0;
//		}
//	}

}
