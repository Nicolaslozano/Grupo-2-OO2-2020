package com.unla.grupo_2_oo2_2020.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data @NoArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPedido;
	
	@Column (name = "aceptado")
	private boolean aceptado;
	
	@Column (name = "cantidad")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private long idCliente;
	
	@ManyToOne
	@JoinColumn(name = "id_vendedor_original")
	private long idVendedorOriginal;
	
	@ManyToOne
	@JoinColumn(name = "id_vendedor_auxiliar")
	private long idVendedorAuxiliar;
	
	@ManyToOne
	@JoinColumn(name = "idLocal")
	private long local;
	
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private long producto;

	public Pedido(long idPedido, boolean aceptado, int cantidad, long idCliente, long idVendedorOriginal,
			long idVendedorAuxiliar, long local, long producto) {
		this.idPedido = idPedido;
		this.aceptado = aceptado;
		this.cantidad = cantidad;
		this.idCliente = idCliente;
		this.idVendedorOriginal = idVendedorOriginal;
		this.idVendedorAuxiliar = idVendedorAuxiliar;
		this.local = local;
		this.producto = producto;
	}

	public Pedido(boolean aceptado, int cantidad, long idCliente, long idVendedorOriginal, long idVendedorAuxiliar,
			long local, long producto) {
		this.aceptado = aceptado;
		this.cantidad = cantidad;
		this.idCliente = idCliente;
		this.idVendedorOriginal = idVendedorOriginal;
		this.idVendedorAuxiliar = idVendedorAuxiliar;
		this.local = local;
		this.producto = producto;
	}
	
}
