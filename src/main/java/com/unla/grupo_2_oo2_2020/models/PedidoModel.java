package com.unla.grupo_2_oo2_2020.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoModel {
	
	private long idPedido;
	private long idProducto;
	private int cantidad;
	private long idLocal;
	private long idCliente;
	private long idVendedorOriginal;
	private long idVendedorAuxiliar;
	private boolean aceptado;
	
	public PedidoModel(long idPedido, long idProducto, int cantidad, long idLocal, long idCliente,
			long idVendedorOriginal, long idVendedorAuxiliar, boolean aceptado) {
	
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idLocal = idLocal;
		this.idCliente = idCliente;
		this.idVendedorOriginal = idVendedorOriginal;
		this.idVendedorAuxiliar = idVendedorAuxiliar;
		this.aceptado = aceptado;
	}
	


	

}