package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public abstract class PersonaModel {

	protected long idPersona;
	@NotNull(message = "Nombre requerido")
	protected String nombre;
	@NotNull(message = "Apellido requerido")
	protected String apellido;
	@NotNull(message = "Fecha requerida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fechaNacimiento;
	@NotNull(message = "DNI requerido")
	protected int dni;

	public PersonaModel(long idPersona,String nombre, String apellido, LocalDate fechaNacimiento, int dni) {

		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
	}

}