package com.unla.grupo_2_oo2_2020.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalModel {

	private long idLocal;
	private String direccion;
	private double latitud;
	private double longitud;
	private long telefono;

	public LocalModel(long idLocal, String direccion, double latitud, double longitud, long telefono) {

		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;

	}

}
