package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Seguimiento_Insumo;

public interface Seguimiento_InsumoRepository extends JpaRepository<Seguimiento_Insumo, Integer>{

	Page<Seguimiento_Insumo> findAll (Pageable pageable);
}
