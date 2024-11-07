package com.springboot.dto;

import com.springboot.entity.Maquina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion_TiempoDTO {

	private double precioxminuto;
	
	private Maquina maquina;
}
