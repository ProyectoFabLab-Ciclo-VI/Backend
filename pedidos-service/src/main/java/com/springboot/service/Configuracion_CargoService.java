package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Configuracion_Cargo;
import com.springboot.repository.Configuracion_CargoRepository;

@Service
public class Configuracion_CargoService {

	@Autowired
	private Configuracion_CargoRepository configuracion_CargoRepository;
	
	public Page<Configuracion_Cargo> getAllConfiguracionCargos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return configuracion_CargoRepository.findAll(pageable);
	}
	
	//Para listar sin paginacion
	public List<Configuracion_Cargo> list(){
		return configuracion_CargoRepository.findAll();
	}
	
	public Optional<Configuracion_Cargo> getOne (int id){
		return configuracion_CargoRepository.findById(id);
	}
	
	public void save (Configuracion_Cargo configuracion_Cargo) {
		configuracion_CargoRepository.save(configuracion_Cargo);
	}
	
	public void delete (int id) {
		configuracion_CargoRepository.deleteById(id);
	}
}
