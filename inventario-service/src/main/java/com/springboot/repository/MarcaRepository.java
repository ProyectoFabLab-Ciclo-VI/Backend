package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer>{

}
