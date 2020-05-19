package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public class SolicitudStockModel {

	private long idSolicitud;
	private LocalDate fecha;
	private ProductoModel producto;
	private int cantidad;
	private EmpleadoModel vendedor;
	private EmpleadoModel colaborador;
	private boolean aceptado;

	public SolicitudStockModel(long idSolicitud, LocalDate fecha, ProductoModel producto, int cantidad,
			EmpleadoModel vendedor, EmpleadoModel colaborador) {

		super();

		this.idSolicitud = idSolicitud;
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.vendedor = vendedor;
		this.colaborador = colaborador;
		this.aceptado = false;
	}

}