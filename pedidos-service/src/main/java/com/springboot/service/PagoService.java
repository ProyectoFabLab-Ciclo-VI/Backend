package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Pago;
import com.springboot.repository.PagoRepository;

@Service
public class PagoService {

	@Autowired
	private PagoRepository pagoRepository;
	
	//Para lista con paginacion
	public Page<Pago> getAllPagos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return pagoRepository.findAll(pageable);
	}
	
	//Para listar sin paginacion
	public List<Pago> listAllPagos(){
		return pagoRepository.findAll();
	}
	
	public Optional<Pago> getOne (int id){
		return pagoRepository.findById(id);
	}
	
	public void save (Pago pago) {
		pagoRepository.save(pago);
	}
	
	public void delete (int id) {
		pagoRepository.deleteById(id);
	}
}
