package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*Annotations do JPA: instruem o JPA como ele vai transformar os objetos para o modelo de banco de dados relacional*/
@Entity
@Table(name = "tb_order_item")
/*A classe OrderItem é uma associação muitos para muitos com atributos extras entre produto e pedido, pois há
 quantidade do produto*/
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*O @EmbeddedId é usado por ser uma chave composta*/
	@EmbeddedId
	/*Todo id composto precisa ser instanciado para depois chamar os gets e sets da classe*/
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	/*O preço é colocado para fins de armazenar um histórico*/
	private Double price;
	
	public OrderItem() {
		
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		/*Os setOrder e setProduct ajudam a compor o id*/
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	/*O getOrder() chama o pedido associado ao item de pedido e o pedido chama o item de pedido e, assim 
	 ficaria em um loop infinito, sendo necessário usar o @JsonIgnore para evitar isso*/
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSubTotal() {
		return getQuantity() * getPrice();
	}

	/*O hashcode e o equals são criados com base apenas no Id*/
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

}
