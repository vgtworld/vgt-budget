package pl.vgtworld.budget.storage.tags;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
@NamedQueries({
	  @NamedQuery(
			name = Tag.QUERY_FIND_BY_NAME,
			query = "SELECT t FROM Tag t WHERE t.name = :NAME"
	  )
})
public class Tag {

	static final String QUERY_FIND_BY_NAME = "Tag.findByName";

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
