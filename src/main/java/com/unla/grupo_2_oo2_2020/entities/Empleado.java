package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Data
@EqualsAndHashCode(callSuper = false, exclude = "local")
@NoArgsConstructor
public class Empleado extends User {

	private String franjaHoraria;

	@Column(name = "tipoEmpleado")
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.

	@ManyToOne
	@JoinColumn(name = "idLocal")
	private Local local;

	public Empleado(long id, String franjaHoraria, boolean tipoEmpleado, String nombre, String apellido,
			LocalDate fechaNacimiento, int dni, String username, String password) {
		super(id, nombre, apellido, fechaNacimiento, dni, username, password);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;

	}

	public Empleado(String franjaHoraria, boolean tipoEmpleado, String nombre, String apellido,
			LocalDate fechaNacimiento, int dni, String username, String password) {
		super(nombre, apellido, fechaNacimiento, dni, username, password);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;

	}

}
