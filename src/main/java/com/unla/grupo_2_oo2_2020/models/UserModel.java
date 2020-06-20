package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserModel {

	protected long id;
	protected String nombre;
	protected String apellido;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected LocalDate fechaNacimiento;
	protected int dni;
	protected String username;
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