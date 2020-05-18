package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(referencedColumnName="idPersona")
@Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor
public class Cliente extends Persona {

	@Column(name = "email",nullable = false, length = 45)
	private String email;

	public Cliente(long idPersona,String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(idPersona,nombre,apellido,fechaNacimiento,dni);
		this.email = email;
	}

	public Cliente(String email,String nombre,String apellido,LocalDate fechaNacimiento,int dni) {
		super(nombre,apellido,fechaNacimiento,dni);
		this.email = email;
	}

}