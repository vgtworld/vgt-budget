package pl.vgtworld.budget.storage.producttags;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_tags")
public class ProductTag {

	@EmbeddedId
	private ProductTagPK id;

	public ProductTagPK getId() {
		return id;
	}

	public void setId(ProductTagPK id) {
		this.id = id;
	}

}
