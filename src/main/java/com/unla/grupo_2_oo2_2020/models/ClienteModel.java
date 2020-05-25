package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.unla.grupo_2_oo2_2020.models.PersonaModel;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor
public class ClienteModel extends PersonaModel {

	@Email(message = StaticValuesHelper.EMAIL_REQUIRED)
	@NotEmpty(message = StaticValuesHelper.EMAIL_REQUIRED)
	private String email;

	public ClienteModel(long idPersona, String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(idPersona,nombre,apellido,fechaNacimiento,dni);
		this.email = email;
	}

}