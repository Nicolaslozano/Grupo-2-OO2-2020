package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import com.unla.grupo_2_oo2_2020.entities.Persona;

public class ClienteModel extends Persona {
	private String email;

	public ClienteModel() {

	}

	public ClienteModel(String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(nombre,apellido,fechaNacimiento,dni);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClienteModel [email=" + email + ", idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + "]";
	}


}