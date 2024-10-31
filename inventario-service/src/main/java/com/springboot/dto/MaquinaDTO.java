package com.springboot.dto;



import com.springboot.entity.Tipo_Maquina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class MaquinaDTO {

	private String nombre;
	private String codigo_upeu;
	private String estado;
	private Boolean activo;
	
	private Tipo_Maquina tipo_maquina;
	
}
