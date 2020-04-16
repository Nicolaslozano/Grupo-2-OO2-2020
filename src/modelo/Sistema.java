package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Empleado> listaEmpleados;
	private List<Cliente> listaClientes;
	private List<Local> listaLocales;
	private List<Producto> listaProductos;
	private List<Carrito> listaCarritos;

	public Sistema() {
		this.listaEmpleados = new ArrayList<Empleado>();
		this.listaClientes = new ArrayList<Cliente>();
		this.listaLocales = new ArrayList<Local>();
		this.listaProductos = new ArrayList<Producto>();
		this.listaCarritos = new ArrayList<Carrito>();
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public List<Local> getListaLocales() {
		return listaLocales;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public List<Carrito> getListaCarritos() {
		return listaCarritos;
	}

	@Override
	public String toString() {
		return "Sistema [listaEmpleados=" + listaEmpleados + ", listaClientes=" + listaClientes + ", listaLocales="
				+ listaLocales + ", listaProductos=" + listaProductos + ", listaCarritos=" + listaCarritos + "]";
	}

	/**********************************************************************************************************************************************/
	public boolean altaProducto(String nombre, String descripcion, float precio, LocalDate fechaAlta) throws Exception {
		if (traerProducto(nombre) != null)
			throw new Exception("ERROR: El producto ya existe");
		int id = 1;
		if (!listaProductos.isEmpty()) {
			id = listaProductos.get(listaProductos.size() - 1).getIdProducto() + 1;
		}
		return listaProductos.add(new Producto(id, nombre, descripcion, precio, fechaAlta));
	}

	/**********************************************************************************************************************************************/
	public void modificarProducto(int idProducto, String nombre, String descripcion, float precio, LocalDate fechaAlta)
			throws Exception {
		if (traerProducto(idProducto) == null)
			throw new Exception("ERROR: No se encontro el producto");
		Producto p = traerProducto(idProducto);
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setFechaAlta(fechaAlta);
	}

	/**********************************************************************************************************************************************/
	public boolean saberSiExisteEnUnCarrito(int idProducto) throws Exception {
		int i = 0, j = 0;
		boolean encontrado = false;
		Producto p = traerProducto(idProducto);

		if (p == null)
			throw new Exception("ERROR: La ID no corresponde a ningun producto");

		while (i < listaCarritos.size() && !encontrado) {
			Carrito c = listaCarritos.get(i);

			while (j < c.getListaPedidos().size() && !encontrado) {
				if (c.getListaPedidos().get(j).getProducto().equals(p)) {
					encontrado = true;
				}
				j++;
			}
			j = 0; // reinicia el contador de pedidos
			i++;
		}

		return encontrado;
	}

	/**********************************************************************************************************************************************/

	/**********************************************************************************************************************************************/

	public boolean bajaProducto(int idProducto) throws Exception {
		if (traerProducto(idProducto) == null)
			throw new Exception("ERROR: La ID ingresada no pertenece a ningun producto");
		if (saberSiExisteEnUnCarrito(idProducto))
			throw new Exception("El producto existe en un carrito");
		if (!this.localesConStock(this.traerProducto(idProducto), 1).isEmpty())
			throw new Exception("El producto existe en un Local");
		return this.getListaProductos().remove(traerProducto(idProducto));
	}

	/**********************************************************************************************************************************************/
	public Producto traerProducto(int idProducto) {
		int i = 0;
		Producto productoEncontrado = null;
		while (i < listaProductos.size() && productoEncontrado == null) {
			if (listaProductos.get(i).getIdProducto() == idProducto) {
				productoEncontrado = listaProductos.get(i);
			}
			i++;
		}
		return productoEncontrado;
	}

	/**********************************************************************************************************************************************/
	public Producto traerProducto(String nombre) {
		int i = 0;
		Producto productoEncontrado = null;
		while (i < listaProductos.size() && productoEncontrado == null) {
			if (listaProductos.get(i).getNombre().equals(nombre)) {
				productoEncontrado = listaProductos.get(i);
			}
			i++;
		}
		return productoEncontrado;
	}

	/**********************************************************************************************************************************************/
	public boolean altaLocal(String direccion, float latitud, float longitud, long telefono) throws Exception {
		if (traerLocal(direccion) != null)
			throw new Exception("ERROR: El local ya existe");
		int id = 1;
		if (!listaLocales.isEmpty()) {
			id = listaLocales.get(listaLocales.size() - 1).getIdLocal() + 1;
		}
		return listaLocales.add(new Local(id, direccion, latitud, longitud, telefono));
	}

	/**********************************************************************************************************************************************/
	public void modificarLocal(int idLocal, String direccion, float latitud, float longitud, long telefono)
			throws Exception {
		if (traerLocal(idLocal) == null)
			throw new Exception("ERROR: No se encontro el local");
		Local l = traerLocal(idLocal);
		l.setDireccion(direccion);
		l.setLatitud(latitud);
		l.setLongitud(longitud);
		l.setTelefono(telefono);

	}

	/**********************************************************************************************************************************************/
	public boolean bajaLocal(int idLocal) throws Exception {
		if (traerLocal(idLocal) == null)
			throw new Exception("ERROR: La ID ingresada no pertenece a ningun local");
		return listaLocales.remove(traerLocal(idLocal));
	}

	/**********************************************************************************************************************************************/
	public Local traerLocal(int idLocal) {
		int i = 0;
		Local localEncontrado = null;
		while (i < listaLocales.size() && localEncontrado == null) {
			if (listaLocales.get(i).getIdLocal() == idLocal) {
				localEncontrado = listaLocales.get(i);
			}
			i++;
		}
		return localEncontrado;
	}

	/**********************************************************************************************************************************************/
	public Local traerLocal(String direccion) {
		int i = 0;
		Local localEncontrado = null;
		while (i < listaLocales.size() && localEncontrado == null) {
			if (listaLocales.get(i).getDireccion().equals(direccion)) {
				localEncontrado = listaLocales.get(i);
			}
			i++;
		}
		return localEncontrado;
	}

	/**********************************************************************************************************************************************/
	public boolean altaCliente(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String email)
			throws Exception {
		if (traerCliente(dni) != null)
			throw new Exception("ERROR: El cliente ya existe");
		return this.getListaClientes().add(new Cliente(nombre, apellido, fechaNacimiento, dni, email));

	}

	/**********************************************************************************************************************************************/
	public void modificarLocal(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String email)
			throws Exception {
		if (traerCliente(dni) == null)
			throw new Exception("ERROR: No se encontro al cliente");
		Cliente c = traerCliente(dni);
		c.setApellido(apellido);
		c.setDni(dni);
		c.setEmail(email);
		c.setFechaNacimiento(fechaNacimiento);
		c.setNombre(nombre);
	}

	/**********************************************************************************************************************************************/
	public boolean clienteTieneCarrito(long dni) {
		int i = 0;
		boolean band = false;
		int j = 0;
		while (i < listaCarritos.size() && !band) {
			j = 0;
			Carrito c = listaCarritos.get(i);
			while (j < c.getListaPedidos().size() && !band) {
				if (c.getListaPedidos().get(j).getCliente().getDni() == dni) {
					band = true;
				}
				j++;
			}
			i++;
		}

		return band;
	}

	/**********************************************************************************************************************************************/
	public boolean bajaCliente(long dni) throws Exception {
		if (traerCliente(dni) == null || clienteTieneCarrito(dni))
			throw new Exception(
					"ERROR: El DNI ingresado no pertenece a ningun cliente o Cliente tiene un carrito asociado");
		return listaClientes.remove(traerCliente(dni));
	}

	/**********************************************************************************************************************************************/
	public Cliente traerCliente(long dni) {
		int i = 0;
		Cliente clienteEncontrado = null;
		while (i < listaClientes.size() && clienteEncontrado == null) {
			if (listaClientes.get(i).getDni() == dni) {
				clienteEncontrado = listaClientes.get(i);
			}
			i++;
		}
		return clienteEncontrado;
	}

	/**********************************************************************************************************************************************/
	public boolean altaCarrito(LocalDate fecha) {
		int id = 1;
		if (!listaCarritos.isEmpty()) {
			id = listaCarritos.get(listaCarritos.size() - 1).getIdCarrito() + 1;
		}
		return listaCarritos.add(new Carrito(id, fecha));
	}

	/**********************************************************************************************************************************************/
	public void modificarCarrito(int idCarrito, LocalDate fecha) throws Exception {
		if (traerCarrito(idCarrito) == null)
			throw new Exception("ERROR: No se encontro el carrito");
		Carrito c = traerCarrito(idCarrito);
		c.setFecha(fecha);
		c.setTotal(c.calcularTotal());
	}

	/**********************************************************************************************************************************************/
	public boolean bajaCarrito(int idCarrito) throws Exception {
		if (traerCarrito(idCarrito) == null)
			throw new Exception("ERROR: La ID ingresada no pertenece a ningun carrito");
		return listaCarritos.remove(traerCarrito(idCarrito));
	}

	/**********************************************************************************************************************************************/
	public Carrito traerCarrito(int idCarrito) {
		int i = 0;
		Carrito carritoEncontrado = null;
		while (i < listaCarritos.size() && carritoEncontrado == null) {
			if (listaCarritos.get(i).getIdCarrito() == idCarrito) {
				carritoEncontrado = listaCarritos.get(i);
			}
			i++;
		}
		return carritoEncontrado;
	}

	/**********************************************************************************************************************************************/
	public double calcularDistancia(Local local1, Local local2) throws Exception {
		double rad = Math.PI / 180; // Para convertir a Radianes
		double dlat = local1.getLatitud() - local2.getLatitud(); // Diferencia de latitudes
		double dlong = local1.getLongitud() - local2.getLongitud(); // Diferencia de longitudes

		if (local1.equals(local2))
			throw new Exception("La distancia es nula debido a que es esta comparando el mismo local");
		double R = 6372.795477598;// Radio de la tierra
		double a = Math.pow(Math.sin(rad * dlat / 2), 2) + Math.cos(rad * local1.getLatitud())
				* Math.cos(rad * local2.getLatitud()) * Math.pow(rad * Math.sin(dlong / 2), 2);
		double distancia = 2 * R * Math.asin(Math.sqrt(a));

		return distancia;
	}

	/**********************************************************************************************************************************************/

	public Producto productoMasVendidoPorCantidad(List<Producto> listaProd) {
		int maxCantidadProducto = 0, maxActual = -1;
		Producto productoMasVendido = null;

		for (Producto producto : listaProd) {
			maxCantidadProducto = 0;
			for (Carrito carrito : listaCarritos) {
				for (Pedido pedido : carrito.getListaPedidos()) {
					if (pedido.getProducto().equals(producto)) {
						maxCantidadProducto += pedido.getCantidad();
					}
				}
			}
			if (maxCantidadProducto > maxActual) {
				maxActual = maxCantidadProducto;
				productoMasVendido = producto;
			}
		}

		return productoMasVendido;
	}

	/**********************************************************************************************************************************************/
	public List<Producto> rankingProductos() {

		List<Producto> rankingProductos = new ArrayList<Producto>();
		List<Producto> listaAux = new ArrayList<Producto>();

		for (Producto p : listaProductos) {
			Producto p1 = p;
			listaAux.add(p1);
		}

		Producto productoRanking = null;

		while (!listaAux.isEmpty()) {

			productoRanking = productoMasVendidoPorCantidad(listaAux);
			rankingProductos.add(productoRanking);
			listaAux.remove(productoRanking);

		}
		return rankingProductos;
	}

	/**********************************************************************************************************************************************/
	public boolean existeProducto(List<Producto> productos, Producto producto) {
		int i = 0;
		boolean encontrado = false;
		while (i < productos.size() && !encontrado) {
			if (productos.get(i).equals(producto)) {
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}

	/**********************************************************************************************************************************************/
	public List<Producto> productosVendidosEntreFechas(Local local, LocalDate comienzo, LocalDate fin) {

		List<Producto> productos = new ArrayList<Producto>();
		for (Carrito c : listaCarritos) {
			if (c.getFecha().isAfter(comienzo) && c.getFecha().isBefore(fin)) {

				for (int i = 0; i < c.getListaPedidos().size(); i++) {
					if (c.getListaPedidos().get(i).getLocal().equals(local)) {

						if (productos.isEmpty()) {
							productos.add(c.getListaPedidos().get(i).getProducto());
						} else if (!existeProducto(productos, c.getListaPedidos().get(i).getProducto())) {
							productos.add(c.getListaPedidos().get(i).getProducto());
						}
					}
				}
			}
		}
		return productos;
	}

	/**********************************************************************************************************************************************/

	public List<Local> localesConStock(Producto producto, int cantidad) {
		List<Local> locales = new ArrayList<Local>();
		for (Local l : listaLocales) {
			if (l.getStock().stockValido(producto, cantidad)) {
				locales.add(l);
			}

		}
		return locales;
	}

	/**********************************************************************************************************************************************/

	public List<String> localesConStockConDistancia(Local local, Producto producto, int cantidad) {
		List<String> listaLocales = new ArrayList<String>();
		String localesDistancia = "";
		for (Local l : this.localesConStock(producto, cantidad)) {
			try {
				localesDistancia = Integer.toString(l.getIdLocal()) + " " + "Direccion: " + l.getDireccion() + " "
						+ "Telefono:" + l.getTelefono() + " Distancia =" + this.calcularDistancia(local, l) + "\n";
				listaLocales.add(localesDistancia);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}

		return listaLocales;
	}

	/**********************************************************************************************************************************************/

}
