package pl.vgtworld.budget.storage.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@NamedQueries({
	  @NamedQuery(
			name = Product.QUERY_LIST_ALL,
			query = "SELECT p FROM Product p ORDER BY p.name ASC"
	  )
})
public class Product {

	static final String QUERY_LIST_ALL = "Product.listAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 100, unique = true)
	private String name;

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

}
