package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{

}
