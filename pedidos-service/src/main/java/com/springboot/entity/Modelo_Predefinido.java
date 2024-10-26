package com.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modelo_predefinido")
public class Modelo_Predefinido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modelo_predefinido_id;
	private String nombre;
	private String codigo;
	private String comentario;
	private double precio;
	private String imagen;
}
