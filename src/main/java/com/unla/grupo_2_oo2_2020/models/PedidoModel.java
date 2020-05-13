package com.unla.grupo_2_oo2_2020.models;

import java.util.HashSet;
import java.util.Set;

public class PedidoModel {

	private long idPedido;
	private ProductoModel producto;
	private int cantidad;
	private LocalModel local;
	private ClienteModel cliente;
	private EmpleadoModel vendedorOriginal; 
	private EmpleadoModel vendedorAuxiliar;
	private float subtotal;
	private boolean aceptado;

	public PedidoModel() {
	}

	public PedidoModel(ProductoModel producto, int cantidad, LocalModel local, ClienteModel cliente,
			EmpleadoModel vendedorOriginal, EmpleadoModel vendedorAuxiliar, boolean aceptado) {
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

	public ProductoModel getProducto() {

		return this.producto;
	}
	protected void setProducto(ProductoModel producto) {
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

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	public ClienteModel getCliente() {
		return cliente;
	}
	
	public EmpleadoModel getVendedorOriginal() {

		return this.vendedorOriginal;
	}

	public void setVendedorOriginal(EmpleadoModel vendedorOriginal) {

		this.vendedorOriginal = vendedorOriginal;
	}

	public EmpleadoModel getVendedorAuxiliar() {

		return this.vendedorAuxiliar;
	}

	public void setVendedorAuxiliar(EmpleadoModel vendedorAuxiliar) {

		this.vendedorAuxiliar = vendedorAuxiliar;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
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
				+ ", empleados: "+vendedorOriginal+vendedorAuxiliar+"]\n\n";
	}

}