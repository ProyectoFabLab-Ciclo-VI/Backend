package com.springboot.dto;

import com.springboot.entity.Insumo;
import com.springboot.entity.Maquina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarifarioDTO {

	private double precio_venta;
	private double porcentaje_desperdicio;
	
	private Insumo insumo;
	private Maquina maquina;
}
