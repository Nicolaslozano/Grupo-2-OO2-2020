package test;

import java.time.LocalDate;

import modelo.Local;
import negocio.ProductoABM;
import negocio.StockABM;
import negocio.ClienteABM;
import negocio.LocalABM;

public class test {

	public static void main(String[] args) {

		//ProductoABM productoABM = ProductoABM.getInstance();

		//productoABM.agregar("saba", "lero", 10, LocalDate.now());

		//productoABM.eliminar(2);

		//ClienteABM clienteABM = ClienteABM.getInstance();

		//clienteABM.agregar(123, "cuaren", "tena", LocalDate.now(), "email");

		//System.out.println(clienteABM.traerCliente(123));

		LocalABM localABM = LocalABM.getInstance();

		StockABM stockABM = StockABM.getInstance();

		//localABM.agregar("direccion", 555, 555, 555, null);

		Local local =localABM.traerLocal(1);

		System.out.println(local.getTelefono());

		stockABM.agregar(12, local);

	}
}
