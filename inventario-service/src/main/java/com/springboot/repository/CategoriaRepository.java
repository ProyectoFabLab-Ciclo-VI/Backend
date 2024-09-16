package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
