package org.saram.accesos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.saram.controladores.HibernateUtil;
import org.saram.modelo.MProduct;

public class MProduct_X implements MProduct_I {
	private static Session sesion;

	@Override
	public List<MProduct> buscarTodos(MProduct mp) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		if (mp.getName() != "") {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(name) like upper(:name) and value like 'PT%'");
			query.setParameter("name", "%" + mp.getName() + "%");
		} else 
		if (mp.getValue() != "") {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and value like upper(:value) and name value 'PT%'");
			query.setParameter("value", "%" + mp.getValue() + "%");
		} else 
		{
			query = sesion.createQuery(
					"from MProduct where isactive = 'Y' and upper(value) like upper(:value) " + "and upper(name) like upper(:name)");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Long salvar(MProduct mp) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MProduct buscarUno(Integer id) {
		Session sesion;
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MProduct.class).add(Restrictions.eq("m_product_id", id));
			List<MProduct> lista = crit.list();
			sesion.close();
			return lista.get(0);
		} catch (Exception e) {
			sesion.close();
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorCliente(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and value like 'PT%' and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid)" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 
		if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid)" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 
		{
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid)");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorClientePecuario(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct mp where mp.isactive = 'Y' and upper(mp.name) like upper(:name) and (mp.value like 'PT-AC%' or mp.value like 'PT-AE%' or mp.value like 'PT-AP%' or mp.value like 'PT-RE%' "
					+ "or mp.value like 'PT-CE%' or mp.value like 'PT-EQ%' or mp.value like 'PT-GA%') and mp.m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 
		if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and (value like 'PT-AC%' or value like 'PT-AE%' or value like 'PT-AP%' or value like 'PT-RE%' "
					+ "or value like 'PT-CE%' or value like 'PT-EQ%' or value like 'PT-GA%') and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) and (value like 'PT-AC%' or value like 'PT-AE%' or value like 'PT-AP%' or value like 'PT-RE%' "
					+ "or value like 'PT-CE%' or value like 'PT-EQ%' or value like 'PT-GA%') "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorClienteExtranjero(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct mp where mp.isactive = 'Y' and upper(mp.name) like upper(:name) and (mp.value like 'PT-AC%' or mp.value like 'PT-AE%' or mp.value like 'PT-AP%' "
					+ "or mp.value like 'PT-CE%' or mp.value like 'PT-EQ%' or mp.value like 'PT-GA%' or value like 'PT-MA-%' or value like 'PT-RE%' or value like 'PT-RO%') "
					+ "and mp.m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 
		if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and (value like 'PT-AC%' or value like 'PT-AE%' or value like 'PT-AP%' "
					+ "or value like 'PT-CE%' or value like 'PT-EQ%' or value like 'PT-GA%'  or value like 'PT-MA-%' or value like 'PT-RE%' or value like 'PT-RO%') "
					+ "and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) and (value like 'PT-AC%' or value like 'PT-AE%' or value like 'PT-AP%' "
					+ "or value like 'PT-CE%' or value like 'PT-EQ%' or value like 'PT-GA%' or value like 'PT-MA-%' or value like 'PT-RE%' or value like 'PT-RO%') "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public List<MProduct> buscarTodosPorClienteMascota(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and (value like 'PT-MA-%' or value like 'PT-RE%' or value like 'PT-RO%') and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 	if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and (value like 'PT-MA-%' or value like 'PT-RE%' or value like 'PT-RO%') and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) and (value like 'PT-MA-%' or value like 'PT-RE%' or value like 'PT-RO%') "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorClienteTrotemor(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and (value like 'PT-EQ-%') and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 	if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and (value like 'PT-EQ-%') and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) and (value like 'PT-EQ-%') "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public List<MProduct> buscarTodosPorClienteMateriaPrima(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
//		if (mp.getName() != "") {
//			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and (value like 'MP-%') and m_locator_id = 1000021 and m_product_id in ("
//					+ " select MProductPrice.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice MProductPrice"
//					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
//					+ " AND MProductPrice.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid)" ;
//			query = sesion.createQuery(sql);
//			query.setParameter("name", "%" + mp.getName() + "%");
//			query.setParameter("cbpartnerid", cb);
//		} else 	if (mp.getValue() != "") {
//			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and value like 'MP-%' and m_locator_id = 1000021 and m_product_id in ("
//					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
//					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
//					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid)" ;
//			query = sesion.createQuery(sql);
//			query.setParameter("value", "%" + mp.getValue() + "%");
//			query.setParameter("cbpartnerid", cb);
//		} else {
//			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) and value like 'MP-%' and m_locator_id = 1000021 "
//					+ "and upper(name) like upper(:name) and m_product_id in ("
//					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
//					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
//					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid)");
//			query.setParameter("value", "%" + mp.getValue() + "%");
//			query.setParameter("name", "%" + mp.getName() + "%");
//			query.setParameter("cbpartnerid", cb);
//		}
		
		
		if (mp.getName() != "") {
			sql = "from MProduct mp where mp.isactive = 'Y' and upper(mp.name) like upper(:name) and (mp.value like 'MP-%') and mp.m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else 
		if (mp.getValue() != "") {
			sql = "from MProduct mp where mp.isactive = 'Y' and mp.value like upper(:value) and (mp.value like 'MP-%') and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct mp where mp.isactive = 'Y' and upper(mp.value) like upper(:value) and (mp.value like 'MP-%%') "
					+ "and upper(mp.name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			System.out.println(lista.size());
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorClienteHuevo(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and value like 'AVI-HUEVO%' and m_locator_id = 1000076 and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and value like 'AVI-HUEVO%' and m_locator_id = 1000076 and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value) and value like 'AVI-HUEVO%' and m_locator_id = 1000076 "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorClienteInsumo(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and (value like 'IN-CONSUM%' or value like 'RE-%') and m_locator_id = 1000019 and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and (value like 'IN-CONSUM%' or value like 'RE-%') and m_locator_id = 1000019 and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value)  and (value like 'IN-CONSUM%' or value like 'RE-%') and m_locator_id = 1000019 "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid  AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<MProduct> buscarTodosPorClienteProductoProceso(MProduct mp, Integer cb) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Query query = sesion.createQuery("from MProduct");
		String sql="";
		if (mp.getName() != "") {
			sql = "from MProduct where isactive = 'Y' and upper(name) like upper(:name) and (value like 'MP-%' or value like 'PI-%') and m_locator_id = 1000022 and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		} else
		if (mp.getValue() != "") {
			sql = "from MProduct where isactive = 'Y' and value like upper(:value) and (value like 'MP-%' or value like 'PI-%') and m_locator_id = 1000022 and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')" ;
			query = sesion.createQuery(sql);
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("cbpartnerid", cb);
		} else  {
			query = sesion.createQuery("from MProduct where isactive = 'Y' and upper(value) like upper(:value)  and (value like 'MP-%' or value like 'PI-%') and m_locator_id = 1000022 "
					+ "and upper(name) like upper(:name) and m_product_id in ("
					+ " select d.m_product_id from MPriceList a, CBPartner b, MPriceListVersion c, MProductPrice d"
					+ " WHERE a.m_pricelist_id = b.m_pricelist_id AND b.m_pricelist_id = c.m_pricelist_id  AND d.isactive = 'Y' "
					+ " AND d.m_pricelist_version_id = c.m_pricelist_version_id AND c.isactive = 'Y' AND b.c_bpartner_id = :cbpartnerid AND a.isactive='Y')");
			query.setParameter("value", "%" + mp.getValue() + "%");
			query.setParameter("name", "%" + mp.getName() + "%");
			query.setParameter("cbpartnerid", cb);
		}
		try {
			// sesion.flush();
			@SuppressWarnings("unchecked")
			List<MProduct> lista = query.list();
			sesion.close();
			return lista;
		} catch (Exception e) {
			sesion.close();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean buscarProductoEsMascota(Integer cb) {
		Session sesion;
		Disjunction or = Restrictions.disjunction();
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MProduct.class).add(Restrictions.eq("m_product_id", cb)).add(Restrictions.like("value", "PT-MA-%"));
			or.add(Restrictions.eq("m_product_id", cb)).add(Restrictions.like("value", "PT-RE-%"));
			or.add(Restrictions.eq("m_product_id", cb)).add(Restrictions.like("value", "PT-RO-%"));
			List<MProduct> lista = crit.list();
			sesion.close();
			if (lista.size()>0) return true; else return false;
		} catch (Exception e) {
			sesion.close();
			return false;
		}
	}

	@Override
	public boolean buscarProductoMP(Integer cb) {
		Session sesion;
		Disjunction or = Restrictions.disjunction();
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		try {
			Criteria crit = sesion.createCriteria(MProduct.class).add(Restrictions.eq("m_product_id", cb)).add(Restrictions.like("value", "MP-MISCEL-014"));
			List<MProduct> lista = crit.list();
			sesion.close();
			if (lista.size()>0) return true; else return false;
		} catch (Exception e) {
			sesion.close();
			return false;
		}
	}
	
	

}