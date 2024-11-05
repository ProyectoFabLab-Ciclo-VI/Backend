package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Mantenimiento;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer>{

	Page<Mantenimiento> findAll (Pageable pageable);
}
