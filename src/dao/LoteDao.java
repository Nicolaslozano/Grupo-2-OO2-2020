package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Lote;
import modelo.Stock;


public class LoteDao {

    private static LoteDao instance;
    private static Session session;
    private Transaction tx;

    protected LoteDao() {}

    public static LoteDao getInstance() {

        if(instance == null) {

            instance = new LoteDao();
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

    public void agregar(List<Lote> objetos) {

        try {

            iniciarOperacion();

            //System.out.println(objetos);

            for(Lote p : objetos) {

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

    public void agregar(Lote obj) {

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

    public void actualizar(Lote objeto) throws HibernateException {

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

    public void eliminar(Lote objeto) throws HibernateException {

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

    public void eliminar(List<Lote> objetos) throws HibernateException {

        try {

            iniciarOperacion();

            for(Lote p : objetos) {

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


    public Lote traerLote(long idLote) throws HibernateException {

        Lote obj = null;

        try {

            iniciarOperacion();
            obj = (Lote) session.get(Lote.class, idLote);
            Hibernate.initialize(obj.getProducto());
            Hibernate.initialize(obj.getStock());
        } finally {

            session.close();
        }

        return obj;
    }

    @SuppressWarnings("unchecked")
    public List<Lote> traer(Stock stock) throws HibernateException{ //traer lotes x stock

        List<Lote> list = null;

        try {

            iniciarOperacion();
            String hql = "from Lote l inner join fetch l.stock s where s.idStock="+stock.getIdStock();
            list = session.createQuery(hql).list();

        } finally {

            session.close();
        }

        return list;
    }

}