package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public class ProductoModel {

	private long idProducto;
	private String nombre;
	private String descripcion;
	private float precio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaAlta;

	public ProductoModel(long idProducto, String nombre, String descripcion, float precio, LocalDate fechaAlta) {

		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaAlta = LocalDate.now();
	}

}
