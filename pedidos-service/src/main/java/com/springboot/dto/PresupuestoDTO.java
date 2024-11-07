package com.springboot.dto;

import com.springboot.entity.Configuracion_Cargo;
import com.springboot.entity.Configuracion_Tiempo;
import com.springboot.entity.Tarifario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PresupuestoDTO {

	private int cantidad_empleada;
	private int monto_mano_obra;
	private double precio_total;
	
	private Configuracion_Cargo configuracion_cargo;
	private Configuracion_Tiempo configuracion_tiempo;
	private Tarifario tarifario;
}
