package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;

public class Lote {

	private long idLote;
	private int cantidadInicial;
	private int cantidadActual;
	private LocalDate fechaIngreso;
	private Producto producto;
	private boolean estado;
	private Stock stock;

	public Lote() {
	}

	public Lote(int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, Producto producto, Stock stock){
		super();

		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.stock = stock;
		this.estado = true;
	}

	public long getIdLote() {
		return idLote;
	}

	protected void setIdLote(long idLote) {
		this.idLote = idLote;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Stock getStock() {

		return this.stock;
	}

	public void setStock(Stock stock) {

		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Lote [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual=" + cantidadActual
				+ ", fechaIngreso=" + fechaIngreso + ", producto=" + producto + ", estado=" + estado + "Stock: "+stock+"]\n";
	}

}