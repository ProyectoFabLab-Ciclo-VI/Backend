package com.springboot.controller;

import java.util.List;
import java.util.Optional;

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
		
		//Aca enviamos el email de confirmación
		String email = usuarioDTO.getPersona().getEmail();
		String subject = "Confirmación de correo";
		//Con la linea 51 y 52 podemos enviar enviar un email de en forma de texto
		//String message = "Hola " + usuarioDTO.getPersona().getNombre() + ", tu registro a la aplicación de Fablab ha sido exitosa !!";
		//usuarioService.sendEmail(email, subject, message);
		
		//Ahora implementaremos para enviar enviar une email con HTML
		String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
	            "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
	            "<head>" +
	            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />" +
	            "<meta name=\"x-apple-disable-message-reformatting\" />" +
	            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" +
	            "<meta name=\"color-scheme\" content=\"light dark\" />" +
	            "<meta name=\"supported-color-schemes\" content=\"light dark\" />" +
	            "<title></title>" +
	            "<style type=\"text/css\" rel=\"stylesheet\" media=\"all\">" +
	            "@import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");" +
	            "body { width: 100% !important; height: 100%; margin: 0; -webkit-text-size-adjust: none; }" +
	            "a { color: #3869D4; }" +
	            "td { word-break: break-word; }" +
	            "body, td, th { font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif; }" +
	            "h1 { margin-top: 0; color: #333333; font-size: 22px; font-weight: bold; text-align: left; }" +
	            "p, ul, ol, blockquote { margin: .4em 0 1.1875em; font-size: 16px; line-height: 1.625; }" +
	            "body { background-color: #F2F4F6; color: #51545E; }" +
	            ".email-wrapper { width: 100%; margin: 0; padding: 0; background-color: #F2F4F6; }" +
	            ".email-body { width: 100%; margin: 0; padding: 0; }" +
	            ".email-body_inner { width: 570px; margin: 0 auto; padding: 0; background-color: #FFFFFF; }" +
	            ".content-cell { padding: 45px; }" +
	            "</style>" +
	            "</head>" +
	            "<body>" +
	            "<table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">" +
	            "<tr><td align=\"center\">" +
	            "<table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">" +
	            "<tr><td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">" +
	            "<table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">" +
	            "<tr><td class=\"content-cell\">" +
	            "<div class=\"f-fallback\">" +
	            "<h1>¡Hola, " + usuarioDTO.getPersona().getNombre() + "!</h1>" +
	            "<p>Nos emociona darte la bienvenida a <strong>FabLab</strong>.</p>" +
	            "<p>Gracias por registrarte en nuestra aplicación. A partir de ahora, podrás gestionar fácilmente cualquier servicio que brindamos y disfrutar de una experiencia optimizada.</p>" +
	            "<p>Si tienes alguna duda o necesitas ayuda, no dudes en contactarnos. Estamos aquí para apoyarte en cada paso, puedes acercate a nuestra oficina o enviarnos un correo a: fablabproyecto@gmail.com</p>" +
	            "<p>¡Que disfrutes al máximo la aplicación!</p>" +
	            "<p>Saludos cordiales,<br>El equipo de FabLab</p>" +
	            "<p class=\"sub\">Visita también nuestra web: <a href=\"#\">https://www.fablabs.io/labs/fablabUPeULima</a></p>" +
	            "</div></td></tr></table></td></tr></table></td></tr></table>" +
	            "</body></html>";
		
		try {
			usuarioService.sendHtmlEmail(email, subject, message);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al enviar el correo" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
		boolean esValido = usuarioService.validarUsuario(usuarioDTO.getUsername(), usuarioDTO.getPassword());

		if (esValido) {
			return new ResponseEntity<>("Login exitoso!!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡¡¡ Credenciales incorrectas !!!", HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<Page<Usuario>> listarUsuario(@RequestParam  int page,
													   @RequestParam  int size) {
		
		Page<Usuario> listUsuario = usuarioService.getAllUsuarios(page, size);
		return new ResponseEntity<Page<Usuario>>(listUsuario, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable("id") int id, @RequestBody UsuarioDTO usuarioDTO) {
		//Usuario usuario = usuarioService.getOne(id).get();
		//usuario.setUsername(usuarioDTO.getUsername());
		//usuario.setPassword(usuarioDTO.getPassword());
		//Verificar si el usuario existe
		Optional<Usuario> usuarioOpt = usuarioService.getOne(id);
		//Si en caso el usuario no existe
		if (!usuarioOpt.isPresent()) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}
		Usuario usuario = usuarioOpt.get();
		usuario.setUsername(usuarioDTO.getUsername());
		usuario.setPassword(usuarioDTO.getPassword());

		// Para verificar si la persona existe:
		Persona personaExistente = usuario.getPersona();
		if (personaExistente != null) {
			personaExistente.setApellido(usuarioDTO.getPersona().getApellido());
			personaExistente.setCodigo(usuarioDTO.getPersona().getCodigo());
			personaExistente.setNombre(usuarioDTO.getPersona().getNombre());
			personaExistente.setFecha_nacimiento(usuarioDTO.getPersona().getFecha_nacimiento());
			personaExistente.setEmail(usuarioDTO.getPersona().getEmail());

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
