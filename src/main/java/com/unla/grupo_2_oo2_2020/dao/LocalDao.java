package com.unla.grupo_2_oo2_2020.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.unla.grupo_2_oo2_2020.modelo.Local;

public class LocalDao {

	private static LocalDao instance;
	private static Session session;
	private Transaction tx;

	public static LocalDao getInstance() {

        if(instance == null) {

            instance = new LocalDao();
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

	public int agregar(Local objeto) {
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

	public void actualizar(Local objeto) throws HibernateException {
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

	public void eliminar(Local objeto) throws HibernateException {
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

	public Local traer(long idLocal) throws HibernateException {
		Local objeto = null;

		try {

			iniciarOperacion();
			objeto = (Local) session.get(Local.class, idLocal);
			Hibernate.initialize(objeto.getListaEmpleados());
			Hibernate.initialize(objeto.getListaFacturas());
			Hibernate.initialize(objeto.getListaSolicitudesStock());
			Hibernate.initialize(objeto.getStock());

		} finally {
			session.close();
		}

		return objeto;
	}

}
