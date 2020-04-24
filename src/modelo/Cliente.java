package modelo;

import java.time.LocalDate;

public class Cliente extends Persona {
	private int idCliente;
	private String email;

	public Cliente() {
	}

	public Cliente(int idCliente, String email, String nombre, String apellido, LocalDate fechaNacimiento, long dni) {
		super(nombre, apellido, fechaNacimiento, dni);
		this.idCliente = idCliente;
		this.email = email;
	}



	public int getIdCliente() {
		return idCliente;
	}

	protected void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Cliente [email=" + email + "]";
	}

}