package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public abstract class PersonaModel {

	protected long idPersona;
	@NotEmpty(message = StaticValuesHelper.NAME_REQUIRED)
	protected String nombre;
	@NotEmpty(message = StaticValuesHelper.SURNAME_REQUIRED)
	protected String apellido;
	@NotNull(message = StaticValuesHelper.BIRTHDATE_REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fechaNacimiento;
	@Min(value=1, message = StaticValuesHelper.DNI_REQUIRED)
	protected int dni;

	public PersonaModel(long idPersona,String nombre, String apellido, LocalDate fechaNacimiento, int dni) {

		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
	}

}