package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Estado_Maquina;
import com.springboot.repository.Estado_MaquinaRepository;

@Service
public class Estado_MaquinaService {

	@Autowired
	private Estado_MaquinaRepository estado_MaquinaRepository;
	
	//Para listar sin paginacion
	public List<Estado_Maquina> list(){
		return estado_MaquinaRepository.findAll();
	}
}
