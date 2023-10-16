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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*Annotations do JPA: instruem o JPA como ele vai transformar os objetos para o modelo de banco de dados relacional*/
@Entity
/* O @Table especifica o nome da tabela, pois User é uma palavra reservada do H2*/
@Table(name = "tb_product")
/*O Serializable permite que os objetos sejam transformados em cadeias de bytes e, assim, trafeguem na rede,
sejam gravados em arquivo, etc*/
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*O @Id indica qual atributo é a chave-primária do Banco de Dados H2*/
	@Id
	/*O @GeneratedValue permite a auto-incrementação da chave primária*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	/*O @ManyToMany indica que um produto pode se associar a várias categorias e vice-versa*/
	@ManyToMany
	/*O @JoinTable gera um tabela de associação entre Product e Category e o @JoinTable(name = "tb_product_category"
	 indica o nome dessa tabela*/
	@JoinTable(name = "tb_product_category",
	/*O @JoinColumn(name = "product_id") indica o nome da coluna de chave estrangeira com o id de produto*/
	joinColumns = @JoinColumn(name = "product_id"),
	/*O inverseJoinColumns = @JoinColumn(name = "category_id") indica o nome coluna de chave estrangeira com o id de category
	  categoria*/
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	/*O Set impede a ocorrência de categorias repetidas*/
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {
		
	}
	
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	/*O @JsonIgnore impede que ao chamar um produto sejam chamados diversos pedidos*/
	@JsonIgnore
	public Set<Order> getOrders(){
	    Set<Order> set = new HashSet<>();
	    for(OrderItem x : items) {
	    	set.add(x.getOrder());
	    }
	    return set;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
