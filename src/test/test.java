package test;

import java.time.LocalDate;
import negocio.ProductoABM;

public class test {

	public static void main(String[] args) {

		ProductoABM productoABM = ProductoABM.getInstance();

		productoABM.agregar("nombre", "descripcion", 10, LocalDate.now());

	}
}
