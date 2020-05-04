package com.unla.grupo_2_oo2_2020.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPedido;

	@ManyToOne 
	@JoinColumn(name = "idProducto")
	private Producto producto;
	@ManyToOne 
	@JoinColumn(name = "idLocal")
	private Local local;
	@ManyToOne 
	@JoinColumn(name = "idCliente") 
	private Cliente cliente;
	@ManyToOne 
	@JoinColumn(name = "idCarrito") 
	private Carrito carrito;
	@ManyToOne 
	@JoinColumn(name = "id_vendedor_original") 
	private Empleado vendedorOriginal;
	@ManyToOne 
	@JoinColumn(name = "id_vendedor_auxiliar") 
	 private Empleado vendedorAuxiliar;
	@Column private boolean aceptado;
	@Column private int cantidad;

	public Pedido() {
	}

	public Pedido(Producto producto, int cantidad, Local local, Cliente cliente, Carrito carrito,
			Empleado vendedorOriginal, Empleado vendedorAuxiliar, boolean aceptado) {
		super();

		this.producto = producto;
		this.cantidad = cantidad;
		this.local = local;
		this.cliente = cliente;
		this.carrito = carrito;
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

	public Empleado getVendedorOriginal() {

		return this.vendedorOriginal;
	}

	public void setVendedorOriginal(Empleado vendedorOriginal) {

		this.vendedorOriginal = vendedorOriginal;
	}

	public Empleado getVendedorAuxiliar() {

		return this.vendedorAuxiliar;
	}

	public void setVendedorAuxiliar(Empleado vendedorAuxiliar) {

		this.vendedorAuxiliar = vendedorAuxiliar;
	}

	public Carrito getCarrito() {

		return this.carrito;
	}

	public void setCarrito(Carrito carrito) {

		this.carrito = carrito;
	}

	public float getSubtotal() {

		return producto.getPrecio() * cantidad;
	}

	//ADD EMPLEADO EN ABM

	@Override
	public String toString() {
		return "Pedido [producto=" + producto + ", cantidad=" + cantidad + ", local=" + local + ", cliente=" + cliente
				+ ", vendedorAuxiliar: "+vendedorAuxiliar + "VendedorOriginal:"+vendedorOriginal+ "carrito" + carrito + "]\n\n";
	}

}