package pl.vgtworld.budget.app.product.list.dto;

import pl.vgtworld.budget.services.dto.products.ProductDto;
import pl.vgtworld.budget.services.dto.tags.TagDto;

import java.util.List;

public class ProductWithTags {

	private ProductDto product;

	private List<TagDto> tags;

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

}
