package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

/*O @Service registra um serviço na camada de serviço e, dessa forma, permite que o objeto seja
registrado no mecanismo de injeção de dependência e injetado no ProductRepository*/
@Service
public class ProductService {
	
	/*O @Autowired estabelece que a classe ProductService possui uma dependência com ProductRepository
	e faz a injeção de dependência de forma transparente*/
	@Autowired
	private ProductRepository repository;
	
	/*Método que retorna todos os produtos do banco de dados*/
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	/*Método que retorna o produto do banco de dados com base no id*/
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}

}
