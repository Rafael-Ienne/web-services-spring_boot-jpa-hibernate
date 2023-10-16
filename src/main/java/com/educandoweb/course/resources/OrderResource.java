package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

/*O RestController indica que OrderResource é um recurso web que é implementado por um controlador rest */
@RestController
/*O ResquestMapping dá nome para o recurso(caminho) e por onde ocorre a resposta*/
@RequestMapping(value = "/orders")
/*A classe OrderResource disponibiliza recurso web para a entidade Order*/
public class OrderResource {
	
	/*Dependência pelo serviço do Order*/
	@Autowired
	private OrderService service;

	/*O GetMapping indica que é um método que responde a requisições get do http*/
	@GetMapping
	/*O método findAll é um end-point para acessar todos os pedidos*/
	/*O retorno ResponseEntity é um tipo específico do Spring para retornar respostas de requisições web*/
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		/*o ok retorna a resposta com sucesso e o .body retorna o corpo da resposta*/
		return ResponseEntity.ok().body(list);
	}
	
	/*O GetMapping indica que é um método que responde a requisições get do http*/
	/*O (value = "/{id}") indica que o componente final do endereço passado na ferramenta de teste de solicitações
	 será usado como id para fazer a requisição*/
	@GetMapping(value = "/{id}")
	/*O método findById é um end-point para acessar uma pedido com base no Id*/
	/*O @PathVariable Long id permite que o valor passado no endereço seja usado como id*/
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
