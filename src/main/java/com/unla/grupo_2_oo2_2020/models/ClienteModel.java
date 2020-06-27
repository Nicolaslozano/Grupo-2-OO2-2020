package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;

import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ClienteModel extends UserModel {

	@Email(message = StaticValuesHelper.EMAIL_REQUIRED)
	private String email;

	public ClienteModel(long id, String email, String nombre, String apellido, LocalDate fechaNacimiento,
			int dni, String username, String password, String passwordConfirm) {
		super(id, nombre, apellido, fechaNacimiento, dni, username, password, passwordConfirm);
		this.email = email;
	}

	public ClienteModel(long id, String email, String nombre, String apellido, LocalDate fechaNacimiento,
			int dni, String username, String password) {
		super(id, nombre, apellido, fechaNacimiento, dni, username, password);
		this.email = email;
	}

}