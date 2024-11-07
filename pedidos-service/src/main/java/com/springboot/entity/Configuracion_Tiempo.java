package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configuracion_tiempo")
public class Configuracion_Tiempo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int configuracion_tiempo_id;
	private double precioxminuto;
	
	@OneToOne
	@JoinColumn(name = "maquina_id")
	private Maquina maquina;
	
	@OneToMany(mappedBy = "configuracion_tiempo")
	@JsonIgnore
	private List<Presupuesto> presupuesto;

	public Configuracion_Tiempo(double precioxminuto, Maquina maquina) {
		
		this.precioxminuto = precioxminuto;
		this.maquina = maquina;
	}
	
}
