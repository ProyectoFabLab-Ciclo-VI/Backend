package com.springboot.dto;

import java.util.Date;

import com.springboot.entity.Insumo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguimiento_InsumoDTO {

	private Date fecha_compra;
	private double cantidad_compra;
	private Boolean validado;
	
	private Insumo insumo;
}
