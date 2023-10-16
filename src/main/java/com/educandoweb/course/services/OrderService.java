package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

/*O @Service registra um serviço na camada de serviço e, dessa forma, permite que o objeto seja
registrado no mecanismo de injeção de dependência e injetado no OrderRepository*/
@Service
public class OrderService {
	
	/*Estabelece que a classe OrderService possui uma dependência com OrderRepository e faz a injeção de dependência
	 de forma transparente*/
	@Autowired
	private OrderRepository repository;
	
	/*Método que retorna todos os pedidos do banco de dados*/
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	/*Método que retorna o pedido do banco de dados com base no id*/
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}

}
