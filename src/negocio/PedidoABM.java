package negocio;

import dao.PedidoDao;
import modelo.Pedido;
import modelo.Producto;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Local;

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

    public void agregar(Producto producto, int cantidad, Local local, Cliente cliente,
    Empleado vendedorOriginal, Empleado vendedorAuxiliar, boolean aceptado) {

		Pedido p = new Pedido(producto, cantidad, local, cliente, vendedorOriginal, vendedorAuxiliar, aceptado);
		dao.agregar(p);
	}

	public Pedido traerPedido(long idPedido) {

		return dao.traer(idPedido);
	}

	public void eliminar(long idPedido) {

        Pedido p=traerPedido(idPedido);

		dao.eliminar(p);
	}

}