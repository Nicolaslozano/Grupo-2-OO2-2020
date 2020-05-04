package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

public class EmpleadoModel extends PersonaModel {
	private String franjaHoraria;
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.
	private LocalModel local;

	public EmpleadoModel() {
	}

	public EmpleadoModel(String nombre, String apellido, LocalDate fechaNacimiento, int dni,
			String franjaHoraria, boolean tipoEmpleado, LocalModel local) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.franjaHoraria = franjaHoraria;
		this.tipoEmpleado = tipoEmpleado;
		this.local = local;
	}

	public String getFranjaHoraria() {
		return franjaHoraria;
	}

	public void setFranjaHoraria(String franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}

	public boolean isTipoEmpleado() {
		return tipoEmpleado;
	}
	
	
	

	public void setTipoEmpleado(boolean tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Empleado [franjaHoraria=" + franjaHoraria + ", tipoEmpleado=" + tipoEmpleado + ", local=" + local
				+ ", idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", dni=" + dni + "]";
	}

	
	

}
