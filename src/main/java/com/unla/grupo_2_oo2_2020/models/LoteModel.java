package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public class LoteModel {

	private long idLote;
	@Min(value=1, message = StaticValuesHelper.QUANTITY_REQUIRED)
	private int cantidadInicial;
	private int cantidadActual;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;
	@Min(value=1, message = StaticValuesHelper.PRODUCT_REQUIRED)
	private long idProducto;
	private boolean estado=true;
	@Min(value=1, message = StaticValuesHelper.LOCAL_REQUIRED)
	private long idStock;

	public LoteModel(long idLote, int cantidadInicial, int cantidadActual, long idProducto, long idStock, boolean estado) {
		this.idLote = idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = LocalDate.now();
		this.estado = estado;
		this.idProducto = idProducto;
		this.idStock = idStock;
	}

}