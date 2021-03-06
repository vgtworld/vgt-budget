package pl.vgtworld.budget.app.product.edit.dto;

import java.util.List;

public class ProductWithTags {

	private String name;

	private List<String> tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "NewProductWithTags{" +
			  "name='" + name + '\'' +
			  ", tags=" + tags +
			  '}';
	}

}
