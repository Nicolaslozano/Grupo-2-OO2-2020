package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Cliente extends Persona {
	private String email;
	private Set<Producto> carrito = new HashSet();

	public Cliente() {

	}

	public Cliente(String email, Set<Producto> carrito, String nombre, String apellido, int dni,
			LocalDate fechaNacimiento) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.email = email;
		this.carrito = carrito;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Producto> getCarrito() {
		return carrito;
	}

	public void setCarrito(Set<Producto> carrito) {
		this.carrito = carrito;
	}

	@Override
	public String toString() {
		return "Cliente [email=" + email + ", carrito=" + carrito + ",IdPersona()=" + getIdPersona() + ",Nombre="
				+ getNombre() + ",Apellido=" + getApellido() + ",Dni=" + getDni() + ",FechaNacimiento="
				+ getFechaNacimiento() + "]";
	}

}
