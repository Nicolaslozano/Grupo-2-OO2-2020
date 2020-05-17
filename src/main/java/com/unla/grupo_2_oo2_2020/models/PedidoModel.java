package com.unla.grupo_2_oo2_2020.models;

public class PedidoModel {

	private long idProducto;
	private int cantidad;
	private long idLocal;
	private long idCliente;
	private long idVendedorOriginal;
	private long idVendedorAuxiliar;
	private boolean aceptado;

	public PedidoModel() {
	}

	public PedidoModel(long idProducto, int cantidad, long idLocal, long idCliente,
			long idVendedorOriginal, long idVendedorAuxiliar, boolean aceptado) {
		super();

		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idLocal = idLocal;
		this.idCliente = idCliente;
		this.idVendedorAuxiliar = idVendedorAuxiliar;
		this.idVendedorOriginal = idVendedorOriginal;
		this.aceptado = aceptado;
	}

	public long getIdProducto() {

		return this.idProducto;
	}
	public void setIdProducto(long idProducto) {
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

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public long getIdCliente() {
		return idCliente;
	}
	
	public long getIdVendedorOriginal() {

		return this.idVendedorOriginal;
	}

	public void setIdVendedorOriginal(long idVendedorOriginal) {

		this.idVendedorOriginal = idVendedorOriginal;
	}

	public long getIdVendedorAuxiliar() {

		return this.idVendedorAuxiliar;
	}

	public void setIdVendedorAuxiliar(long idVendedorAuxiliar) {

		this.idVendedorAuxiliar = idVendedorAuxiliar;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	//ADD EMPLEADO EN ABM

	@Override
	public String toString() {
		return "Pedido [idProducto=" + idProducto + ", cantidad=" + cantidad + ", idLocal=" + idLocal + ", idCliente=" + idCliente
				+ ", empleados: "+idVendedorOriginal+idVendedorAuxiliar+"]\n\n";
	}

}