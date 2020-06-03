package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor
public class LoteModel {

	private long idLote;
	private int cantidadInicial;
	private int cantidadActual;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;
	private long idProducto;
	private boolean estado=true;
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