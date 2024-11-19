package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Insumo;
import com.springboot.repository.InsumoRepository;

@Service
public class InsumoService {

	@Autowired
	InsumoRepository insumoRepository;
	
	//Con esto listamos con paginación
	public Page<Insumo> getAllInsumos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return insumoRepository.findAll(pageable);
	}
	
	//Listar sin paginacion
	public List<Insumo> list(){
		return insumoRepository.findAll();
	}
	
	public Optional<Insumo> getOne (int id){
		return insumoRepository.findById(id);
	}
	
	public void save (Insumo insumo) {
		insumoRepository.save(insumo);
	}
	
	public void delete (int id) {
		insumoRepository.deleteById(id);
	}
}
