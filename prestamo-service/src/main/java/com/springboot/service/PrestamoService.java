package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Prestamo;
import com.springboot.repository.PrestamoRepository;

@Service
public class PrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	public Page<Prestamo> findAllPrestamos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return prestamoRepository.findAll(pageable);
	}
	
	public Optional<Prestamo> getOne (int id){
		return prestamoRepository.findById(id);
	}
	
	public void save (Prestamo prestamo) {
		prestamoRepository.save(prestamo);
	}
	
	public void delete(int id) {
		prestamoRepository.deleteById(id);
	}
}
