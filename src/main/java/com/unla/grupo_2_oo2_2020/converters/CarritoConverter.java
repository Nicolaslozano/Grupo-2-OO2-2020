package com.unla.grupo_2_oo2_2020.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo_2_oo2_2020.entities.Carrito;
import com.unla.grupo_2_oo2_2020.models.CarritoModel;

@Component("CarritoConverter")
public class CarritoConverter {

	public CarritoModel entityToModel(Carrito carrito) {
		return new CarritoModel(carrito.getFecha());
	}

	public Carrito modelToEntity(CarritoModel CarritoModel) {
		return new Carrito(CarritoModel.getFecha());
	}
}
