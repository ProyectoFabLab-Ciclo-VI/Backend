package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.springboot.dto.Configuracion_CargoDTO;
import com.springboot.dto.Configuracion_TiempoDTO;
import com.springboot.entity.Configuracion_Cargo;
import com.springboot.entity.Configuracion_Tiempo;
import com.springboot.service.Configuracion_CargoService;
import com.springboot.service.Configuracion_TiempoService;

@RestController
@RequestMapping("/apiconfiguracion")
public class ConfiguracionesController {

	@Autowired
	private Configuracion_TiempoService configuracion_TiempoService;
	
	@Autowired
	private Configuracion_CargoService configuracion_CargoService;
	
	//CRUD Configuracion_Tiempo
	//Listar con paginacion
	@GetMapping("/list/configuracion-tiempo")
	public ResponseEntity<Page<Configuracion_Tiempo>> getConfiguracionTiempo (@RequestParam int page,
																			  @RequestParam int size){
		Page<Configuracion_Tiempo> listConfiguracionTiempo = configuracion_TiempoService.getAllConfiguracionTiempos(page, size);
		return new ResponseEntity<>(listConfiguracionTiempo, HttpStatus.OK);
	}
	
	//Para listar sin paginacion
	@GetMapping("/list-all/configuracion-tiempo")
	public ResponseEntity<List<Configuracion_Tiempo>> findAllConfiguracionTiempo(){
		List<Configuracion_Tiempo> listConfiguracionTiempo = configuracion_TiempoService.list();
		return new ResponseEntity<List<Configuracion_Tiempo>>(listConfiguracionTiempo, HttpStatus.OK);
	}
	
	@PostMapping("/add/configuracion-tiempo")
	public ResponseEntity<?> addConfiguracionTiempo (@RequestBody Configuracion_TiempoDTO configuracion_TiempoDTO){
		Configuracion_Tiempo configuracion_Tiempo = new Configuracion_Tiempo(configuracion_TiempoDTO.getPrecioxminuto());
		configuracion_TiempoService.save(configuracion_Tiempo);
		return new ResponseEntity<>(configuracion_Tiempo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/configuracion-tiempo/{id}")
	public ResponseEntity<?> updateConfiguracionTiempo (@PathVariable ("id") int id, @RequestBody Configuracion_TiempoDTO configuracion_TiempoDTO){
		Configuracion_Tiempo configuracion_Tiempo = configuracion_TiempoService.getOne(id).get();
		configuracion_Tiempo.setPrecioxminuto(configuracion_TiempoDTO.getPrecioxminuto());
		configuracion_TiempoService.save(configuracion_Tiempo);
		return new ResponseEntity<>("Configuración de Tiempo actualizado", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/configuracion-tiempo/{id}")
	public ResponseEntity<?> deleteConfiguracionTiempo (@PathVariable ("id") int id){
		configuracion_TiempoService.delete(id);
		return new ResponseEntity<>("Configuración de Tiempo eliminado", HttpStatus.OK);
	}
	
	//CRUD Configuracion_Cargo
	//Listar con paginacion
	@GetMapping("/list/configuracion-cargo")
	public ResponseEntity<Page<Configuracion_Cargo>> getConfiguracionCargo (@RequestParam int page,
																			@RequestParam int size){
		Page<Configuracion_Cargo> listConfiguracionCargo = configuracion_CargoService.getAllConfiguracionCargos(page, size);
		return new ResponseEntity<>(listConfiguracionCargo, HttpStatus.OK);
	}
	//Listar sin paginacion
	@GetMapping("/list-all/configuracion-cargo")
	public ResponseEntity<List<Configuracion_Cargo>> findAllConfiguracionCargos(){
		List<Configuracion_Cargo> listConfiguracionCargos = configuracion_CargoService.list();
		return new ResponseEntity<List<Configuracion_Cargo>>(listConfiguracionCargos, HttpStatus.OK);
	}
	
	@PostMapping("/add/configuracion-cargo")
	public ResponseEntity<?> addConfiguracionCargo (@RequestBody Configuracion_CargoDTO configuracion_CargoDTO){
		Configuracion_Cargo configuracion_Cargo = new Configuracion_Cargo(configuracion_CargoDTO.getIgv(),
																		  configuracion_CargoDTO.getMano_obra(),
																		  configuracion_CargoDTO.getCargo());
		configuracion_CargoService.save(configuracion_Cargo);
		return new ResponseEntity<>(configuracion_Cargo, HttpStatus.CREATED);
	}
	/*
	@PutMapping("/update/configuracion-cargo/{id}")
	public ResponseEntity<?> updateConfiguracionCargo (@PathVariable ("id") int id, @RequestBody Configuracion_CargoDTO configuracion_CargoDTO){
		Configuracion_Cargo configuracion_Cargo = configuracion_CargoService.getOne(id).get();
		configuracion_Cargo.setIgv(configuracion_CargoDTO.getIgv());
		configuracion_Cargo.setMano_obra(configuracion_CargoDTO.getMano_obra());
		configuracion_Cargo.setCargo(configuracion_CargoDTO.getCargo());
		configuracion_CargoService.save(configuracion_Cargo);
		return new ResponseEntity<>("Configuración de cargo actualizado", HttpStatus.OK);
	}*/
	@PutMapping("/update/configuracion-cargo")
	public ResponseEntity<?> updateConfiguracionCargo(@RequestBody List<Configuracion_Cargo> cofiguraciones) {
		for (Configuracion_Cargo configuracion_Cargo : cofiguraciones) {
			configuracion_CargoService.save(configuracion_Cargo);
		}
		
		return new ResponseEntity<>("Configuración de la lista de cargo actualizado", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/configuracion-cargo/{id}")
	public ResponseEntity<?> deleteConfiguracionCargo (@PathVariable ("id") int id){
		configuracion_CargoService.delete(id);
		return new ResponseEntity<>("Configuración de cargo eliminado", HttpStatus.OK);
	}
}
