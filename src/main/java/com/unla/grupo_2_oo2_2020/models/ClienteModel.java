package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import com.unla.grupo_2_oo2_2020.models.PersonaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor
public class ClienteModel extends PersonaModel {
	private String email;

	public ClienteModel(long idPersona, String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(idPersona,nombre,apellido,fechaNacimiento,dni);
		this.email = email;
	}

}