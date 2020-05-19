package com.unla.grupo_2_oo2_2020.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comercio") // 'local' esta reservado en mysql
@Data @NoArgsConstructor
public class Local {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLocal;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "latitud")
	private double latitud;

	@Column(name = "longitud")
	private double longitud;

	@Column(name = "telefono")
	private long telefono;

	@OneToOne(mappedBy = "local", cascade = CascadeType.ALL)
	private Stock stock;

	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private Set<SolicitudStock> solicitudesStock;

	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private Set<Empleado> empleados;

	public Local(long idLocal, String direccion, double latitud, double longitud, long telefono) {

		this.idLocal = idLocal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.empleados = new HashSet<Empleado>();
		this.solicitudesStock = new HashSet<SolicitudStock>();
	}

	public Local(String direccion, double latitud, double longitud, long telefono) {

		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.telefono = telefono;
		this.empleados = new HashSet<Empleado>();
		this.solicitudesStock = new HashSet<SolicitudStock>();
	}

	public double calculateDistance(Local local_2) {

        Local local_1 = this;

        double rad = Math.PI / 180; // Para convertir a Radianes
        double dlat = local_1.getLatitud() - local_2.getLatitud(); // Diferencia de latitudes
        double dlong = local_1.getLongitud() - local_2.getLongitud(); // Diferencia de longitudes

        double R = 6372.795477598;// Radio de la tierra
        double a = Math.pow(Math.sin(rad * dlat / 2), 2) + Math.cos(rad * local_1.getLatitud())
                * Math.cos(rad * local_2.getLatitud()) * Math.pow(rad * Math.sin(dlong / 2), 2);
        double distancia = 2 * R * Math.asin(Math.sqrt(a));

        return Math.round(distancia);
    }

}