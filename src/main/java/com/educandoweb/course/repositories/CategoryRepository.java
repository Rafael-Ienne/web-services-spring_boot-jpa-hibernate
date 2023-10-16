package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Category;

/*O CategoryRepository é uma interface e irá fazer operações com a entidade Category. Para criar,
 é preciso passar o tipo de entidade(Category) que será acessada e o tipo da chave(Long)*/
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
