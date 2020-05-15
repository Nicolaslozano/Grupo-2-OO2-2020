package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "lote")
public class Lote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idLote;

	@Column (name = "cantidadInicial")
	private int cantidadInicial;

	@Column (name = "CantidadActual")
	private int cantidadActual;

	@Column (name = "fechaIngreso")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;

	@Column (name = "estado") 
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "idStock")
	private Stock stock;

	public Lote() {
	}

	public Lote(int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, Producto producto){
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.estado = true;
	}
	public Lote(long idLote,int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, Producto producto){
		this.idLote=idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.estado = true;
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