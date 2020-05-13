package com.unla.grupo_2_oo2_2020.models;

public class PedidoModel {

	private long idPedido;
	private long idProducto;
	private int cantidad;
	private long idLocal;
	private long idCliente;
	private long idVendedorOriginal; 
	private long idVendedorAuxiliar;
	private float subtotal;
	private boolean aceptado;

	public PedidoModel() {
	}

	public PedidoModel(long idPedido, long idProducto, int cantidad, long idLocal, long idCliente,
			long idVendedorOriginal, long idVendedorAuxiliar, boolean aceptado) {
		super();
		
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idLocal = idLocal;
		this.idCliente = idCliente;
		this.idVendedorAuxiliar = idVendedorAuxiliar;
		this.idVendedorOriginal = idVendedorOriginal;
		this.aceptado = aceptado;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getidProducto() {

		return this.idProducto;
	}
	protected void setidProducto(long idProducto) {
		this.idProducto = idProducto;
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

	public long getidLocal() {
		return idLocal;
	}

	public void setidLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public long getidCliente() {
		return idCliente;
	}
	
	public long getidVendedorOriginal() {

		return this.idVendedorOriginal;
	}

	public void setidVendedorOriginal(long idVendedorOriginal) {

		this.idVendedorOriginal = idVendedorOriginal;
	}

	public long getidVendedorAuxiliar() {

		return this.idVendedorAuxiliar;
	}

	public void setidVendedorAuxiliar(long idVendedorAuxiliar) {

		this.idVendedorAuxiliar = idVendedorAuxiliar;
	}

	public void setidCliente(long idCliente) {
		this.idCliente = idCliente;
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
		return "Pedido [idProducto=" + idProducto + ", cantidad=" + cantidad + ", idLocal=" + idLocal + ", idCliente=" + idCliente
				+ ", empleados: "+idVendedorOriginal+idVendedorAuxiliar+"]\n\n";
	}

}