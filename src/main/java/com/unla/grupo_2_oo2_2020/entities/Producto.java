package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "producto")
@Data @NoArgsConstructor
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	@Column (name="nombre") 
	private String nombre;
	@Column (name="descripcion")
	private String descripcion;
	@Column (name="precio") 
	private float precio;
	@Column (name="fechaAlta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaAlta;

	public Producto(String nombre, String descripcion, float precio, LocalDate fechaAlta) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaAlta = LocalDate.now();
	}

	public Producto(long idProducto, String nombre, String descripcion, float precio, LocalDate fechaAlta) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaAlta = LocalDate.now();
	}

}
