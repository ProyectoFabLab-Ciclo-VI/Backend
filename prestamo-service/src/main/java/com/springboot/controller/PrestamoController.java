package com.springboot.controller;

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

import com.springboot.dto.Detalle_prestamoDTO;
import com.springboot.dto.PrestamoDTO;
import com.springboot.entity.Detalle_prestamo;
import com.springboot.entity.Prestamo;
import com.springboot.service.Detalle_prestamoService;
import com.springboot.service.PrestamoService;

@RestController
@RequestMapping("/apiprestamo")
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private Detalle_prestamoService detalle_prestamoService;
	
	//CRUD Prestamo
	@GetMapping("/list/prestamo")
	public ResponseEntity<Page<Prestamo>> getPrestamos (@RequestParam int page,
														@RequestParam int size){
		Page<Prestamo> listPrestamos = prestamoService.findAllPrestamos(page, size);
		return new ResponseEntity<>(listPrestamos, HttpStatus.OK);
	}
	
	@PostMapping("/add/prestamo")
	public ResponseEntity<?> addPrestamos (@RequestBody PrestamoDTO prestamoDTO){
		Prestamo prestamo = new Prestamo(prestamoDTO.getCodigo(),
										 prestamoDTO.getMotivo(),
										 prestamoDTO.getFecha_prestamo(),
										 prestamoDTO.getFecha_devolucion(),
										 prestamoDTO.getPersona());
		prestamoService.save(prestamo);
		return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/prestamo/{id}")
	public ResponseEntity<?> updatePrestamo (@PathVariable ("id") int id, @RequestBody PrestamoDTO prestamoDTO){
		Prestamo prestamo = prestamoService.getOne(id).get();
		prestamo.setCodigo(prestamoDTO.getCodigo());
		prestamo.setMotivo(prestamoDTO.getMotivo());
		prestamo.setFecha_prestamo(prestamoDTO.getFecha_prestamo());
		prestamo.setFecha_devolucion(prestamoDTO.getFecha_devolucion());
		prestamo.setPersona(prestamoDTO.getPersona());
		prestamoService.save(prestamo);
		return new ResponseEntity<>("Préstamo actualizado", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/prestamo/{id}")
	public ResponseEntity<?> deletePrestamo (@PathVariable ("id") int id){
		prestamoService.delete(id);
		return new ResponseEntity<>("Préstamo eliminado", HttpStatus.ACCEPTED);
	}
	
	//CRUD Detalle_prestamo
	@GetMapping("/list/detalle")
	public ResponseEntity<Page<Detalle_prestamo>> getDetalles (@RequestParam int page,
															   @RequestParam int size){
		Page<Detalle_prestamo> listDetalles = detalle_prestamoService.getAllDetalles(page, size);
		return new ResponseEntity<>(listDetalles, HttpStatus.OK);
	}
	
	@PostMapping("/add/detalle")
	public ResponseEntity<?> addDetalles (@RequestBody Detalle_prestamoDTO detalle_prestamoDTO){
		Detalle_prestamo detalle_prestamo = new Detalle_prestamo(detalle_prestamoDTO.getCantidad(),
																 detalle_prestamoDTO.getMaterial(),
																 detalle_prestamoDTO.getPrestamo());
		detalle_prestamoService.save(detalle_prestamo);
		return new ResponseEntity<>(detalle_prestamo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/detalle/{id}")
	public ResponseEntity<?> updateDetalles (@PathVariable ("id") int id, @RequestBody Detalle_prestamoDTO detalle_prestamoDTO){
		Detalle_prestamo detalle_prestamo = detalle_prestamoService.getOne(id).get();
		detalle_prestamo.setCantidad(detalle_prestamoDTO.getCantidad());
		detalle_prestamo.setMaterial(detalle_prestamoDTO.getMaterial());
		detalle_prestamo.setPrestamo(detalle_prestamoDTO.getPrestamo());
		detalle_prestamoService.save(detalle_prestamo);
		return new ResponseEntity<>("Detalles actualizacos", HttpStatus.OK);
}
	
	@DeleteMapping("/delete/detalle/{id}")
	public ResponseEntity<?> deleteDetalles (@PathVariable ("id") int id){
		detalle_prestamoService.delete(id);
		return new ResponseEntity<>("Detalle eliminado", HttpStatus.ACCEPTED);
	}
}
