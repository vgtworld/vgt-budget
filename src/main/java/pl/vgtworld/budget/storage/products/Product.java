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

	static final String NATIVE_QUERY_RECENTLY_BOUGHT_IN_STORE = "SELECT DISTINCT * FROM " +
		  "( " +
		  "SELECT p.* " +
		  "FROM receipt_products rp " +
		  "INNER JOIN receipts r ON r.id = rp.receipt_id " +
		  "INNER JOIN products p ON p.id = rp.product_id " +
		  "WHERE r.store_id = ?1 AND r.deleted = false AND r.id != ?2 " +
		  "ORDER BY r.purchase_date DESC, rp.id DESC " +
		  ") temp " +
		  "ORDER BY name ASC";

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
