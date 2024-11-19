package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Configuracion_Tiempo;
import com.springboot.repository.Configuracion_TiempoRepository;

@Service
public class Configuracion_TiempoService {

	@Autowired
	private Configuracion_TiempoRepository configuracion_TiempoRepository;
	
	public Page<Configuracion_Tiempo> getAllConfiguracionTiempos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return configuracion_TiempoRepository.findAll(pageable);
	}
	
	//Para listar sin paginacion
	public List<Configuracion_Tiempo> list(){
		return configuracion_TiempoRepository.findAll();
	}
	
	public Optional<Configuracion_Tiempo> getOne (int id){
		return configuracion_TiempoRepository.findById(id);
	}
	
	public void save (Configuracion_Tiempo configuracion_Tiempo) {
		configuracion_TiempoRepository.save(configuracion_Tiempo);
	}
	
	public void delete (int id) {
		configuracion_TiempoRepository.deleteById(id);
	}
}
