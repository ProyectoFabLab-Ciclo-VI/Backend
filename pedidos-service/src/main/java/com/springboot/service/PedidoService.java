package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.entity.Pedido;
import com.springboot.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Page<Pedido> getAllPedidos (int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return pedidoRepository.findAll(pageable);
	}
	
	//Para listar sin paginacion
	public List<Pedido> list(){
		return pedidoRepository.findAll();
	}
	
	public Optional<Pedido> getOne (int id){
		return pedidoRepository.findById(id);
	}
	
	public void save (Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public void delete (int id) {
		pedidoRepository.deleteById(id);
	}
}
