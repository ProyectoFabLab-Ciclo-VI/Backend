package com.springboot.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pedido_id;
	private String url_modelo;
	private Date fecha_pedido;
	private Date fecha_validacion;
	private String comentario;
	private String estado;
	private String codigo;
	private double precio_venta;
	private Date fecha_pago;
	
	@OneToOne(mappedBy = "pedido")
	@JsonIgnore
	private Pago pago;
	
}
