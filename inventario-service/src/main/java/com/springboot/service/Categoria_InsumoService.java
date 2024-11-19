package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Categoria_Insumo;
import com.springboot.repository.Categoria_InsumoRepository;

@Service
public class Categoria_InsumoService {

	@Autowired
	private Categoria_InsumoRepository categoria_InsumoRepository;
	
	//Para listar sin paginacion
	public List<Categoria_Insumo> list(){
		return categoria_InsumoRepository.findAll();
	}
}
