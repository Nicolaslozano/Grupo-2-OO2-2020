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
@Table(name = "solicitudStock")
@Data @NoArgsConstructor
public class SolicitudStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSolicitud;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "idLocal")
	private Local local;
	@Column
	private int cantidad;
	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Empleado vendedor;
	@ManyToOne
	@JoinColumn(name = "id_colaborador")
	private Empleado colaborador;
	@Column(name = "aceptado")
	private boolean aceptado;

	public SolicitudStock(Local local,LocalDate fecha, Producto producto, int cantidad, Empleado vendedor, Empleado colaborador) {

		this.local = local;
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.vendedor = vendedor;
		this.colaborador = colaborador;

		this.aceptado = false;
	}

}