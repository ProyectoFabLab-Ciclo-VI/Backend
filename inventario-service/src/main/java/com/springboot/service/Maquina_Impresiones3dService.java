package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Maquina_Impresiones3D;
import com.springboot.repository.Maquina_Impresiones3dRepository;

@Service
public class Maquina_Impresiones3dService {

	@Autowired
	private Maquina_Impresiones3dRepository maquina_Impresiones3dRepository;
	
	public void delete (int id) {
		maquina_Impresiones3dRepository.deleteById(id);
	}
	//Para listar sin paginacion
	public List<Maquina_Impresiones3D> list(){
		return maquina_Impresiones3dRepository.findAll();
	}
}
