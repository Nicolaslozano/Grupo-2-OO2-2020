package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(referencedColumnName="idPersona")
@Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor
public class Empleado extends Persona {

	@Column(name = "franjaHoraria",length = 45)
	private String franjaHoraria;

	@Column(name = "tipoEmpleado")
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.

	@ManyToOne
	@JoinColumn(name = "idLocal")
	private Local local;

	public Empleado(long idPersona,String nombre, String apellido, LocalDate fechaNacimiento, int dni,
			String franjaHoraria, boolean tipoEmpleado) {
		super(idPersona,nombre, apellido, fechaNacimiento, dni);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;

	}

	public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, int dni,
			String franjaHoraria, boolean tipoEmpleado) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;

	}

}
