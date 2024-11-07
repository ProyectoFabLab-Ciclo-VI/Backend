package com.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "presupuesto")
public class Presupuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int presupuesto_id;
	private int cantidad_empleada;
	private int monto_mano_obra;
	private double precio_total;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "configuracion_cargo_id")
	private Configuracion_Cargo configuracion_cargo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "configuracion_tiempo_id")
	private Configuracion_Tiempo configuracion_tiempo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tarifario_id")
	private Tarifario tarifario;

	public Presupuesto(int cantidad_empleada, int monto_mano_obra, double precio_total,
			Configuracion_Cargo configuracion_cargo, Configuracion_Tiempo configuracion_tiempo, Tarifario tarifario) {
		this.cantidad_empleada = cantidad_empleada;
		this.monto_mano_obra = monto_mano_obra;
		this.precio_total = precio_total;
		this.configuracion_cargo = configuracion_cargo;
		this.configuracion_tiempo = configuracion_tiempo;
		this.tarifario = tarifario;
	}
	
	
	
}
