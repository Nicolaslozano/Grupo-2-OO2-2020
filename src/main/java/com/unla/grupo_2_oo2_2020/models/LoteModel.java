package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

public class LoteModel {

	private long idLote;
	private int cantidadInicial;
	private int cantidadActual;
	private LocalDate fechaIngreso;
	private ProductoModel producto;
	private boolean estado;
	private StockModel stock;

	public LoteModel() {
	}

	public LoteModel(int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, ProductoModel producto,boolean estaado) {
		super();

		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
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

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public StockModel getStock() {

		return this.stock;
	}

	public void setStock(StockModel stock) {

		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Lote [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual=" + cantidadActual
				+ ", fechaIngreso=" + fechaIngreso + ", producto=" + producto + ", estado=" + estado + "Stock: "+stock+"]\n";
	}

}