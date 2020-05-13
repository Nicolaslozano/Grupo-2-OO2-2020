package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(referencedColumnName="idPersona")
public class Empleado extends Persona {

	@Column(name = "franjaHoraria",length = 45)
	private String franjaHoraria;

	@Column(name = "tipoEmpleado")
	private boolean tipoEmpleado; // true = Vendedor, false = Gerente.

	@ManyToOne
	@JoinColumn(name = "idLocal")
	private Local local;

	public Empleado() {
	}

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

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Empleado [franjaHoraria=" + franjaHoraria + ", tipoEmpleado=" + tipoEmpleado + ", local=" + local
				+ ", idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", dni=" + dni + "]";
	}

	
	

}
