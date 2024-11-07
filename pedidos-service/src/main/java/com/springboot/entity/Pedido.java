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
	private String fecha_pedido;
	private String fecha_validacion;
	private String comentario;
	private String estado;
	private String codigo;
	private double precio_venta;
	private String fecha_pago;
	
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modelo_predefinido_id")
	private Modelo_Predefinido modelo_predefinido;

	public Pedido(String url_modelo, String fecha_pedido, String fecha_validacion, String comentario, String estado,
			String codigo, double precio_venta, String fecha_pago, Maquina maquina, Persona persona,
			Modelo_Predefinido modelo_predefinido) {
		
		this.url_modelo = url_modelo;
		this.fecha_pedido = fecha_pedido;
		this.fecha_validacion = fecha_validacion;
		this.comentario = comentario;
		this.estado = estado;
		this.codigo = codigo;
		this.precio_venta = precio_venta;
		this.fecha_pago = fecha_pago;
		this.maquina = maquina;
		this.persona = persona;
		this.modelo_predefinido = modelo_predefinido;
	}
	
	
}
