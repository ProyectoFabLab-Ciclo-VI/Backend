package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

	Page<Prestamo> findAll (Pageable pageable);
}
