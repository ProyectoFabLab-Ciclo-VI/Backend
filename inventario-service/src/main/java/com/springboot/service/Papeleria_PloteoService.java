package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Papeleria_Ploteo;
import com.springboot.repository.Papeleria_PloteoRepository;

@Service
public class Papeleria_PloteoService {

	@Autowired
	private Papeleria_PloteoRepository papeleria_PloteoRepository;
	
	public void delete (int id) {
		papeleria_PloteoRepository.deleteById(id);
	}
	
	//Para listar sin paginacion
	public List<Papeleria_Ploteo> list(){
		return papeleria_PloteoRepository.findAll();
	}
}
