package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="persona")
@Data @NoArgsConstructor
@Deprecated
public abstract class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long idPersona;

	protected String nombre;

	protected String apellido;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaNacimiento")
	protected LocalDate fechaNacimiento;

	protected int dni;

	public Persona(long idPersona, String nombre, String apellido, LocalDate fechaNacimiento, int dni) {

		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
	}

	public Persona(String nombre, String apellido, LocalDate fechaNacimiento, int dni) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
	}

}