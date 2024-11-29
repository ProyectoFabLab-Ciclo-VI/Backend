package com.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Modelo_Predefinido;

public interface Modelo_predefinidoRepository extends JpaRepository<Modelo_Predefinido, Integer>{

	Page<Modelo_Predefinido> findAll (Pageable pageble);
	
	List<Modelo_Predefinido> findByEstado (String estado);
}
