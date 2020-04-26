package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Cliente;

public class ClienteDao {

	private static ClienteDao instance;
	private static Session session;
	private Transaction tx;

	public static ClienteDao getInstance() {

        if(instance == null) {

            instance = new ClienteDao();
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

	public int agregar(Cliente objeto) {
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

	public void actualizar(Cliente objeto) throws HibernateException {
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

	public void eliminar(Cliente objeto) throws HibernateException {
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

	public Cliente traer(long idPersona) throws HibernateException {
		Cliente objeto = null;

		try {

			iniciarOperacion();
			objeto = (Cliente) session.get(Cliente.class, idPersona);
		} finally {
			session.close();
		}

		return objeto;
	}

	public Cliente traer(int dni) throws HibernateException {
		Cliente objeto = null;

		try {
			iniciarOperacion();
			objeto = (Cliente) session.createQuery("from Cliente c where c.dni="+dni).uniqueResult();
		} finally {
			session.close();
		}

		return objeto;
	}

}
