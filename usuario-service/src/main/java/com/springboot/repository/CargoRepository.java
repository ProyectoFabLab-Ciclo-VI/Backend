package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{

}
