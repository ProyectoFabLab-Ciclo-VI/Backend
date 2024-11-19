package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Mantenimiento;
import com.springboot.repository.MantenimientoRepository;

@Service
public class MantenimientoService {

	@Autowired
	private MantenimientoRepository mantenimientoRepository;
	
	public Page<Mantenimiento> getAllMantenimientos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return mantenimientoRepository.findAll(pageable);
	}
	//Para listar sin paginacion
	public List<Mantenimiento> list(){
		return mantenimientoRepository.findAll();
	}
	
	public Optional<Mantenimiento> getOne (int id){
		return mantenimientoRepository.findById(id);
	}
	
	public void save (Mantenimiento mantenimiento) {
		mantenimientoRepository.save(mantenimiento);
	}
	
	public void delete (int id) {
		mantenimientoRepository.deleteById(id);
	}
}
