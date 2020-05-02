package test;

import java.time.LocalDate;

import modelo.*;
import negocio.*;
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

		//carritoABM.agregar(LocalDate.now());

		//localABM.agregar("direccion", 555, 555, 555, null);

		//empleadoABM.agregar("EMPLE", "ADO", LocalDate.now(), 45678, null, false, localABM.traerLocal(1));

		Producto p = productoABM.traerProducto(2);
		Pedido pp = pedidoABM.traerPedido(1);

		//System.out.println(p.getIdProducto());
		//pedidoABM.agregar(p, 300, localABM.traerLocal(1), clienteABM.traerCliente(123),
		 //carritoABM.traerCarrito(1), empleadoABM.traerEmpleado(45678), null, false);

		 System.out.println(pp.getVendedorOriginal().getApellido());
		//Local local =localABM.traerLocal(1);

		//System.out.println(local.getTelefono());

		//stockABM.agregar(12, local);


	}
}
