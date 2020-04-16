package modelo;

public class Pedido {

	private int idPedido;
	private Producto producto;
	private int cantidad;
	private Local local;
	private Cliente cliente;
	private Empleado vendedorOriginal;
	private Empleado vendedorAuxiliar;
	private float subtotal;
	private boolean aceptado;

	public Pedido(int idPedido, Producto producto, int cantidad, Local local, Cliente cliente,
			Empleado vendedorOriginal, Empleado vendedorAuxiliar, boolean aceptado) {
		super();
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.local = local;
		this.cliente = cliente;
		this.vendedorOriginal = vendedorOriginal;
		this.vendedorAuxiliar = vendedorAuxiliar;
		this.aceptado = aceptado;
		this.subtotal = CalcularSubtotal();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
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

	public Empleado getVendedorOriginal() {
		return vendedorOriginal;
	}

	public void setVendedorOriginal(Empleado vendedorOriginal) {
		this.vendedorOriginal = vendedorOriginal;
	}

	public Empleado getVendedorAuxiliar() {
		return vendedorAuxiliar;
	}

	public void setVendedorAuxiliar(Empleado vendedorAuxiliar) {
		this.vendedorAuxiliar = vendedorAuxiliar;
	}

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
				+ ", vendedorOriginal=" + vendedorOriginal + ", vendedorAuxiliar=" + vendedorAuxiliar + "]\n\n";
	}
	
	public float CalcularSubtotal() {
		return producto.getPrecio()*cantidad;
	}
	
	

}