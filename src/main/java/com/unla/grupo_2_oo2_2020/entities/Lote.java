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

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "lote")
@Data @NoArgsConstructor
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

	public Lote(int cantidadInicial, int cantidadActual){
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = LocalDate.now();
		this.estado = true;
	}
	public Lote(long idLote,int cantidadInicial, int cantidadActual, boolean estado){
		this.idLote=idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = LocalDate.now();
		this.estado = estado;
	}

}