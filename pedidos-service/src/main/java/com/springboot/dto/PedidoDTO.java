package com.springboot.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.entity.Maquina;
import com.springboot.entity.Modelo_Predefinido;
import com.springboot.entity.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	private MultipartFile url_modelo;
	private String fecha_pedido;
	private String fecha_validacion;
	private String comentario;
	private String estado;
	private String codigo;
	private double precio_venta;
	private String fecha_pago;
	
	private Maquina maquina;
	private Persona persona;
	private Modelo_Predefinido modelo_predefinido;
}
