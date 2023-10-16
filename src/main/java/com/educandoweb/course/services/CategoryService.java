package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

/*O @Service registra um serviço na camada de serviço e, dessa forma, permite que o objeto seja
registrado no mecanismo de injeção de dependência e injetado no CategoryRepository*/
@Service
public class CategoryService {
	
	/*O @Autowired estabelece que a classe CategoryService possui uma dependência com CategoryRepository e faz
	 a injeção de dependência de forma transparente*/
	@Autowired
	private CategoryRepository repository;
	
	/*Método que retorna todas as categorias do banco de dados*/
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	/*Método que retorna a categoria do banco de dados com base no id*/
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}

}
