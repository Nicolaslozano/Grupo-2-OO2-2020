package com.unla.grupo_2_oo2_2020.dao;

import com.unla.grupo_2_oo2_2020.modelo.Factura;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class FacturaDao {

	private static FacturaDao instance;
	private static Session session;
	private Transaction tx;

	public static FacturaDao getInstance() {

        if(instance == null) {

            instance = new FacturaDao();
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

	public int agregar(Factura objeto) {
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

	public void actualizar(Factura objeto) throws HibernateException {
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

	public void eliminar(Factura objeto) throws HibernateException {
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


	public Factura traer(long idFactura) throws HibernateException {
		Factura objeto = null;

		try {
			iniciarOperacion();
			objeto = (Factura) session.get(Factura.class, idFactura);
		} finally {
			session.close();
		}

		return objeto;
	}
	
	
}//end
