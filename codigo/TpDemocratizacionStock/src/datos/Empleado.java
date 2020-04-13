package datos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Empleado extends Persona {

	private LocalTime horaEntrada;
	private LocalTime horaSalida;
	private float sueldo;
	private boolean esGerente;

	public Empleado() {
	}

	public Empleado(LocalTime horaEntrada, LocalTime horaSalida, float sueldo, boolean esGerente, String nombre,
			String apellido, int dni, LocalDate fechaNacimiento) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.sueldo = sueldo;
		this.esGerente = esGerente;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public boolean isEsGerente() {
		return esGerente;
	}

	public void setEsGerente(boolean esGerente) {
		this.esGerente = esGerente;
	}

}
