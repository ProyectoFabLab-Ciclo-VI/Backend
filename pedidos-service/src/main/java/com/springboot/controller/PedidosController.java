package com.springboot.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dto.Insumo_PedidoDTO;
import com.springboot.dto.Modelo_predefinidoDTO;
import com.springboot.dto.PresupuestoDTO;
import com.springboot.dto.TarifarioDTO;
import com.springboot.entity.Insumo_Pedido;
import com.springboot.entity.Modelo_Predefinido;
import com.springboot.entity.Presupuesto;
import com.springboot.entity.Tarifario;
import com.springboot.service.Insumo_PedidoService;
import com.springboot.service.Modelo_predefinidoService;
import com.springboot.service.PresupuestoService;
import com.springboot.service.TarifarioService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping("/apipedidos")
public class PedidosController {

	@Autowired
	private Modelo_predefinidoService modelo_predefinidoService;
	
	@Autowired
	private Insumo_PedidoService insumo_PedidoService;
	
	@Autowired
	private PresupuestoService presupuestoService;
	
	@Autowired
	private TarifarioService tarifarioService;

	@Value("${file.upload-dir}")
	private String directorio;

	// CRUD Modelo_predefinidos
	@GetMapping("/list/modelo")
	public ResponseEntity<Page<Modelo_Predefinido>> getModelos(@RequestParam int page, @RequestParam int size) {
		Page<Modelo_Predefinido> listModelos = modelo_predefinidoService.getAllModelos(page, size);

		listModelos.forEach(modelo -> {
			String nombreImagen = modelo.getImagen(); // Asumiendo que este es el nombre de la imagen almacenada
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

	// Metodo para guardar la imagen
	private String guardarImagen(MultipartFile imagen) throws IOException {

		String nombreArchivo = imagen.getOriginalFilename();
		Path path = Paths.get(directorio);

		// Para asegurarnos que el directorio exista
		if (!Files.exists(path)) {
			Files.createDirectories(path); // Crea el directorio si no existe
		}

		// Concatenamos el nombre del archivo de manera segura
		Path archivoPath = path.resolve(nombreArchivo);
		Files.copy(imagen.getInputStream(), archivoPath, StandardCopyOption.REPLACE_EXISTING);

		return nombreArchivo; // retorna solo el nombre del archivo, no la ruta
	}

	// endpoint para servir la imagen a la web o app
	@GetMapping("/imagen-pedido/{nombreImagen:.+}")
	public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen) {
		try {
			// Ruta del directorio donde se almacena la imagen
			Path path = Paths.get(directorio).resolve(nombreImagen);
			Resource resource = new UrlResource(path.toUri());

			// Verificar si el recurso existe
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
					mediaType = MediaType.APPLICATION_OCTET_STREAM; // tipo generico
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
	public ResponseEntity<?> updateModelo(@PathVariable("id") int id,
			@ModelAttribute Modelo_predefinidoDTO modelo_predefinidoDTO) {
		try {
			// Verificamos si el modelo predefinido existe
			Optional<Modelo_Predefinido> optionalModelo = modelo_predefinidoService.getOne(id);
			if (!optionalModelo.isPresent()) {
				return new ResponseEntity<>("Modelo no encontrado", HttpStatus.NO_CONTENT);
			}
			// Obtenemos el modelo existente
			Modelo_Predefinido modelo_Predefinido = optionalModelo.get();
			//Actualizamos una imagen si se proporciona una nueva
			if (modelo_predefinidoDTO.getImagen() != null && !modelo_predefinidoDTO.getImagen().isEmpty()) {
				//Eliminamos la imagen anterior
				String antiguaImagen = modelo_Predefinido.getImagen();
				if (antiguaImagen != null && !antiguaImagen.isEmpty()) {
					Path antiguaImagenPath = Paths.get(directorio).resolve(antiguaImagen);
					Files.deleteIfExists(antiguaImagenPath);//Elimina la imagen anterior si existe
				}
				//Guarda la nueva imagen
				String nuevaImagenPath = guardarImagen(modelo_predefinidoDTO.getImagen());
				modelo_Predefinido.setImagen(nuevaImagenPath);
			}
			//Y actualizamos el modelo predefinido
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
	
	@DeleteMapping("delete/modelo/{id}")
	public ResponseEntity<?> deleteModelo (@PathVariable ("id") int id){
		modelo_predefinidoService.delete(id);
		return new ResponseEntity<> ("Modelo predefinido eliminado", HttpStatus.OK);
	}
	
	//CRUD Insumo_Pedido
	@GetMapping("/list/insumo-pedido")
	public ResponseEntity<Page<Insumo_Pedido>> getInsumoPedidos(@RequestParam int page,
																@RequestParam int size){
		Page<Insumo_Pedido> listInsumoPedidos = insumo_PedidoService.getAllInsumosPedidos(page, size);
		return new ResponseEntity<>(listInsumoPedidos, HttpStatus.OK);
	}
	
	@PostMapping("/add/insumo-pedido")
	public ResponseEntity<?> addInsumoPedido (@RequestBody Insumo_PedidoDTO insumo_PedidoDTO){
		Insumo_Pedido insumo_Pedido = new Insumo_Pedido(insumo_PedidoDTO.getCantidad_usada(),
														insumo_PedidoDTO.getPedido(),
														insumo_PedidoDTO.getInsumo());
		insumo_PedidoService.save(insumo_Pedido);
		return new ResponseEntity<>(insumo_Pedido, HttpStatus.CREATED);
	}
	@PutMapping("/update/insumo-pedido/{id}")
	public ResponseEntity<?> updateInsumoPedido (@PathVariable ("id") int id, @RequestBody Insumo_PedidoDTO insumo_PedidoDTO){
		Insumo_Pedido insumo_Pedido = insumo_PedidoService.getOne(id).get();
		insumo_Pedido.setCantidad_usada(insumo_PedidoDTO.getCantidad_usada());
		insumo_Pedido.setPedido(insumo_PedidoDTO.getPedido());
		insumo_Pedido.setInsumo(insumo_PedidoDTO.getInsumo());
		insumo_PedidoService.save(insumo_Pedido);
		return new ResponseEntity<>("Insumo - pedido actualizado", HttpStatus.OK);
	}
	
	//CRUD Presupuesto
	@GetMapping("/list/presupuesto")
	public ResponseEntity<Page<Presupuesto>> getPresupuestos (@RequestParam int page,
															  @RequestParam int size){
		Page<Presupuesto> listPresupuesto = presupuestoService.getAllPresupuestos(page, size);
		return new ResponseEntity<>(listPresupuesto, HttpStatus.OK);
	}
	
	@PostMapping("/add/presupuesto")
	public ResponseEntity<?> addPresupuesto (@RequestBody PresupuestoDTO presupuestoDTO){
		Presupuesto presupuesto = new Presupuesto(presupuestoDTO.getCantidad_empleada(),
												  presupuestoDTO.getMonto_mano_obra(),
												  presupuestoDTO.getPrecio_total(),
												  presupuestoDTO.getConfiguracion_cargo(),
												  presupuestoDTO.getConfiguracion_tiempo(),
												  presupuestoDTO.getTarifario());
		presupuestoService.save(presupuesto);
		return new ResponseEntity<>(presupuesto, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/presupuesto/{id}")
	public ResponseEntity<?> updatePresupuesto (@PathVariable ("id") int id, @RequestBody PresupuestoDTO presupuestoDTO){
		Presupuesto presupuesto = presupuestoService.getOne(id).get();
		presupuesto.setCantidad_empleada(presupuestoDTO.getCantidad_empleada());
		presupuesto.setMonto_mano_obra(presupuestoDTO.getMonto_mano_obra());
		presupuesto.setPrecio_total(presupuestoDTO.getPrecio_total());
		presupuesto.setConfiguracion_cargo(presupuestoDTO.getConfiguracion_cargo());
		presupuesto.setConfiguracion_tiempo(presupuestoDTO.getConfiguracion_tiempo());
		presupuesto.setTarifario(presupuestoDTO.getTarifario());
		presupuestoService.save(presupuesto);
		return new ResponseEntity<>("Presupuesto Actualizado", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/presupuesto/{id}")
	public ResponseEntity<?> deletePresupuesto (@PathVariable ("id") int id){
		presupuestoService.delete(id);
		return new ResponseEntity<>("Presupuesto elimnado" , HttpStatus.OK);
	}
	
	//CRUD Tarifario
	@GetMapping("/list/tarifario")
	public ResponseEntity<Page<Tarifario>> getTarifarios (@RequestParam int page,
														  @RequestParam int size){
		Page<Tarifario> listTarifario = tarifarioService.getAllTarifarios(page, size);
		return new ResponseEntity<>(listTarifario, HttpStatus.OK);
	}
	
	@PostMapping("/add/tarifario")
	public ResponseEntity<?> addTarifario  (@RequestBody TarifarioDTO tarifarioDTO){
		Tarifario tarifario = new Tarifario(tarifarioDTO.getPrecio_venta(),
											tarifarioDTO.getPorcentaje_desperdicio(),
											tarifarioDTO.getInsumo(),
											tarifarioDTO.getMaquina());
		tarifarioService.save(tarifario);
		return new ResponseEntity<>(tarifario, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/tarifario/{id}")
	public ResponseEntity<?> updateTarifario (@PathVariable ("id") int id, @RequestBody TarifarioDTO tarifarioDTO){
		Tarifario tarifario = tarifarioService.getOne(id).get();
		tarifario.setPrecio_venta(tarifarioDTO.getPrecio_venta());
		tarifario.setPorcentaje_desperdicio(tarifarioDTO.getPorcentaje_desperdicio());
		tarifario.setInsumo(tarifarioDTO.getInsumo());
		tarifario.setMaquina(tarifarioDTO.getMaquina());
		tarifarioService.save(tarifario);
		return new ResponseEntity<>("Tarifario actualizado", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/tarifario/{id}")
	public ResponseEntity<?> deleteTarifario (@PathVariable ("id") int id){
		tarifarioService.delete(id);
		return new ResponseEntity<>("Tarifario eliminado", HttpStatus.OK);
	}
}
