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

import com.springboot.dto.InsumoDTO;
import com.springboot.dto.MaquinaDTO;
import com.springboot.dto.MaterialDTO;
import com.springboot.entity.Insumo;
import com.springboot.entity.Maquina;
import com.springboot.entity.Material;
import com.springboot.entity.Tipo_Maquina;
import com.springboot.repository.Tipo_MaquinaRepository;
import com.springboot.service.InsumoService;
import com.springboot.service.MaquinaService;
import com.springboot.service.MaterialService;

@RestController
@RequestMapping("/apiinventario")
public class InventarioController {

	@Autowired
	private MaquinaService maquinaService;
	
	@Autowired
	private Tipo_MaquinaRepository tipo_MaquinaRepository;
	
	@Autowired
	private InsumoService insumoService;
	
	@Autowired
	private MaterialService materialService;
	
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
									  maquinaDTO.getCodigo_upeu(),
									  maquinaDTO.getEstado(),
									  maquinaDTO.getActivo(),
									  maquinaDTO.getTipo_maquina());
		maquinaService.save(maquina);
		return new ResponseEntity<>(maquina, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/maquina/{id}")
	public ResponseEntity<?> updateMaquina(@PathVariable ("id") int id, @RequestBody MaquinaDTO maquinaDTO){
		Maquina maquina = maquinaService.getOne(id).get();
		maquina.setNombre(maquinaDTO.getNombre());
		maquina.setCodigo_upeu(maquinaDTO.getCodigo_upeu());
		maquina.setEstado(maquinaDTO.getEstado());
		maquina.setActivo(maquinaDTO.getActivo());
		maquina.setTipo_maquina(maquinaDTO.getTipo_maquina());
		maquinaService.save(maquina);
		return new ResponseEntity<>("Máquina actualizada", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/maquina/{id}")
	public ResponseEntity<?> deleteMaquina(@PathVariable ("id") int id){
		maquinaService.delete(id);
		return new ResponseEntity<>("Máquina Eliminada", HttpStatus.ACCEPTED);
	}
	
	//Insumo CRUD
	@GetMapping("/list/insumo")
	public ResponseEntity<Page<Insumo>> getInsumos (@RequestParam int page,
													  @RequestParam int size){
		Page<Insumo> listInsumo = insumoService.getAllInsumos(page, size);
		return new ResponseEntity<>(listInsumo, HttpStatus.OK);
	}
	
	@PostMapping("/add/insumo")
	public ResponseEntity<?> addInsumo(@RequestBody InsumoDTO insumoDTO){
		Insumo insumo = new Insumo(insumoDTO.getNombre(),
								   insumoDTO.getDescripcion(),
								   insumoDTO.getUnidad_medida(),
								   insumoDTO.getActivo(),
								   insumoDTO.getMarca());
		insumoService.save(insumo);
		return new ResponseEntity<>(insumo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/insumo/{id}")
	public ResponseEntity<?> updateInsumo(@PathVariable ("id") int id, @RequestBody InsumoDTO insumoDTO){
		Insumo insumo = insumoService.getOne(id).get();
		insumo.setNombre(insumoDTO.getNombre());
		insumo.setDescripcion(insumoDTO.getDescripcion());
		insumo.setUnidad_medida(insumoDTO.getUnidad_medida());
		insumo.setActivo(insumoDTO.getActivo());
		insumo.setMarca(insumoDTO.getMarca());
		insumoService.save(insumo);
		return new ResponseEntity<>("Insumo actualizado", HttpStatus.OK);		
	}
	
	@DeleteMapping("/delete/insumo/{id}")
	public ResponseEntity<?> deleteInsumo(@PathVariable ("id") int id){
		insumoService.delete(id);
		return new ResponseEntity<>("Insumo Eliminado", HttpStatus.ACCEPTED);
	}
	
	//CRUD Material
	@GetMapping("/list/material")
	public ResponseEntity<Page<Material>> getMateriales (@RequestParam int page,
													  	 @RequestParam int size){
		Page<Material> listMaterial = materialService.getAllMateriales(page, size);
		return new ResponseEntity<>(listMaterial, HttpStatus.OK);
	}
	
	@PostMapping("/add/material")
	public ResponseEntity<?> addMaterial(@RequestBody MaterialDTO materialDTO){
		Material material = new Material(materialDTO.getCantidad(),
										 materialDTO.getNombre(),
										 materialDTO.getCodigo_upeu(),
										 materialDTO.getActivo());
		materialService.save(material);
		return new ResponseEntity<>(material, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/material/{id}")
	public ResponseEntity<?> updateMaterial(@PathVariable ("id") int id, @RequestBody MaterialDTO materialDTO){
		Material material = materialService.getOne(id).get();
		material.setCantidad(materialDTO.getCantidad());
		material.setNombre(materialDTO.getNombre());
		material.setCodigo_upeu(materialDTO.getCodigo_upeu());
		material.setActivo(materialDTO.getActivo());
		
		materialService.save(material);
		return new ResponseEntity<>("Material actualizado", HttpStatus.OK);		
	}
	
	@DeleteMapping("/delete/material/{id}")
	public ResponseEntity<?> deleteMaterial (@PathVariable ("id") int id){
		materialService.delete(id);
		return new ResponseEntity<>("Material eliminado", HttpStatus.ACCEPTED);
	}
	
}
