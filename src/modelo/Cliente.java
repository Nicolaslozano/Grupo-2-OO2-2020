package modelo;

import java.time.LocalDate;

public class Cliente extends Persona {
	private String email;

	public Cliente(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String email) {
		super(nombre, apellido, fechaNacimiento, dni);
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
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", dni=" + dni + ", email=" + email + "]\n";
	}

	public boolean rechazarEspera() {
		return false;
	}

	public boolean aceptarEspera() {
		return true;
	}

}