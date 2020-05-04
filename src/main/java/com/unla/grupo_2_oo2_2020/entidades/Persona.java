package com.unla.grupo_2_oo2_2020.entidades;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="persona")
public abstract class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long idPersona;

	@Column(name="nombre", unique=false, nullable=false, length=45)
	protected String nombre;

	@Column(name="apellido", unique=false, nullable=false, length=45)
	protected String apellido;

	@Column(name="fechaNacimiento")
	protected LocalDate fechaNacimiento;

	@Column(name="dni",unique = true,nullable = false)
	protected int dni;

	public Persona() {
	}

	public Persona(String nombre, String apellido, LocalDate fechaNacimiento, int dni) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
	}

	public long getIdPersona() {
		return idPersona;
	}

	protected void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + "]";
	}

	
	

}