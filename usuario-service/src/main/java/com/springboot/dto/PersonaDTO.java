package com.springboot.dto;

import java.util.Date;


import com.springboot.entity.Cargo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaDTO {

	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String codigo;
	
	private Cargo cargo;
}
