package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Carrito {

	private int idCarrito;
	private List<Pedido> listaPedidos;
	private LocalDate fecha;
	private float total;

	public Carrito(int idCarrito, LocalDate fecha) {
		super();
		this.idCarrito = idCarrito;
		this.fecha = fecha;
		this.listaPedidos = new ArrayList<Pedido>();
		this.total = this.calcularTotal();

	}

	public int getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "\nCarrito [idCarrito=" + idCarrito + ", listaPedidos=" + listaPedidos + ", fecha=" + fecha + ", total="
				+ total + "]";
	}

	/**********************************************************************************************************************************************/
	public boolean altaPedido(Producto producto, int cantidad, Local local, Cliente cliente, Empleado vendedorOriginal,
			Empleado vendedorAuxiliar) throws Exception{
		
		int id = 1;
		if (!this.getListaPedidos().isEmpty()) {
			id = this.getListaPedidos().get(this.getListaPedidos().size() - 1).getIdPedido() + 1;
		}
		
		if(!local.getStock().stockValido(producto, cantidad)) throw new Exception ("ERROR: El stock del local no alcanza para realizar el pedido");
		
		local.getStock().consumoStock(producto, cantidad);
		return this.getListaPedidos().add(new Pedido(id, producto, cantidad, local, cliente, vendedorOriginal, vendedorAuxiliar, false));
		
	}

	/**********************************************************************************************************************************************/
	public void modificarPedido(int idPedido, Producto producto, int cantidad, Local local, Cliente cliente,
			Empleado vendedorOriginal, Empleado vendedorAuxiliar) throws Exception {
		if (traerPedido(idPedido) == null)
			throw new Exception("ERROR: No se encontro el pedido");
		Pedido p = traerPedido(idPedido);
		p.setVendedorOriginal(vendedorOriginal);
		p.setVendedorAuxiliar(vendedorAuxiliar);
		p.setTotal(p.CalcularSubtotal());
		p.setProducto(producto);
		p.setCantidad(cantidad);
		p.setCliente(cliente);
	}

	/**********************************************************************************************************************************************/
	public boolean bajaPedido(int idPedido) throws Exception {
		if (traerPedido(idPedido) == null)
			throw new Exception("ERROR: La ID ingresada no pertenece a ningun pedido");
		return listaPedidos.remove(traerPedido(idPedido));
	}

	/**********************************************************************************************************************************************/
	public Pedido traerPedido(int idPedido) {
		int i = 0;
		Pedido pedidoEncontrado = null;
		while (i < listaPedidos.size() && pedidoEncontrado == null) {
			if (listaPedidos.get(i).getIdPedido() == idPedido) {
				pedidoEncontrado = listaPedidos.get(i);
			}
			i++;
		}
		return pedidoEncontrado;
	}

	/**********************************************************************************************************************************************/

	public float calcularTotal() {
		float total = 0;

		for (Pedido p : listaPedidos) {
			total += p.CalcularSubtotal();
		}

		return total;
	}

}