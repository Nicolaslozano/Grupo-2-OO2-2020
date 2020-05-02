package negocio;

import java.util.List;

import dao.PedidoDao;
import modelo.Pedido;
import modelo.Producto;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Local;
import modelo.Carrito;

public class PedidoABM {

    private static PedidoABM instance;
	private PedidoDao dao = PedidoDao.getInstance();

    protected PedidoABM() {}

    public static PedidoABM getInstance() {

        if(instance == null) {

            instance = new PedidoABM();
        }

        return instance;
    }

    public void agregar(Producto producto, int cantidad, Local local, Cliente cliente, Carrito carrito,
    Empleado vendedorOriginal, Empleado vendedorAuxiliar, boolean aceptado) {

		Pedido p = new Pedido(producto, cantidad, local, cliente, carrito, vendedorOriginal, vendedorAuxiliar, aceptado);
		dao.agregar(p);
	}

	public Pedido traerPedido(long idPedido) {

		return dao.traer(idPedido);
	}

	public void eliminar(long idPedido) {

        Pedido p=traerPedido(idPedido);

		dao.eliminar(p);
    }

    public List<Pedido> traerPedido(Carrito carrito) {

        return dao.traer(carrito);
    }

}