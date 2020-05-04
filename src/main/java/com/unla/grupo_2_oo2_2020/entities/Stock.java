package com.unla.grupo_2_oo2_2020.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long idStock;
    @Column (name = "cantidad") 
    private int cantidad;

    @OneToOne
    @JoinColumn(name="local")
    private Local local;
    
    @OneToMany
    @JoinColumn (name="idStock")
    private Set<Lote> lotes;
    
	public Stock() {
	}

	public Stock(int cantidad, Local local) {
		this.lotes = new HashSet<Lote>();
		this.cantidad = cantidad;
		this.local = local;
	}

	public long getIdStock() {
		return idStock;
	}

	protected void setIdStock(long idStock) {
		this.idStock = idStock;
	}

	public Set<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Stock [idStock=" + idStock + ", lotes=" + lotes + ", cantidad=" + cantidad + "]";
	}

}