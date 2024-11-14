package com.springboot.dto;



import com.springboot.entity.Categoria_Insumo;
import com.springboot.entity.Estado_Maquina;
import com.springboot.entity.Insumo;
import com.springboot.entity.Maquina_Impresiones3D;
import com.springboot.entity.Papeleria_Ploteo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class MaquinaDTO {

	private String nombre;
	private String codigo_upeu;
	private double coste_maquina;
	private double coste_amortizacion;
	private Boolean activo;
	
	private Categoria_Insumo categoria_insumo;
	private Estado_Maquina estado_maquina;
	private Insumo insumo;
	private Maquina_Impresiones3D maquina_impresiones3d;
	private Papeleria_Ploteo papeleria_ploteo;
	
}
