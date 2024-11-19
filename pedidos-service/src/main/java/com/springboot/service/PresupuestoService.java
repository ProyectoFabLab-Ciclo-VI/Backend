package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Presupuesto;
import com.springboot.repository.PresupuestoRepository;

@Service
public class PresupuestoService {

	@Autowired
	private PresupuestoRepository presupuestoRepository;
	
	public Page<Presupuesto> getAllPresupuestos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return presupuestoRepository.findAll(pageable);
	}
	//Para listar sin paginacion
	public List<Presupuesto> list(){
		return presupuestoRepository.findAll();
	}
	
	public Optional<Presupuesto> getOne (int id){
		return presupuestoRepository.findById(id);
	}
	
	public void save (Presupuesto presupuesto) {
		presupuestoRepository.save(presupuesto);
	}
	
	public void delete (int id) {
		presupuestoRepository.deleteById(id);
	}
}
