package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Insumo_Pedido;

public interface Insumo_PedidoRepository extends JpaRepository<Insumo_Pedido, Integer>{

	Page<Insumo_Pedido> findAll(Pageable pageable);
}
