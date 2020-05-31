package com.unla.grupo_2_oo2_2020.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalModel {

	private long idLocal;
	@NotEmpty(message = StaticValuesHelper.ADDRESS_REQUIRED)
	private String direccion;
	@Min(value=-90, message = StaticValuesHelper.LATITUDE_ERROR)
	@Max(value=90, message = StaticValuesHelper.LATITUDE_ERROR)
	private double latitud;
	@Min(value=-180, message = StaticValuesHelper.LONGITUDE_ERROR)
	@Max(value=180, message = StaticValuesHelper.LONGITUDE_ERROR)
	private double longitud;
	@Min(value=1, message = StaticValuesHelper.TELEPHONE_REQUIRED)
	private long telefono;

	public LocalModel(long idLocal, String direccion, double latitud, double longitud, long telefono) {

		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;

	}

}
