package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insumo")
public class Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int insumo_id;
	private String nombre;
	private String descripcion;
	private String unidad_medida;
	private String marca;
	private double precio_xunidad;
	private int cantidad_total;
	private Boolean activo;
	private double coste_insumo;
	
	
	@OneToMany(mappedBy = "insumo")
	@JsonIgnore
	private List<Insumo_Pedido> insumo_pedido;
	
	@OneToMany(mappedBy = "insumo")
	@JsonIgnore
	private List<Presupuesto> presupuesto;
	
	@OneToMany(mappedBy = "insumo")
	@JsonIgnore
	private List<Modelo_Predefinido> modelo_predefinido;
}
