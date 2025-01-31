package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Seguimiento_Insumo;
import com.springboot.repository.Seguimiento_InsumoRepository;

@Service
public class Seguimiento_InsumoService {

	@Autowired
	private Seguimiento_InsumoRepository seguimiento_InsumoRepository;
	
	//Listar por paginacion
	public Page<Seguimiento_Insumo> getAllSeguimientoInsumos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return seguimiento_InsumoRepository.findAll(pageable);
	}
	
	//Listar sin paginacion
	public List<Seguimiento_Insumo> list (){
		return seguimiento_InsumoRepository.findAll();
	}
	
	public Optional<Seguimiento_Insumo> getOne (int id){
		return seguimiento_InsumoRepository.findById(id);
	}
	
	public void save (Seguimiento_Insumo seguimiento_Insumo) {
		seguimiento_InsumoRepository.save(seguimiento_Insumo);
	}
	
	public void delete (int id) {
		seguimiento_InsumoRepository.deleteById(id);
	}
}
