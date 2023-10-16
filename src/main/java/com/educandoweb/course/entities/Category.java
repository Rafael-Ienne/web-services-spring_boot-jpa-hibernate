package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/*Annotations do JPA: instruem o JPA como ele vai transformar os objetos para o modelo de banco de dados relacional*/
@Entity
/* O @Table especifica o nome da tabela, que não deve entrar em conflito com uma palavra reservada*/
@Table(name = "tb_category")
/*O Serializable permite que os objetos sejam transformados em cadeias de bytes e, assim, trafeguem na rede,
sejam gravados em arquivo, etc*/
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*O @Id indica qual atributo é a chave-primária do banco de dados H2*/
	@Id
	/*O @GeneratedValue permite a auto-incrementação da chave primária*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	/*O @JsonIgnore não mostra os produtos aos quais as categorias estão relacionadas*/
	@JsonIgnore
	/*O @ManyToMany indica que uma categoria podem ser associadas a diversos produtos e vice-versa*/
	/*O mappedBy = "categories" indica que os products são mapeados pela coleção categories da classe Product*/
	@ManyToMany(mappedBy = "categories")
	/*O Set impede a repetição de produtos*/
	private Set<Product> products = new HashSet<>();
	
	public Category() {
		
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*Só se faz get quando se trata de coleções*/
	public Set<Product> getProducts() {
		return products;
	}

	/*O hashcode e o equals são gerados com base no id*/
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

}
