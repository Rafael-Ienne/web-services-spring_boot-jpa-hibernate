package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
/*Annotations do JPA: instruem o JPA como ele vai transformar os objetos para o modelo de banco de dados relacional*/
@Entity
/* O @Table especifica o nome da tabela, que não deve entrar em conflito com uma palavra reservada*/
@Table(name = "tb_order")
/*O Serializable permite que os objetos sejam transformados em cadeias de bytes e, assim, trafeguem na rede,
sejam gravados em arquivo, etc*/
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*O @Id indica qual atributo é a chave-primária do Banco de Dados H2*/
	@Id
	/*O @GeneratedValue permite a auto-incrementação da chave primária*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*O @JsonFormat garante que a data seja mostrada em um formato padronizado do ISO 8601*/
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	/*Internamente o orderStatus é tratado como inteiro, mas para o exterior é um OrderStatus*/
	private Integer orderStatus;
	
	/*O @ManyToOne indica que um pedido pode estar associado a um único usuário e um usuário pode estar
	associado a diversos pedidos*/
	@ManyToOne
	/*O @JoinColumn cria a coluna de chave estrangeira na tabela de Order com base no id do atributo User e indica
	o nome da coluna de chave estrangeira*/
	@JoinColumn(name = "client_id")
	private User client;
	
	/*O @OneToMany indica que um pedido pode estar associado a vários items de pedido e um item de pedido
	  associado a um pedido*/
	/*O @OneToMany(mappedBy = "id.order") indica que os items são mapeados pelo order do Id composto do OrderItemPK*/
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	/*O @OneToOne indica que um pedido possui nenhum ou 1 pagamento (a classe Order é a independente) e vice-versa e
	 o mappedBy indica o nome do atributo em Pyment onde o Order foi mapeado*/
	/*O cascade é para que as duas entidades tenham o mesmo id*/
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
		
	}
	
	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		/*O setOrderStatus é usado para retornar um valor do tipo OrderStatus*/
		this.setOrderStatus(orderStatus);
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
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

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	/*Método que retorna o valor total de um pedido*/
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem i : items) {
			sum += i.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	/*Método que converte o valor de inteiro(orderStatus) para OrderStatus*/
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	/*Método que converte o OrderStatus para um valor inteiro*/
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
