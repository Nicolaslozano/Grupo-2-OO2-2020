package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import com.unla.grupo_2_oo2_2020.models.PersonaModel;

public class ClienteModel extends PersonaModel {
	private String email;

	public ClienteModel() {

	}

	public ClienteModel(long idPersona, String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(idPersona,nombre,apellido,fechaNacimiento,dni);
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