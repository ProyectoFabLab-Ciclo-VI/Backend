package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Integer>{

	Page<Insumo> findAll (Pageable pageable);
}
