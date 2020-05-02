package dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.SolicitudStock;

public class SolicitudStockDao {

	private static SolicitudStockDao instance;
	private static Session session;
	private Transaction tx;

	public static SolicitudStockDao getInstance() {

		if (instance == null) {

			instance = new SolicitudStockDao();
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

	public int agregar(SolicitudStock objeto) {
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

	public void actualizar(SolicitudStock objeto) throws HibernateException {
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

	public void eliminar(SolicitudStock objeto) throws HibernateException {
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

	public SolicitudStock traer(long idSolicitud) throws HibernateException {
		SolicitudStock objeto = null;

		try {

			iniciarOperacion();
			objeto = (SolicitudStock) session.get(SolicitudStock.class, idSolicitud);
			Hibernate.initialize(objeto.getVendedor());
			Hibernate.initialize(objeto.getColaborador());
			Hibernate.initialize(objeto.getProducto());
		} finally {
			session.close();
		}

		return objeto;
	}

}
