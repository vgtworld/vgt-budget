package pl.vgtworld.budget.storage.stores;

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
@Table(name = "stores")
@NamedQueries({
	  @NamedQuery(
			name = Store.QUERY_LIST_ALL,
			query = "SELECT s FROM Store s WHERE s.deleted = FALSE ORDER BY s.name ASC"
	  )
})
public class Store {

	static final String QUERY_LIST_ALL = "Store.listAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@Column(name = "address", nullable = false, length = 200)
	private String address;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
