package test;

import java.time.LocalDate;
import negocio.ProductoABM;
import negocio.ClienteABM;

public class test {

	public static void main(String[] args) {

		//ProductoABM productoABM = ProductoABM.getInstance();

		//productoABM.agregar("saba", "lero", 10, LocalDate.now());

		//productoABM.eliminar(2);

		ClienteABM clienteABM = ClienteABM.getInstance();

		//clienteABM.agregar(123, "cuaren", "tena", LocalDate.now(), "email");

		System.out.println(clienteABM.traerCliente(123));
	}
}
