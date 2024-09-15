package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.springboot.entity.Cargo;
import com.springboot.service.CargoService;

@RestController
@RequestMapping("/apicargo")
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Cargo>> listarCargo() {
		List<Cargo> listCargo = cargoService.list();
		return new ResponseEntity<List<Cargo>>(listCargo, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCargo(@PathVariable("id") int id, @RequestBody CargoDTO cargoDTO) {
		Cargo cargo = cargoService.getOne(id).get();
		cargo.setNombre(cargoDTO.getNombre());

		cargoService.save(cargo);
		return new ResponseEntity("Cargo Actualizado", HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> addCargo(@RequestBody CargoDTO cargoDTO){
		Cargo cargo = new Cargo(cargoDTO.getNombre());
		cargoService.save(cargo);
		return new ResponseEntity<>(cargo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCargo(@PathVariable ("id") int id){
		cargoService.delete(id);
		return new ResponseEntity("Cargo eliminado", HttpStatus.ACCEPTED);
	}
}
