package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

/*O OrderRepository é uma interface e irá fazer operações com a entidade Order. Para criar, é preciso 
 passar o tipo de entidade(Order) que será acessada e o tipo da chave(Long)*/
public interface OrderRepository extends JpaRepository<Order, Long> {

}
