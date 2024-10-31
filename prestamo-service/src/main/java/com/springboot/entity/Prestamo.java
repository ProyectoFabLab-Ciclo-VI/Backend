package com.springboot.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prestamo")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prestamo_id;
	private String codigo;
	private String motivo;
	private Date fecha_prestamo;
	private Date fecha_devolucion;
	
	@OneToMany(mappedBy = "prestamo")
	@JsonIgnore
	private List<Detalle_prestamo> detalle_prestamo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "persona_id")
	private Persona persona;

	public Prestamo(String codigo, String motivo, Date fecha_prestamo, Date fecha_devolucion, Persona persona) {
		this.codigo = codigo;
		this.motivo = motivo;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.persona = persona;
	}
	
	
	
}
