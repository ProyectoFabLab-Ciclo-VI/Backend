package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Maquina;

public interface MaquinaRepository extends JpaRepository<Maquina, Integer>{

	Page<Maquina> findAll(Pageable pageable);
}
