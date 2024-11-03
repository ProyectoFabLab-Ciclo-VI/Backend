package com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CambioContrasenaDTO {

	private String codigoRecuperacion;
	private UsuarioDTO usuarioDTO;
}
