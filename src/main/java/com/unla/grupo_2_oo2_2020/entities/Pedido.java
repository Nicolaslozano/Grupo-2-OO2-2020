package com.unla.grupo_2_oo2_2020.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data @NoArgsConstructor
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPedido;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idLocal")
	private Local local;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_vendedor_original")
	private Empleado vendedorOriginal;

	@ManyToOne
	@JoinColumn(name = "id_vendedor_auxiliar")
	@Nullable
	 private Empleado vendedorAuxiliar;

	@Column(name = "aceptado")
	private boolean aceptado;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;

	public Pedido(boolean aceptado, int cantidad) {
		this.aceptado = aceptado;
		this.cantidad = cantidad;
		this.fecha = LocalDate.now();
	}

	public Pedido(long idPedido, boolean aceptado, int cantidad) {
		this.idPedido = idPedido;
		this.aceptado = aceptado;
		this.cantidad = cantidad;
		this.fecha = LocalDate.now();
	}

}
