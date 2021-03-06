package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EmpleadoModel extends UserModel {
	private String franjaHoraria;
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.
	private long idLocal;

	public EmpleadoModel(long id, String nombre, String apellido, LocalDate fechaNacimiento, int dni,
			String franjaHoraria, boolean tipoEmpleado, long idLocal, String username, String password) {
		super(id, nombre, apellido, fechaNacimiento, dni, username, password);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;
		this.idLocal = idLocal;

	}

}
