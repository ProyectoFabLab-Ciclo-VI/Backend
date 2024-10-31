package com.springboot.dto;


import com.springboot.entity.Material;
import com.springboot.entity.Prestamo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle_prestamoDTO {

	private int cantidad;
	
	private Material material;
	private Prestamo prestamo;
}
