package pl.vgtworld.budget.app.productlist.dto;

import pl.vgtworld.budget.services.dto.products.ProductItem;
import pl.vgtworld.budget.services.dto.tags.TagItem;

import java.util.List;

public class ProductWithTags {

	private ProductItem product;

	private List<TagItem> tags;

	public ProductItem getProduct() {
		return product;
	}

	public void setProduct(ProductItem product) {
		this.product = product;
	}

	public List<TagItem> getTags() {
		return tags;
	}

	public void setTags(List<TagItem> tags) {
		this.tags = tags;
	}

}
