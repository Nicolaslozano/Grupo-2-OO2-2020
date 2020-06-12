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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data @NoArgsConstructor @EqualsAndHashCode(exclude="local")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPedido;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	@NotNull
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idLocal")
	@NotNull
	private Local local;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	@NotNull
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_vendedor_original")
	@NotNull
	private Empleado vendedorOriginal;

	@ManyToOne
	@JoinColumn(name = "id_vendedor_auxiliar")
	@Nullable
	 private Empleado vendedorAuxiliar;

	@Column(name = "estado") // 0 RECHAZADO 1 ACEPTADO 2 PENDIENTE
	private int estado;

	@Column(name = "cantidad")
	@Min(1)
	private int cantidad;

	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;

	public Pedido(int estado, int cantidad) {
		this.estado = estado;
		this.cantidad = cantidad;
		this.fecha = LocalDate.now();
	}

	public Pedido(long idPedido, int estado, int cantidad) {
		this.idPedido = idPedido;
		this.estado = estado;
		this.cantidad = cantidad;
		this.fecha = LocalDate.now();
	}

}
