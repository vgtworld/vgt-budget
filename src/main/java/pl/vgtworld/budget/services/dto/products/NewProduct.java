package pl.vgtworld.budget.services.dto.products;

import java.util.List;

public class NewProduct {

	private String name;

	//TODO Remove, when products CRUD is removed from creating receipt page.
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

}
