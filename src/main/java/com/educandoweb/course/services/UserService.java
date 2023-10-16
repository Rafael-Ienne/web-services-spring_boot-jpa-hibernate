package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/*O @Service registra um serviço na camada de serviço e, dessa forma, permite que o objeto seja
 registrado no mecanismo de injeção de dependência e injetado no UserRepository*/
@Service
public class UserService {
	
	/*Estabelece que a classe UserService possui uma dependência com UserRepository e faz a injeção de dependência
	 de forma transparente*/
	@Autowired
	private UserRepository repository;
	
	/*Método que retorna todos os usuários do banco de dados*/
	public List<User> findAll(){
		return repository.findAll();
	}
	
	/*Método que retorna o usuário do banco de dados com base no id*/
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		/*O orElseThrow indica que a vai haver a tentativa de fazer a requisição. Se não der certo, será lançada uma 
		 ResourceNotFoundException*/
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	/*Método que insere um novo User*/
	public User insert(User obj) {
		/*O save() retorna o objeto salvo*/
		return repository.save(obj);
	}
	
	/*Método que deleta um usuário com base no id*/
	public void delete(Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				/*A DataBaseException é lançada quando tem um objeto relacionado a outro e tenta deletar um deles*/
				throw new DataBaseException(e.getMessage());
			}
		} else {
			/*Se o usuário não existir, será lançado um erro 404*/
			throw new ResourceNotFoundException(id);
		}
	}
	
	/*Método que atualiza usuário*/
	public User update(Long id, User obj) {
		try {
			/*O getReferenceById deixa um objeto monitorado pelo JPA, de forma a ser trabalhado,
			 e, em seguida, pode-se realzar alguma operação com o banco de dados*/
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
			/*O EntityNotFoundException retorna o erro 404, que, no http, é não encontrado*/
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}

}
