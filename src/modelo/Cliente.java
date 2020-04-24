package modelo;

import java.time.LocalDate;

public class Cliente extends Persona {
	private String email;
	
	public Cliente() {
		
	}

	public Cliente(String email,int idPersona,String nombre,String apellido,LocalDate fechaNacimiento,long dni) {
		super(idPersona,nombre,apellido,fechaNacimiento,dni);
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
		return "Cliente [email=" + email + ", idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + "]";
	}


}