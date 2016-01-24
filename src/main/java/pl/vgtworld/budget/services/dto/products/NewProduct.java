package pl.vgtworld.budget.services.dto.products;

public class NewProduct {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NewProduct{" +
			  "name='" + name + '\'' +
			  '}';
	}

}
