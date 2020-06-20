package com.unla.grupo_2_oo2_2020.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@ToString(exclude = "roles")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String nombre;

	protected String apellido;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaNacimiento")
	protected LocalDate fechaNacimiento;

	protected int dni;

    protected String username;

    protected String password;

    @ManyToMany
    @JoinTable(
        name = "role",
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"))
    protected Collection<Role> roles;

    public User(String nombre, String apellido,LocalDate fechaNacimiento, int dni, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.username = username;
        this.dni = dni;
        this.password = password;
    }

    public User(long id, String nombre, String apellido,LocalDate fechaNacimiento, int dni, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.password = password;
        this.username = username;
    }

}
