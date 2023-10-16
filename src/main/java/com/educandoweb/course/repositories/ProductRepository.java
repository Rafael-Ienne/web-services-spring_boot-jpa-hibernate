package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Product;

/*O ProductRepository é uma interface e irá fazer operações com a entidade Product. Para criar,
 é preciso passar o tipo de entidade(Product) que será acessada e o tipo da chave(Long)*/
public interface ProductRepository extends JpaRepository<Product, Long> {

}
