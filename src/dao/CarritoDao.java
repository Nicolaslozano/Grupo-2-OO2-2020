package dao;

import modelo.Carrito;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CarritoDao {

	private static CarritoDao instance;
	private static Session session;
	private Transaction tx;

	public static CarritoDao getInstance() {

		if (instance == null) {

			instance = new CarritoDao();
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

	public int agregar(Carrito objeto) {
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

	public void actualizar(Carrito objeto) throws HibernateException {
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

	public void eliminar(Carrito objeto) throws HibernateException {
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

	public Carrito traer(long idCarrito) throws HibernateException {
		Carrito objeto = null;

		try {
			iniciarOperacion();
			String hql = "from Carrito c where c.idCarrito="+idCarrito;
			objeto = (Carrito) session.createQuery(hql).uniqueResult();
			Hibernate.initialize(objeto.getListaPedidos());

		} finally {

			session.close();
		}

		return objeto;
	}

	@SuppressWarnings("unchecked")
	public List<Carrito> traer() throws HibernateException {
		List<Carrito> list = null;

		try {
			iniciarOperacion();
			String hql = "from Carrito";
			list = session.createQuery(hql).list();

			for(Carrito c : list) {

				Hibernate.initialize(c.getListaPedidos());
			}

		} finally {

			session.close();
		}

		return list;
	}

}// end
