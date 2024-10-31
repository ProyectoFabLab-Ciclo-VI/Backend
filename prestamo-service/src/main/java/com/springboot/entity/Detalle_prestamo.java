package com.springboot.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_prestamo")
public class Detalle_prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detalle_prestamo_id;
	private int cantidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "material_id")
	private Material material;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prestamo_id")
	private Prestamo prestamo;

	public Detalle_prestamo(int cantidad, Material material, Prestamo prestamo) {
		
		this.cantidad = cantidad;
		this.material = material;
		this.prestamo = prestamo;
	}
	
	
}
