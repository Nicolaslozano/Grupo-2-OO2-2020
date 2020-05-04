package com.unla.grupo_2_oo2_2020.models;

import java.util.HashSet;
import java.util.Set;

public class PedidoModel {

	private long idPedido;
	private ProductoModel producto;
	private int cantidad;
	private LocalModel local;
	private ClienteModel cliente;
	private Set<EmpleadoModel> empleados = new HashSet<>();
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
		this.empleados.add(vendedorOriginal);
		if (vendedorAuxiliar != null) this.empleados.add(vendedorAuxiliar); //VALIDACIONES EN SET, CAMBIAR LUEGO
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

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public Set<EmpleadoModel> getEmpleados() {

		return this.empleados;
	}

	public void setEmpleados(Set<EmpleadoModel> empleados) {

		this.empleados = empleados;
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
				+ ", empleados: "+empleados+"]\n\n";
	}

}