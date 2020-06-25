package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Cliente extends User {

	private String email;

	public Cliente(long id, String email, String nombre, String apellido, LocalDate fechaNacimiento, int dni,
			String username, String password) {
		super(id, nombre, apellido, fechaNacimiento, dni, username, password);
		this.email = email;
	}

	public Cliente(String email, String nombre, String apellido, LocalDate fechaNacimiento, int dni, String username,
			String password) {
		super(nombre, apellido, fechaNacimiento, dni, username, password);
		this.email = email;
	}

}