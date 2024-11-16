package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Integer>{

}
