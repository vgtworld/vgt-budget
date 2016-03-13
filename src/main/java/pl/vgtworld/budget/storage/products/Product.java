package pl.vgtworld.budget.storage.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "products")
@NamedQueries({
	  @NamedQuery(
			name = Product.QUERY_LIST_ALL,
			query = "SELECT p FROM Product p ORDER BY p.name ASC"
	  ),
	  @NamedQuery(
			name = Product.QUERY_SEARCH_BY_NAME,
			query = "SELECT p FROM Product p WHERE p.name like :PHRASE ORDER BY p.name ASC"
	  )
})
public class Product {

	static final String QUERY_LIST_ALL = "Product.listAvailable";

	static final String QUERY_SEARCH_BY_NAME = "Product.searchByName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 100, unique = true)
	private String name;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
