package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;

import com.unla.grupo_2_oo2_2020.models.PersonaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor
public class ClienteModel extends PersonaModel {

	@Email(message = "Email valido requerido")
	private String email;

	public ClienteModel(long idPersona, String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(idPersona,nombre,apellido,fechaNacimiento,dni);
		this.email = email;
	}

}