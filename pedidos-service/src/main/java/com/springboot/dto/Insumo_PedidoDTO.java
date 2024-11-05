package com.springboot.dto;

import com.springboot.entity.Insumo;
import com.springboot.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insumo_PedidoDTO {

	private int cantidad_usada;
	
	private Pedido pedido;
	private Insumo insumo;
}
