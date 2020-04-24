package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Producto;

public class ProductoDao {

    private static ProductoDao instance;
    private static Session session;
    private Transaction tx;

    protected ProductoDao() {}

    public static ProductoDao getInstance() {

        if(instance == null) {

            instance = new ProductoDao();
        }

        return instance;
    }

    private void iniciarOperacion() throws HibernateException {

        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejarExcepcion(HibernateException he) throws HibernateException {

        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos",he);
    }

    public void agregar(List<Producto> objetos) {

        try {

            iniciarOperacion();

            //System.out.println(objetos);

            for(Producto p : objetos) {

                session.save(p);
            }

            tx.commit();

        } catch(HibernateException he) {

            manejarExcepcion(he);
            throw he;
        } finally {

            session.close();
        }

    }

    public void agregar(Producto obj) {

        try {

            iniciarOperacion();
            session.save(obj);
            tx.commit();
        } catch(HibernateException he) {

            manejarExcepcion(he);
            throw he;
        } finally {

            session.close();
        }

    }

    public void actualizar(Producto objeto) throws HibernateException {

        try {

            iniciarOperacion();
            session.update(objeto);
            tx.commit();
        } catch(HibernateException he) {

            manejarExcepcion(he);
            throw he;
        } finally {

            session.close();
        }

    }

    public void eliminar(Producto objeto) throws HibernateException {

        try {

            iniciarOperacion();
            session.delete(objeto);
            tx.commit();
        } catch(HibernateException he) {

            manejarExcepcion(he);
            throw he;
        } finally {

            session.close();
        }
    }

    public void eliminar(List<Producto> objetos) throws HibernateException {

        try {

            iniciarOperacion();

            for(Producto p : objetos) {

                session.delete(p);
            }

            tx.commit();
        } catch(HibernateException he) {

            manejarExcepcion(he);
            throw he;
        } finally {

            session.close();
        }
    }


    public Producto traerProducto(long idProducto) throws HibernateException {

        Producto obj = null;

        try {

            iniciarOperacion();
            obj = (Producto) session.get(Producto.class, idProducto);
        } finally {

            session.close();
        }

        return obj;
    }

}