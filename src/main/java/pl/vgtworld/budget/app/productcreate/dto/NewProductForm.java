package pl.vgtworld.budget.app.productcreate.dto;

public class NewProductForm {

	private String name;

	private String tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "NewProductForm{" +
			  "name='" + name + '\'' +
			  ", tags='" + tags + '\'' +
			  '}';
	}

}
