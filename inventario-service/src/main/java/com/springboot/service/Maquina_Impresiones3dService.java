package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.repository.Maquina_Impresiones3dRepository;

@Service
public class Maquina_Impresiones3dService {

	@Autowired
	private Maquina_Impresiones3dRepository maquina_Impresiones3dRepository;
	
	public void delete (int id) {
		maquina_Impresiones3dRepository.deleteById(id);
	}
}
