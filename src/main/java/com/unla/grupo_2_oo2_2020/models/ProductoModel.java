package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public class ProductoModel {

	private long idProducto;
	@NotEmpty(message = StaticValuesHelper.NAME_REQUIRED)
	private String nombre;
	@NotEmpty(message = StaticValuesHelper.DESCRIPTION_REQUIRED)
	private String descripcion;
	@NotEmpty(message = StaticValuesHelper.PRICE_REQUIRED)
	private float precio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaAlta;

	public ProductoModel(long idProducto, String nombre, String descripcion, float precio, LocalDate fechaAlta) {

		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaAlta = fechaAlta;
	}

}
