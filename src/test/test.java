package test;

import java.time.LocalDate;
import modelo.*;

public class test {

	public static void main(String[] args) {

		// CREACI�N DEL SISTEMA.
		Sistema gestor = new Sistema();

		// AGREGAR LOCALES
		try {
			gestor.altaLocal("Carlos Pelegrini 847", -34.824818f, -58.390417f, 42035654);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.altaLocal("Av. Pres. Hip�lito Yrigoyen 12485", -34.800631f, -58.408077f, 425874697);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		try {
			gestor.altaLocal("Peatonal Laprida", -34.760412f, -58.400737f, 4258369);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println("LOCALES:");
		System.out.println(gestor.traerLocal(1));
		System.out.println(gestor.traerLocal(2));
		System.out.println(gestor.traerLocal(3));

		try {
			System.out.println("Distancia entre local 1 y local 2: "
					+ gestor.calcularDistancia(gestor.traerLocal(1), gestor.traerLocal(2)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-------------------------------------------------------------");

		System.out.println();

		// AGREGAR EMPLEADOS A LOS LOCALES
		try {
			gestor.traerLocal(1).altaEmpleado("Ignacio", "Cazcarra", LocalDate.of(1999, 1, 1), 42056897, "8hs a 18hs",
					false);// VENDEDOR
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.traerLocal(1).altaEmpleado("Franco", "Aguirre", LocalDate.of(2000, 1, 1), 43024568, "8hs a 18hs",
					true);// VENDEDOR
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.traerLocal(2).altaEmpleado("Alejandra", "Vranic", LocalDate.of(1970, 5, 22), 20154853, "8hs a 16hs",
					false);// GERENTE
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.traerLocal(2).altaEmpleado("Gustavo", "Siciliano", LocalDate.of(1980, 12, 26), 20154852,
					"8hs a 18hs", true);// VENDEDOR
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//IMPRIME EMPLEADOS

		System.out.println("EMPLEADOS:");
		System.out.println(gestor.traerLocal(1).traerEmpleado(42056897));
		System.out.println(gestor.traerLocal(1).traerEmpleado(43024568));

		System.out.println(gestor.traerLocal(2).traerEmpleado(20154853));
		System.out.println(gestor.traerLocal(2).traerEmpleado(20154852));

		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// AGREGAR PRODUCTOS
		try {
			gestor.altaProducto("Nike AirForce 1", "Resistentes a la lluvia", 5600, LocalDate.of(2015, 10, 1));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.altaProducto("Adidas Superstar", "Color Blanco", 6000, LocalDate.of(2019, 10, 2));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.altaProducto("Nike Air Max 97", "Zapatilla de mujer", 9999, LocalDate.of(2019, 10, 3));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		try {
			gestor.altaProducto("Nike More Uptempo", "Lo �ltimo en moda", 14999, LocalDate.of(2019, 10, 3));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//IMPRIME PRODUCTOS
		
		System.out.println("PRODUCTOS:");
		System.out.println(gestor.traerProducto(1));
		System.out.println(gestor.traerProducto(2));
		System.out.println(gestor.traerProducto(3));
		System.out.println(gestor.traerProducto(4));

		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// AGREGAR LOTES/STOCK A LOS LOCALES
		gestor.traerLocal(1).getStock().agregarLote(50, LocalDate.of(2019, 10, 15), gestor.traerProducto(1));
		try {
			gestor.traerLocal(1).getStock().altaStock(1);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		gestor.traerLocal(1).getStock().agregarLote(25, LocalDate.of(2019, 10, 16), gestor.traerProducto(2));

		try {
			gestor.traerLocal(1).getStock().altaStock(2);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		gestor.traerLocal(2).getStock().agregarLote(50, LocalDate.of(2019, 10, 15), gestor.traerProducto(1));

		try {
			gestor.traerLocal(2).getStock().altaStock(1);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		gestor.traerLocal(2).getStock().agregarLote(25, LocalDate.of(2019, 10, 16), gestor.traerProducto(3));

		try {
			gestor.traerLocal(2).getStock().altaStock(2);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		gestor.traerLocal(2).getStock().agregarLote(25, LocalDate.of(2019, 10, 16), gestor.traerProducto(4));
		try {
			gestor.traerLocal(2).getStock().altaStock(3);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		gestor.traerLocal(3).getStock().agregarLote(25, LocalDate.of(2019, 10, 16), gestor.traerProducto(1));
		try {
			gestor.traerLocal(2).getStock().altaStock(1);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


		System.out.println("LOTES:");
		System.out.println(gestor.traerLocal(1).getStock().traerLote(1));
		System.out.println(gestor.traerLocal(1).getStock().traerLote(2));
		System.out.println(gestor.traerLocal(2).getStock().traerLote(1));
		System.out.println(gestor.traerLocal(2).getStock().traerLote(2));
		System.out.println(gestor.traerLocal(2).getStock().traerLote(3));
		System.out.println(gestor.traerLocal(3).getStock().traerLote(1));
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// AGREGAR CLIENTES

		try {
			gestor.altaCliente("Franco", "Borsani", LocalDate.of(1999, 6, 24), 42043432, "FrancoB@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			gestor.altaCliente("Matias", "Bernardi", LocalDate.of(1999, 8, 5), 42587963, "MatiasBer@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//IMPRIME CLIENTES

		System.out.println("CLIENTES:");
		System.out.println(gestor.traerCliente(42043432));
		System.out.println(gestor.traerCliente(42587963));
		System.out.println("-------------------------------------------------------------");

		System.out.println();

		// DAR DE ALTA UN PEDIDO (CON STOCK PROPIO)
		gestor.altaCarrito(LocalDate.of(2019, 11, 12));
		try {
			gestor.traerCarrito(1).altaPedido(gestor.traerProducto(1), 40, gestor.traerLocal(1),
					gestor.traerCliente(42043432), gestor.traerLocal(1).traerEmpleado(43024568), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			gestor.traerCarrito(1).altaPedido(gestor.traerProducto(2), 20, gestor.traerLocal(1),
					gestor.traerCliente(42043432), gestor.traerLocal(1).traerEmpleado(43024568), null);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		

		// GENERAR FACTURA
		System.out.println("FACTURA VENTA 1:");
		System.out.println(gestor.traerLocal(1).generarFactura(gestor.traerCarrito(1)));
		System.out.println("-------------------------------------------------------------");
		System.out.println();
		
		// ALTA PEDIDO (CON STOCK DE OTRO LOCAL)
		gestor.altaCarrito(LocalDate.of(2019, 11, 13));
		
		try {
			gestor.traerCarrito(2).altaPedido(gestor.traerProducto(1), 15, gestor.traerLocal(1),
					gestor.traerCliente(42587963), gestor.traerLocal(1).traerEmpleado(43024568), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("LOCALES CON STOCK(MOSTRANDO DISTANCIA CON EL LOCAL SOLICITANTE):");
		System.out.println(gestor.localesConStockConDistancia(gestor.traerLocal(1), gestor.traerProducto(1), 15));
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		//SOLICITAR STOCK A OTRO LOCAL
		System.out.println("IMPRIME SOLICITUD DE STOCK");
		gestor.traerLocal(1).altaSolicitudStock(gestor.traerCarrito(2).getFecha(), gestor.traerProducto(1), 15,
				gestor.traerLocal(1).traerEmpleado(43024568));

		SolicitudStock solicitud1 = gestor.traerLocal(1).traerSolicitud(1);
		System.out.println(solicitud1);

		System.out.println();

		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// FINALIZAR/ACTUALIZAR SOLICITUD
		try {
			gestor.traerLocal(2).actualizarSolicitudStock(gestor.traerCarrito(2), solicitud1, 20154852,
					gestor.traerCliente(42587963), gestor.traerCliente(42587963).aceptarEspera());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// IMPRIMIR SOLICITUD ACTUALIZADA
		
		System.out.println("IMPRIME SOLICITUD ACTUALIZADA(ACEPTADA)");

		System.out.println(solicitud1);
		System.out.println("-------------------------------------------------------------");

		System.out.println();

		System.out.println("FACTURA VENTA 2:");
		System.out.println(gestor.traerLocal(1).generarFactura(gestor.traerCarrito(2)));
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// SABER SI EXISTE PRODUCTO EN UN CARRITO
		try {
			System.out.println("Producto 3 existe en alg�n carrito?: " + gestor.saberSiExisteEnUnCarrito(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

		// TRATAR DE DAR DE BAJA UN PRODUCTO QUE EXISTE EN UN LOCAL
		try {
			gestor.bajaProducto(1);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// SABER SI CLIENTE TIENE CARRITO ASOCIADO
		System.out.println("Cliente 2 tiene carrito?: " + gestor.clienteTieneCarrito(42587963));
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// PRODUCTO M�S VENDIDO POR CANTIDAD
		System.out.println("Producto m�s vendido: " + gestor.productoMasVendidoPorCantidad(gestor.getListaProductos()));
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		// RANKING DE PRODUCTOS
		System.out.println("Ranking de productos: " + gestor.rankingProductos());
		System.out.println("-------------------------------------------------------------");
		System.out.println();

		System.out.println("SUELDOS:");
		System.out.println("Empleado " + gestor.traerLocal(1).traerEmpleado(43024568).getApellido() + ": "
				+ gestor.traerLocal(1).traerEmpleado(43024568).calcularSueldo(gestor.getListaCarritos(),
						gestor.traerLocal(1).traerEmpleado(43024568), 11));
		System.out.println("Empleado " + gestor.traerLocal(1).traerEmpleado(42056897).getApellido() + ": "
				+ gestor.traerLocal(1).traerEmpleado(42056897).calcularSueldo(gestor.getListaCarritos(),
						gestor.traerLocal(1).traerEmpleado(42056897), 11));
		System.out.println("Empleado " + gestor.traerLocal(2).traerEmpleado(20154853).getApellido() + ": "
				+ gestor.traerLocal(2).traerEmpleado(20154853).calcularSueldo(gestor.getListaCarritos(),
						gestor.traerLocal(2).traerEmpleado(20154853), 11));
		System.out.println("Empleado " + gestor.traerLocal(2).traerEmpleado(20154852).getApellido() + ": "
				+ gestor.traerLocal(2).traerEmpleado(20154852).calcularSueldo(gestor.getListaCarritos(),
						gestor.traerLocal(2).traerEmpleado(20154852), 11));
		System.out.println("-------------------------------------------------------------");
	
		
		System.out.println("PRODUCTOS VENDIDOS ENTRE FECHAS DEL LOCAL 2:");
		
		
		System.out.println(gestor.productosVendidosEntreFechas(gestor.traerLocal(2), LocalDate.of(2019, 10, 11), 
				LocalDate.of(2019, 12, 1)));
		
		
		
	}
}
