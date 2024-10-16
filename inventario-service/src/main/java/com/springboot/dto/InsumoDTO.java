package com.springboot.dto;



import com.springboot.entity.Categoria;
import com.springboot.entity.Marca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class InsumoDTO {

	private String nombre;
	private String descripcion;
	private String dimension;
	private double div_unidad;
	private double precio_venta;
	private String observacion;
	private Boolean activo;
	
	private Categoria categoria;
	private Marca marca;
	
	
}
