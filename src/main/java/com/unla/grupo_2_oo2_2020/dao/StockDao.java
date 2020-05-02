package com.unla.grupo_2_oo2_2020.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.unla.grupo_2_oo2_2020.modelo.Stock;

public class StockDao {

	private static StockDao instance;
	private static Session session;
	private Transaction tx;

	public static StockDao getInstance() {

		if (instance == null) {

			instance = new StockDao();
		}

		return instance;
	}

	private void iniciarOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso de datos", he);
	}

	public int agregar(Stock objeto) {
		int id = 0;

		try {
			iniciarOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;

	}

	public void actualizar(Stock objeto) throws HibernateException {
		try {
			iniciarOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(Stock objeto) throws HibernateException {
		try {
			iniciarOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public Stock traer(long idStock) throws HibernateException {
		Stock objeto = null;

		try {

			iniciarOperacion();
			objeto = (Stock) session.get(Stock.class, idStock);
			Hibernate.initialize(objeto.getLocal());
			Hibernate.initialize(objeto.getLotes());
		} finally {
			session.close();
		}

		return objeto;
	}

}