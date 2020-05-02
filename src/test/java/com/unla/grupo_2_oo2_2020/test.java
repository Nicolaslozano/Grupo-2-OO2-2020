package com.unla.grupo_2_oo2_2020;

import java.time.LocalDate;

import com.unla.grupo_2_oo2_2020.modelo.*;
import com.unla.grupo_2_oo2_2020.negocio.*;
public class test {

	public static void main(String[] args) {

		ProductoABM productoABM = ProductoABM.getInstance();

		//productoABM.agregar("SALSA", "SALSITA", 10, LocalDate.now());

		//productoABM.agregar("SALSA", "211234123", 44, LocalDate.now());

		//productoABM.eliminar(2);

		ClienteABM clienteABM = ClienteABM.getInstance();

		//clienteABM.agregar(123, "cuaren", "tena", LocalDate.now(), "email");

		//System.out.println(clienteABM.traerCliente(123));

		LocalABM localABM = LocalABM.getInstance();
		StockABM stockABM = StockABM.getInstance();
		CarritoABM carritoABM = CarritoABM.getInstance();
		PedidoABM pedidoABM = PedidoABM.getInstance();
		EmpleadoABM empleadoABM = EmpleadoABM.getInstance();
		SolicitudStockABM SolicitudStock=SolicitudStockABM.getInstance();

		Carrito carrito = carritoABM.traerCarrito(1);

		for(Pedido p : carrito.getListaPedidos()) {

			System.out.println(productoABM.traerProducto(p.getProducto().getIdProducto()));
		}

		//localABM.agregar("direccion", 1555,1555,1555, null);

		//empleadoABM.agregar("EMPLE", "ADO", LocalDate.now(), 45678, null, false, localABM.traerLocal(1));

		//Producto p = productoABM.traerProducto(2);
		// Pedido pp = pedidoABM.traerPedido(1);

		//System.out.println(p.getIdProducto());
		//pedidoABM.agregar(p, 300, localABM.traerLocal(1), clienteABM.traerCliente(123),
		 //carritoABM.traerCarrito(1), empleadoABM.traerEmpleado(45678), null, false);

		// System.out.println(pp.getVendedorOriginal().getApellido());
		//Local local =localABM.traerLocal(1);

		//System.out.println(local.getListaEmpleados());

		//stockABM.agregar(12, local);
		// localABM.eliminar(1);
		/*try {
			System.out.println(SolicitudStock.calcularDistancia(localABM.traerLocal(2), localABM.traerLocal(3)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
		 

	}
	
}
