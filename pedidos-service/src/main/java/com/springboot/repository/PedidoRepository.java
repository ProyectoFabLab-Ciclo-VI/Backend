package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Page<Pedido> findAll (Pageable pageable);
}
