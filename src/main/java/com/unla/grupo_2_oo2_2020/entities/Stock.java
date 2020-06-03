package com.unla.grupo_2_oo2_2020.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "local")
@ToString(exclude = "lotes")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idStock;

	@Column(name = "cantidad")
	private int cantidad;

	@OneToOne
	@JoinColumn(name = "idStock")
	@MapsId
	private Local local;

	@OneToMany(mappedBy = "stock",cascade = CascadeType.ALL)
	private Set<Lote> lotes;

	public Stock(int cantidad, Local local) {
		this.lotes = new HashSet<Lote>();
		this.cantidad = cantidad;
		this.local = local;
	}

	public Stock(long idStock, int cantidad) {
		this.idStock = idStock;
		this.lotes = new HashSet<Lote>();
		this.cantidad = cantidad;
	}

}