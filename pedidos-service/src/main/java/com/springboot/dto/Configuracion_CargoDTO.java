package com.springboot.dto;

import com.springboot.entity.Cargo;
import com.springboot.entity.Configuracion_Cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Configuracion_CargoDTO {

	private Boolean igv;
	private Boolean mano_obra;
	
	private Cargo cargo;
}
