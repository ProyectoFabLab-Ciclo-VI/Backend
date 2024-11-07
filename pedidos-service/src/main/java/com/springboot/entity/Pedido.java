package com.springboot.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Insumo_Pedido> insumo_pedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maquina_id")
	private Maquina maquina;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
}