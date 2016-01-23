package pl.vgtworld.budget.storage.producttags;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductTagPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "product_id", nullable = false)
	private int productId;

	@Column(name = "tag_id", nullable = false)
	private int tagId;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ProductTagPK that = (ProductTagPK) o;

		return productId == that.productId && tagId == that.tagId;
	}

	@Override
	public int hashCode() {
		int result = productId;
		result = 31 * result + tagId;
		return result;
	}

}
