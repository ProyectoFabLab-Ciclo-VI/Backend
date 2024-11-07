package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private Boolean activo;
	
	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Pedido> pedido;
	
	@OneToMany(mappedBy = "maquina")
	@JsonIgnore
	private List<Tarifario> tarifario;
	
	@OneToOne(mappedBy = "maquina")
	@JsonIgnore
	private Configuracion_Tiempo configuracion_tiempo;
}
