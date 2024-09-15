package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Persona;
import com.springboot.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	
	public List<Persona> list(){
		return personaRepository.findAll();
	}
	
	public Optional<Persona> getOne(int id){
		return personaRepository.findById(id);
	}
	
	public void save(Persona persona) {
		personaRepository.save(persona);
	}
	
	public void delete(int id) {
		personaRepository.deleteById(id);
	}
}
