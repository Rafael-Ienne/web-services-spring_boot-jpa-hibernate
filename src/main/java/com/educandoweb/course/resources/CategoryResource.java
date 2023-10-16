package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

/*O RestController indica que CategoryResource é um recurso web que é implementado por um controlador rest */
@RestController
/*O ResquestMapping dá nome para o recurso(caminho) e por onde ocorre a resposta*/
@RequestMapping(value = "/categories")
/*A classe CategoryResource disponibiliza recursos web para a entidade Category*/
public class CategoryResource {
	
	/*Dependência pelo serviço do Category*/
	@Autowired
	private CategoryService service;

	/*O GetMapping indica que é um método que responde a requisições get do http*/
	@GetMapping
	/*O método findAll é um end-point para acessar as categorias*/
	/*O retorno ResponseEntity é um tipo específico do Spring para retornar respostas de requisições web*/
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		/*o ok retorna a resposta com sucesso e o .body retorna o corpo da resposta*/
		return ResponseEntity.ok().body(list);
	}
	
	/*O GetMapping indica que é um método que responde a requisições get do http*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@GetMapping(value = "/{id}")
	/*O método findById é um end-point para acessar uma categoria com base no Id*/
	/*O @PathVariable Long id permite que o valor passado no endereço seja usado como id*/
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
