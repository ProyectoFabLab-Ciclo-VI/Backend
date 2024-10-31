package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Detalle_prestamo;

public interface Detalle_prestamoRepository extends JpaRepository<Detalle_prestamo, Integer>{

	Page<Detalle_prestamo> findAll (Pageable pageable);
}
