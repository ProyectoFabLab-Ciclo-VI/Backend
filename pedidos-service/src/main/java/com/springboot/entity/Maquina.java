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
	private double coste_maquina;
	private double coste_amortizacion;
	private Boolean activo;
	
	@OneToMany(mappedBy = "maquina")
	@JsonIgnore
	private List<Presupuesto>  presupuesto;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "maquina_impresiones3d_id")
	private Maquina_Impresiones3D maquina_impresiones3d;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "papeleria_ploteo_id")
	private Papeleria_Ploteo papeleria_ploteo;
}
