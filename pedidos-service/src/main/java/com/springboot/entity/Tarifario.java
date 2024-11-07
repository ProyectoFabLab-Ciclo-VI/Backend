package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tarifario")
public class Tarifario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tarifario_id;
	private double precio_venta;
	private double porcentaje_desperdicio;
	
	@OneToMany(mappedBy = "tarifario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Presupuesto> presupuesto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insumo_id")
	private Insumo insumo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maquina_id")
	private Maquina maquina;

	public Tarifario(double precio_venta, double porcentaje_desperdicio, Insumo insumo, Maquina maquina) {
		
		this.precio_venta = precio_venta;
		this.porcentaje_desperdicio = porcentaje_desperdicio;
		this.insumo = insumo;
		this.maquina = maquina;
	}
	
	
	
}
