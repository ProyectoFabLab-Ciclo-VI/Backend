package com.springboot.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dto.Modelo_predefinidoDTO;
import com.springboot.entity.Modelo_Predefinido;
import com.springboot.service.Modelo_predefinidoService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


@RestController
@RequestMapping("/apipedidos")
public class PedidosController {

	@Autowired
	private Modelo_predefinidoService modelo_predefinidoService;
	
	@Value("${file.upload-dir}")
	private String directorio;


	// CRUD Modelo_predefinidos
	@GetMapping("/list/modelo")
	public ResponseEntity<Page<Modelo_Predefinido>> getModelos(@RequestParam int page, @RequestParam int size) {
		Page<Modelo_Predefinido> listModelos = modelo_predefinidoService.getAllModelos(page, size);
		
		listModelos.forEach(modelo -> {
			String nombreImagen = modelo.getImagen(); //Asumiendo que este es el nombre de la imagen almacenada
			modelo.setImagen("/apipedidos/imagen-pedido/" + nombreImagen);
		});
		
		return new ResponseEntity<>(listModelos, HttpStatus.CREATED);
	}

	
	@PostMapping("/add/modelo")
	public ResponseEntity<?> addModelo(@ModelAttribute Modelo_predefinidoDTO modelo_predefinidoDTO) {
	    try {
	        // Guardar la imagen y obtener la ruta
	        String imagenPath = guardarImagen(modelo_predefinidoDTO.getImagen());

	        // Crear el modelo y asignar la ruta de la imagen
	        Modelo_Predefinido modelo_Predefinido = new Modelo_Predefinido();
	        modelo_Predefinido.setNombre(modelo_predefinidoDTO.getNombre());
	        modelo_Predefinido.setCodigo(modelo_predefinidoDTO.getCodigo());
	        modelo_Predefinido.setComentario(modelo_predefinidoDTO.getComentario());
	        modelo_Predefinido.setPrecio(modelo_predefinidoDTO.getPrecio());
	        modelo_Predefinido.setImagen(imagenPath); // Asignas la ruta o nombre del archivo

	        // Guardar el modelo en la base de datos
	        modelo_predefinidoService.save(modelo_Predefinido);

	        return new ResponseEntity<>(modelo_Predefinido, HttpStatus.CREATED);
	        
	    } catch (Exception e) {
	        return new ResponseEntity<>("ERROR AL CARGAR EL MODELO", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	//Metodo para guardar la imagen
	private String guardarImagen(MultipartFile imagen)throws IOException {
		
		String nombreArchivo = imagen.getOriginalFilename();
		Path path = Paths.get(directorio);
		
		//Para asegurarnos que el directorio exista
		if (!Files.exists(path)) {
			Files.createDirectories(path); //Crea el directorio si no existe
		}
		
		//Concatenamos el nombre del archivo de manera segura
		Path archivoPath = path.resolve(nombreArchivo);
		Files.copy(imagen.getInputStream(), archivoPath, StandardCopyOption.REPLACE_EXISTING);
				
		return nombreArchivo; //retorna solo el nombre del archivo, no la ruta
	}
	
	//endpoint para servir la imagen a la web o app
	@GetMapping("/imagen-pedido/{nombreImagen:.+}")
	public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen){
		try {
			//Ruta del directorio donde se almacena la imagen
			Path path = Paths.get(directorio).resolve(nombreImagen);
			Resource resource = new UrlResource(path.toUri());
			
			//Verificar si el recurso existe
			if (resource.exists() || resource.isReadable()) {
				MediaType mediaType;
				String extension = nombreImagen.substring(nombreImagen.lastIndexOf('.') + 1).toLowerCase();
				
				switch (extension) {
				case "jpg":
				case "jpeg":	
					mediaType = MediaType.IMAGE_JPEG;
					break;
				case "png":	
					mediaType = MediaType.IMAGE_PNG;
					break;
				default:
					mediaType = MediaType.APPLICATION_OCTET_STREAM; //tipo generico
					break;
				}				
				return ResponseEntity.ok().contentType(mediaType).body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
			
		} catch (MalformedURLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/update/modelo/{id}")
	public ResponseEntity<?> updateModelo (@PathVariable ("id") int id, @ModelAttribute Modelo_predefinidoDTO modelo_predefinidoDTO){
		try {
			//Buscaremos el modelo por su ID
			Modelo_Predefinido modelo_Predefinido = modelo_predefinidoService.getOne(id).get();
			if (!modelo_predefinidoDTO.getImagen().isEmpty()){
				String nuevaImagenPath = guardarImagen(modelo_predefinidoDTO.getImagen());
				modelo_Predefinido.setImagen(nuevaImagenPath);
			}
			modelo_Predefinido.setNombre(modelo_predefinidoDTO.getNombre());
			modelo_Predefinido.setCodigo(modelo_predefinidoDTO.getCodigo());
			modelo_Predefinido.setComentario(modelo_predefinidoDTO.getComentario());
			modelo_Predefinido.setPrecio(modelo_predefinidoDTO.getPrecio());
			modelo_predefinidoService.save(modelo_Predefinido);
			return new ResponseEntity("Modelo actualizado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("ERROR AL ACTUALIZAR EL MODELO", HttpStatus.BAD_REQUEST);
		}
	}
}
