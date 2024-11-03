package com.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByUsername(String username);
	
	Page<Usuario> findAll(Pageable pageable);
	
	Usuario findByPersonaEmail(String email);
}
