package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer>{

	Page<Pago> findAll (Pageable pageable);
}
