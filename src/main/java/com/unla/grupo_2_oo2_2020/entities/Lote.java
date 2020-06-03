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

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Where(clause="estado=1")
@Table(name = "lote")
@Data @NoArgsConstructor @EqualsAndHashCode(exclude="stock")
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
	private boolean estado=true;

	@ManyToOne
	@JoinColumn(name = "idStock")
	private Stock stock;

	public Lote(int cantidadInicial,boolean estado){
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadInicial;
		this.fechaIngreso = LocalDate.now();
		this.estado = estado;
	}
	public Lote(long idLote,int cantidadInicial, int cantidadActual, boolean estado){
		this.idLote=idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = LocalDate.now();
		this.estado = estado;
	}

}