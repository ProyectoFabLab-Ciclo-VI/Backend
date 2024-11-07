package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Tarifario;

public interface TarifarioRepository extends JpaRepository< Tarifario, Integer>{

	Page<Tarifario> findAll (Pageable pageable);
}
