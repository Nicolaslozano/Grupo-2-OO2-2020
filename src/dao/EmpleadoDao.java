package dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Empleado;

public class EmpleadoDao {

	private static EmpleadoDao instance;
	private static Session session;
	private Transaction tx;

	public static EmpleadoDao getInstance() {

        if(instance == null) {

            instance = new EmpleadoDao();
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

	public int agregar(Empleado objeto) {
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

	public void actualizar(Empleado objeto) throws HibernateException {
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

	public void eliminar(Empleado objeto) throws HibernateException {
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

	public Empleado traer(long idPersona) throws HibernateException {
		Empleado objeto = null;

		try {
			iniciarOperacion();
			objeto = (Empleado) session.get(Empleado.class, idPersona);
			Hibernate.initialize(objeto.getLocal());

		} finally {
			session.close();
		}

		return objeto;
	}

	public Empleado traer(int dni) throws HibernateException {
		Empleado objeto = null;

		try {
			iniciarOperacion();
			objeto = (Empleado) session.createQuery("from Empleado e where e.dni="+dni).uniqueResult();
			Hibernate.initialize(objeto.getLocal());

		} finally {
			session.close();
		}

		return objeto;
	}
}