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
	private String codigo_maquina;
	private Date fecha_compra;
	private double precio;
	private String codigo_upeu;
	private Boolean estado;
	private double porc_desperdicio;
	private String tipo_cotizacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Maquina(String nombre, String codigo_maquina, Date fecha_compra, double precio, String codigo_upeu,
			Boolean estado, double porc_desperdicio, String tipo_cotizacion, Categoria categoria) {
		
		this.nombre = nombre;
		this.codigo_maquina = codigo_maquina;
		this.fecha_compra = fecha_compra;
		this.precio = precio;
		this.codigo_upeu = codigo_upeu;
		this.estado = estado;
		this.porc_desperdicio = porc_desperdicio;
		this.tipo_cotizacion = tipo_cotizacion;
		this.categoria = categoria;
	}

	
	
	
}
