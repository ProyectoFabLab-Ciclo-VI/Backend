package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Tarifario;
import com.springboot.repository.TarifarioRepository;

@Service
public class TarifarioService {

	@Autowired
	private TarifarioRepository tarifarioRepository;
	
	public Page<Tarifario> getAllTarifarios (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return tarifarioRepository.findAll(pageable);
	}
	
	public Optional<Tarifario> getOne (int id){
		return tarifarioRepository.findById(id);
	}
	
	public void save (Tarifario tarifario) {
		tarifarioRepository.save(tarifario);
	}
	
	public void delete (int id) {
		tarifarioRepository.deleteById(id);
	}
}
