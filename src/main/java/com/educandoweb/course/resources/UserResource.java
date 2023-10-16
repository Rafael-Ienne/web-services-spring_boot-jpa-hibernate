package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

/*O RestController indica que UserResource é um recurso web que é implementado por um controlador rest */
@RestController
/*O ResquestMapping dá nome para o recurso(caminho) e por onde ocorre a resposta*/
@RequestMapping(value = "/users")
/*A classe UserResource disponibiliza recurso web para a entidade User (é um controlador rest)*/
public class UserResource {
	
	/*Dependência pelo serviço do User*/
	@Autowired
	private UserService service;

	/*O GetMapping indica que é um método que responde a requisições get do http*/
	@GetMapping
	/*O método findAll é um end-point para acessar os usuários*/
	/*O retorno ResponseEntity é um tipo específico do Spring para retornar respostas de requisições web*/
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		/*o ok retorna a resposta com sucesso e o .body retorna o corpo da resposta*/
		return ResponseEntity.ok().body(list);
	}
	
	/*O GetMapping indica que é um método que responde a requisições get do http*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@GetMapping(value = "/{id}")
	/*O método findById é um end-point para acessar um usuário com base no Id*/
	/*O @PathVariable Long id permite que o valor passado no endereço seja usado como id*/
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*O @PostMapping indica que será usado o método http post para adicionar um usuário*/
	@PostMapping
	/*O método insert insere um user no banco de dados*/
	/*O @RequestBody mostra que o objeto User vai chegar na forma de json e será desserializado na forma de objeto
	 User no Java*/
	public ResponseEntity <User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		/*Esse objeto do tipo URI é criado porque, ao se inserir um novo dado, é mais adequado retornar o código de
		 resposta 201 (código específico do http que significa que um novo recurso foi criado*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri(); 
		/*O created() espera um objeto do tipo URI, porque o padrão http, ao retornar um objeto 201, é esperado que a
		 resposta tenha um cabeçalho (location) com o endereço do novo recurso inserido*/
		return ResponseEntity.created(uri).body(obj);
	}
	
	/*O @DeleteMapping indica que será usado o método http delete para deletar um usuário*/
	/*End point para deletar um usuário com base no id*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@DeleteMapping(value = "/{id}")
	/*Usa-se void em ResponseEntity porque a resposta não vai retornar nenhum corpo*/
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*End-point para atualizar um usuário com base no id. O @PutMapping indica que será 
	 usado o método http post para atualizar dados*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
