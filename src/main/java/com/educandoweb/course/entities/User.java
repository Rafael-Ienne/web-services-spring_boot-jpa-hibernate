package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*Annotations do JPA: instruem o JPA como ele vai transformar os objetos para o modelo de banco de dados relacional*/
@Entity
/* O @Table especifica o nome da tabela, pois User é uma palavra reservada do H2*/
@Table(name = "tb_user")
/*O Serializable permite que os objetos sejam transformados em cadeias de bytes e, assim, trafeguem na rede,
 sejam gravados em arquivo, etc*/
public class User implements Serializable{

	//Número de série padrão do serializable
	private static final long serialVersionUID = 1L;
	
	/*O @Id indica qual atributo é a chave-primária do Banco de Dados H2*/
	@Id
	/*O @GeneratedValue permite a auto-incrementação da chave primária*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	/*O @JsonIgnore impede que o Jackson (biblioteca de serialização) serialize(mostre) os vários pedidos
	 ao qual o User está ancorado*/
	@JsonIgnore
	/*O @OneToManyUm mostra que um usuário possui vários pedidos e um pedido está associado a um usuário*/
	/*O mappedBy indica por qual atributo esse um para muitos é mapeado na classe Order*/
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public User() {
		
	}

	public User(Long i, String name, String email, String phone, String password) {
		super();
		this.id = i;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*Não colocamos o set da lista porque não vamos trocar a lista em nenhum momento*/
	public List<Order> getOrders() {
		return orders;
	}

	/*O hashcode e equals são gerados com base no id*/
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
