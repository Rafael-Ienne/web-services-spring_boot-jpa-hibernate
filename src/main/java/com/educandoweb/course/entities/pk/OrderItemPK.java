package com.educandoweb.course.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*O @Embeddable mostra que a classe é uma chave primária composta*/
@Embeddable
/*A classe OrderItemPK é uma classe auxiliar para representar o par produto-pedido, pois não existe chave primária
 composta no paradigma orientado a objetos*/
/*O Serializable permite que os objetos sejam transformados em cadeias de bytes e, assim, trafeguem na rede,
sejam gravados em arquivo, etc*/
public class OrderItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*O @ManyToOne indica que um OrderItemPK está associado a um pedido e um pedido pode estar associado a vários 
	OrderItemPK*/
	@ManyToOne
	/*O @JoinColumn(name = "order_id") indica o nome da coluna de chave estrangeira que conterá o id de order
	 na tabela order_item de banco de dados relacional*/
	@JoinColumn(name = "order_id")
	private Order order;
	/*O @ManyToOne indica que um OrderItemPK está associado a um roduto e um produto pode estar associado a vários 
	OrderItemPK*/
	@ManyToOne
	/*O @JoinColumn(name = "product_id") indica o nome da coluna de chave estrangeira que conterá o id de product
	 na tabela order_item de banco de dados relacional*/
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/*O hashcode e o equals compara os items de pedido com base no produto e no pedido*/
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
}
