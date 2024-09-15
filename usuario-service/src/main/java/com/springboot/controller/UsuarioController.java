package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.CargoDTO;
import com.springboot.dto.UsuarioDTO;
import com.springboot.entity.Cargo;
import com.springboot.entity.Persona;
import com.springboot.entity.Usuario;
import com.springboot.service.CargoService;
import com.springboot.service.PersonaService;
import com.springboot.service.UsuarioService;

@RestController
@RequestMapping("/apiusuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private CargoService cargoService;

	@PostMapping("/registrar")
	public ResponseEntity<?> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.getUsername(), usuarioDTO.getPassword(), usuarioDTO.getPersona());
		usuarioService.save(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
		boolean esValido = usuarioService.validarUsuario(usuarioDTO.getUsername(), usuarioDTO.getPassword());

		if (esValido) {
			return new ResponseEntity<>("Login exitoso, entra gaaa", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Credenciales incorrectas, piensa pe chato", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<Page<Usuario>> listarUsuario(@RequestParam (defaultValue = "0") int page,
													   @RequestParam (defaultValue = "5") int size) {
		
		Page<Usuario> listUsuario = usuarioService.getAllUsuarios(page, size);
		return new ResponseEntity<Page<Usuario>>(listUsuario, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable("id") int id, @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioService.getOne(id).get();
		usuario.setUsername(usuarioDTO.getUsername());
		usuario.setPassword(usuarioDTO.getPassword());

		// Para verificar si la persona existe:
		Persona personaExistente = usuario.getPersona();
		if (personaExistente != null) {
			personaExistente.setApellido(usuarioDTO.getPersona().getApellido());
			personaExistente.setCodigo(usuarioDTO.getPersona().getCodigo());
			personaExistente.setNombre(usuarioDTO.getPersona().getNombre());
			personaExistente.setFecha_nacimiento(usuarioDTO.getPersona().getFecha_nacimiento());

			// Y aca asignamos el cargo tambien
			if (usuarioDTO.getPersona().getCargo() != null) {
				Cargo cargo = cargoService.getOne(usuarioDTO.getPersona().getCargo().getCargo_id())
						.orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
				personaExistente.setCargo(cargo);
			}
		}

		usuarioService.save(usuario);
		return new ResponseEntity("Usuario Actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id) {
		
		Usuario usuario = usuarioService.getOne(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		usuarioService.delete(id);
		
		//Ahora esto es para eliminar a la persona asociada
		if (usuario.getPersona() != null) {
			personaService.delete(usuario.getPersona().getPersona_id());
		}
		
		return new ResponseEntity("Usuario y Persona Eliminada", HttpStatus.ACCEPTED);
	}

	

	

	
}
