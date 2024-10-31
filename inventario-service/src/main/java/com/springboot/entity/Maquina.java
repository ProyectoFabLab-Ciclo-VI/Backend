package com.springboot.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
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

@AllArgsConstructor
@NoArgsConstructor 
@Data
@Entity
@Table(name = "maquina")
public class Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maquina_id;
	private String nombre;
	private String codigo_upeu;
	private String estado;
	private Boolean activo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_maquina_id", referencedColumnName = "tipo_maquina_id")
	private Tipo_Maquina tipo_maquina;

	public Maquina(String nombre, String codigo_upeu, String estado, Boolean activo, Tipo_Maquina tipo_maquina) {
		
		this.nombre = nombre;
		this.codigo_upeu = codigo_upeu;
		this.estado = estado;
		this.activo = activo;
		this.tipo_maquina = tipo_maquina;
	}
	
	
		
	
}
