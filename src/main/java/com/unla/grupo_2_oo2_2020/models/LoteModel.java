package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LoteModel {

	private long idLote;
	private int cantidadInicial;
	private int cantidadActual;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;
	private long idProducto;
	private boolean estado;
	private long idStock;

	public LoteModel() {
	}
	
	public LoteModel(long idLote, int cantidadInicial, int cantidadActual, long idProducto, long idStock, boolean estado) {
		this.idLote = idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = LocalDate.now();
		this.estado = estado;
		this.idProducto = idProducto;
		this.idStock = idStock;
	}

	public long getIdLote() {
		return idLote;
	}

	public void setIdLote(long idLote) {
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

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public long getIdStock() {

		return idStock;
	}

	public void setIdStock(long idStock) {

		this.idStock = idStock;
	}

	@Override
	public String toString() {
		return "Lote [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual=" + cantidadActual
				+ ", fechaIngreso=" + fechaIngreso + ", producto=" + idProducto + ", estado=" + estado + "Stock: "+idStock + "]\n";
	}

}