package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;

/*O OrderItemRepository é uma interface e irá fazer operações com a entidade OrderItem. Para criar,
 é preciso passar o tipo de entidade(OrderItem) que será acessada e o tipo da chave(Long)*/
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
