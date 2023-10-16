package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*Annotations do JPA: instruem o JPA como ele vai transformar os objetos para o modelo de banco de dados relacional*/
@Entity
/* O @Table especifica o nome da tabela, que não deve entrar em conflito com uma palavra reservada*/
@Table(name = "tb_payment")
/*O Serializable permite que os objetos sejam transformados em cadeias de bytes e, assim, trafeguem na rede,
sejam gravados em arquivo, etc*/
public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*O @Id indica qual atributo é a chave-primária do Banco de Dados H2*/
	@Id
	/*O @GeneratedValue permite a auto-incrementação da chave primária*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment;
	
	/*O @JsonIgnore impede o loop de chamadas infinita entre pedido e pagamento*/
	@JsonIgnore
	/*O @OneToOne indica que um pagamento está associado a um pedido (a classe Paymet é dependente) e vice-versa*/
	@OneToOne
	@MapsId
	private Order order;
	
	public Payment() {
		
	}

	public Payment(Long id, Instant moment, Order order) {
		super();
		this.id = id;
		this.moment = moment;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
	
}
