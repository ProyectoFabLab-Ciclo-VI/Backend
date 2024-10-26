package com.springboot.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modelo_predefinidoDTO {

	private String nombre;
	private String codigo;
	private String comentario;
	private double precio;
	private MultipartFile imagen;
}
