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
	private String dimension;
	private double div_unidad;
	private double precio_venta;
	private String observacion;
	private Boolean activo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marca_id")
	private Marca marca;

	public Insumo(String nombre, String descripcion, String dimension, double div_unidad, double precio_venta,
			String observacion, Boolean activo, Categoria categoria, Marca marca) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dimension = dimension;
		this.div_unidad = div_unidad;
		this.precio_venta = precio_venta;
		this.observacion = observacion;
		this.activo = activo;
		this.categoria = categoria;
		this.marca = marca;
	}
	
	
	
}
