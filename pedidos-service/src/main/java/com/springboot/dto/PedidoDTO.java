package com.springboot.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.entity.Maquina;
import com.springboot.entity.Modelo_Predefinido;
import com.springboot.entity.Persona;
import com.springboot.entity.Presupuesto;

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
	private String fecha_pago;
	
	//private Persona persona;
	//private Modelo_Predefinido modelo_predefinido;
	//private Presupuesto presupuesto;
	
	private Integer persona_id;
	private Integer modelo_predefinido_id;
	private Integer presupuesto_id;
}
