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

import com.springboot.dto.MaquinaDTO;
import com.springboot.entity.Maquina;
import com.springboot.service.MaquinaService;

@RestController
@RequestMapping("/apiinventario")
public class InventarioController {

	@Autowired
	private MaquinaService maquinaService;
	
	//Maquina CRUD
	@GetMapping("/list/maquina")
	public ResponseEntity<Page<Maquina>> getMaquinas (@RequestParam int page,
													  @RequestParam int size){
		Page<Maquina> listMaquina = maquinaService.getAllMaquina(page, size);
		return new ResponseEntity<>(listMaquina, HttpStatus.OK);
	}
	
	@PostMapping("/add/maquina")
	public ResponseEntity<?> addMaquina(@RequestBody MaquinaDTO maquinaDTO){
		Maquina maquina = new Maquina(maquinaDTO.getNombre(),
				                      maquinaDTO.getCodigo_maquina(),
				                      maquinaDTO.getFecha_compra(),
				                      maquinaDTO.getPrecio(),
				                      maquinaDTO.getCodigo_upeu(),
				                      maquinaDTO.getEstado(),
				                      maquinaDTO.getPorc_desperdicio(),
				                      maquinaDTO.getTipo_cotizacion(),
				                      maquinaDTO.getCategoria());
		maquinaService.save(maquina);
		return new ResponseEntity(maquina, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/maquina/{id}")
	public ResponseEntity<?> updateMaquina(@PathVariable ("id") int id, @RequestBody MaquinaDTO maquinaDTO){
		Maquina maquina = maquinaService.getOne(id).get();
		maquina.setNombre(maquinaDTO.getNombre());
		maquina.setCodigo_maquina(maquinaDTO.getCodigo_maquina());
		maquina.setFecha_compra(maquinaDTO.getFecha_compra());
		maquina.setPrecio(maquinaDTO.getPrecio());
		maquina.setCodigo_upeu(maquinaDTO.getCodigo_upeu());
		maquina.setEstado(maquinaDTO.getEstado());
		maquina.setPorc_desperdicio(maquinaDTO.getPorc_desperdicio());
		maquina.setTipo_cotizacion(maquinaDTO.getTipo_cotizacion());
		maquina.setCategoria(maquinaDTO.getCategoria());
		maquinaService.save(maquina);
		return new ResponseEntity("Máquina actualizada", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/maquina/{id}")
	public ResponseEntity<?> deleteMaquina(@PathVariable ("id") int id){
		maquinaService.delete(id);
		return new ResponseEntity("Máquina Eliminada", HttpStatus.ACCEPTED);
	}
}
