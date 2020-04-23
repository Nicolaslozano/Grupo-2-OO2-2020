package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Empleado;

public class EmpleadoDao {

	private static Session session;
	private Transaction tx;

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

	public void Eliminar(Empleado objeto) throws HibernateException {
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

	public Empleado traer(long idEmpleado) throws HibernateException {
		Empleado objeto = null;

		try {
			iniciarOperacion();
			objeto = (Empleado) session.get(Empleado.class, idEmpleado);
		} finally {
			session.close();
		}

		return objeto;
	}

	public Empleado traer(int dni) throws HibernateException {
		Empleado objeto = null;

		try {
			iniciarOperacion();
			objeto = (Empleado) session.createQuery("from Empleado c order by c.apellido asc,c.nombre asc").list();
		} finally {
			session.close();
		}

		return objeto;
	}
}