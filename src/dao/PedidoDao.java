package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Pedido;
import modelo.Carrito;


public class PedidoDao {

    private static PedidoDao instance;
    private static Session session;
    private Transaction tx;

    protected PedidoDao() {}

    public static PedidoDao getInstance() {

        if(instance == null) {

            instance = new PedidoDao();
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


    public void agregar(Pedido obj) {

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

    public void actualizar(Pedido objeto) throws HibernateException {

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

    public void eliminar(Pedido objeto) throws HibernateException {

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

    public Pedido traer(long idPedido) {

        Pedido objeto = null;

		try {
            iniciarOperacion();
            String hql = "from Pedido p inner join fetch p.carrito c " +
                                        "inner join fetch p.producto pr "+
                                        "inner join fetch p.local l "+
                                        "inner join fetch p.cliente cl "+
                                        "inner join fetch p.vendedorOriginal vo "+
                                        "inner join fetch p.vendedorAuxiliar va "+
                                        "where p.idPedido="+idPedido;
			objeto = (Pedido) session.createQuery(hql).uniqueResult();
		} finally {
			session.close();
		}

		return objeto;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> traer(Carrito carrito) throws HibernateException{ //traer pedidos x carrito

        List<Pedido> list = null;

        try {

            iniciarOperacion();
            String hql = "from Pedido p inner join fetch p.carrito c where c.idCarrito="+carrito.getIdCarrito();
            list = session.createQuery(hql).list();

        } finally {

            session.close();
        }

        return list;
    }

}