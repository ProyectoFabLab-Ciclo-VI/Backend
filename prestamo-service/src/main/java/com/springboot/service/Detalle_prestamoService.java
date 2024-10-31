package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Detalle_prestamo;
import com.springboot.repository.Detalle_prestamoRepository;

@Service
public class Detalle_prestamoService {

	@Autowired
	private Detalle_prestamoRepository detalle_prestamoRepository;
	
	public Page<Detalle_prestamo> getAllDetalles(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return detalle_prestamoRepository.findAll(pageable);
	}
	
	public Optional<Detalle_prestamo> getOne (int id){
		return detalle_prestamoRepository.findById(id);
	}
	
	public void save (Detalle_prestamo detalle_prestamo) {
		detalle_prestamoRepository.save(detalle_prestamo);
	}
	
	public void delete (int id) {
		detalle_prestamoRepository.deleteById(id);
	}
}
