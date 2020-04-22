package modelo;

import java.util.HashSet;
import java.util.Set;

public class Pedido {

	private int idPedido;
	private Producto producto;
	private int cantidad;
	private Local local;
	private Cliente cliente;
	private Set<Empleado> empleados = new HashSet<>();
	private float subtotal;
	private boolean aceptado;

	public Pedido() {
	}

	public Pedido(int idPedido, Producto producto, int cantidad, Local local, Cliente cliente,
			Empleado vendedorOriginal, Empleado vendedorAuxiliar, boolean aceptado) {
		super();
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.local = local;
		this.cliente = cliente;
		this.empleados.add(vendedorOriginal);
		if (vendedorAuxiliar != null) this.empleados.add(vendedorAuxiliar); //VALIDACIONES EN SET, CAMBIAR LUEGO
		this.aceptado = aceptado;
	}

	public Producto getProducto() {
		return producto;
	}

	protected void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Empleado> getEmpleados() {

		return this.empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {

		this.empleados = empleados;
	}

	//ADD EMPLEADO EN ABM

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public float getTotal() {
		return subtotal;
	}

	public void setTotal(float subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "Pedido [producto=" + producto + ", cantidad=" + cantidad + ", local=" + local + ", cliente=" + cliente
				+ ", empleados: "+empleados+"]\n\n";
	}

}