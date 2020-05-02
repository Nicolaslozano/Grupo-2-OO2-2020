package com.unla.grupo_2_oo2_2020.negocio;

import java.util.List;

import com.unla.grupo_2_oo2_2020.dao.PedidoDao;
import com.unla.grupo_2_oo2_2020.modelo.*;

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