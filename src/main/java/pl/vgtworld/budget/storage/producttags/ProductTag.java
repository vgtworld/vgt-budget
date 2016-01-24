package pl.vgtworld.budget.storage.producttags;


import pl.vgtworld.budget.storage.products.Product;
import pl.vgtworld.budget.storage.tags.Tag;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_tags")
public class ProductTag {

	@EmbeddedId
	private ProductTagPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id", insertable = false, updatable = false)
	private Tag tag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	public ProductTagPK getId() {
		return id;
	}

	public void setId(ProductTagPK id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
