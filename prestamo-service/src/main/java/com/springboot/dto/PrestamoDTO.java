package com.springboot.dto;

import java.util.Date;

import com.springboot.entity.Material;
import com.springboot.entity.Persona;
import com.springboot.entity.Prestamo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDTO {

	private String codigo;
	private String motivo;
	private Date fecha_prestamo;
	private Date fecha_devolucion;
	
	private Persona persona;
}
