package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

/*O UserRepository é uma interface e irá fazer operações com a entidade User. Para criar, é preciso
 passar o tipo de entidade(User) que será acessada e o tipo da chave(Long)*/
public interface UserRepository extends JpaRepository<User, Long> {

}
