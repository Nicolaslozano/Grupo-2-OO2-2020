package com.unla.grupo_2_oo2_2020.models;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import com.unla.grupo_2_oo2_2020.helpers.StaticValuesHelper;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoModel {

	private long idPedido;
	@Min(value=1, message = StaticValuesHelper.PRODUCT_REQUIRED)
	private long idProducto;
	@Min(value=1, message = StaticValuesHelper.QUANTITY_REQUIRED)
	private int cantidad;
	@Min(value=1, message = StaticValuesHelper.LOCAL_REQUIRED)
	private long idLocal;
	private long idCliente;
	@Min(value=1, message = StaticValuesHelper.SELLER_REQUIRED)
	private long idVendedorOriginal;
	private long idVendedorAuxiliar;
	private boolean aceptado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;

	public PedidoModel(long idPedido, long idProducto, int cantidad, long idLocal, long idCliente,
			long idVendedorOriginal, long idVendedorAuxiliar, boolean aceptado, LocalDate fecha) {

		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idLocal = idLocal;
		this.idCliente = idCliente;
		this.idVendedorOriginal = idVendedorOriginal;
		this.idVendedorAuxiliar = idVendedorAuxiliar;
		this.aceptado = aceptado;
		this.fecha = fecha;
	}

}