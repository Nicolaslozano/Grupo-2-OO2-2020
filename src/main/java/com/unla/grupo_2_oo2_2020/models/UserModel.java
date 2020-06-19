package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserModel {

	protected long id;
	@NotEmpty(message = StaticValuesHelper.NAME_REQUIRED)
	protected String nombre;
	@NotEmpty(message = StaticValuesHelper.SURNAME_REQUIRED)
	protected String apellido;
	@NotNull(message = StaticValuesHelper.BIRTHDATE_REQUIRED)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fechaNacimiento;
	@Min(value = 1, message = StaticValuesHelper.DNI_REQUIRED)
	protected int dni;
	@NotEmpty(message = "username_required")
	protected String username;
	@Size(min = 3, max = 32, message = "password_required")
	protected String password;

	protected String passwordConfirm;

	public UserModel(long id, String nombre,String apellido,LocalDate fechaNacimiento,int dni, String username, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.username = username;
		this.password = password;
	}
}