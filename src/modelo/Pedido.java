package modelo;

public class Pedido {

	private long idPedido;
	private Producto producto;
	private int cantidad;
	private Local local;
	private Cliente cliente;
	private Empleado vendedorOriginal;
	private Empleado vendedorAuxiliar;
	private float subtotal;
	private boolean aceptado;

	public Pedido() {
	}

	public Pedido(Producto producto, int cantidad, Local local, Cliente cliente,
			Empleado vendedorOriginal, Empleado vendedorAuxiliar, boolean aceptado) {
		super();

		this.producto = producto;
		this.cantidad = cantidad;
		this.local = local;
		this.cliente = cliente;
		this.vendedorAuxiliar = vendedorAuxiliar;
		this.vendedorOriginal = vendedorOriginal;
		this.aceptado = aceptado;
	}

	public long getIdPedido() {
		return idPedido;
	}

	protected void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public Producto getProducto() {

		return this.producto;
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

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Empleado getvendedorOriginal() {

		return this.vendedorOriginal;
	}

	public void setvendedorOriginal(Empleado vendedorOriginal) {

		this.vendedorOriginal = vendedorOriginal;
	}

	public Empleado getVendedorAuxiliar() {

		return this.vendedorAuxiliar;
	}

	public void setVendedorAuxiliar(Empleado vendedorAuxiliar) {

		this.vendedorAuxiliar = vendedorAuxiliar;
	}

	public float getSubtotal() {

		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {

		this.subtotal = subtotal;
	}

	//ADD EMPLEADO EN ABM

	@Override
	public String toString() {
		return "Pedido [producto=" + producto + ", cantidad=" + cantidad + ", local=" + local + ", cliente=" + cliente
				+ ", vendedorAuxiliar: "+vendedorAuxiliar + "VendedorOriginal:"+vendedorOriginal+"]\n\n";
	}

}