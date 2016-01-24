package pl.vgtworld.budget.services.dto.tags;

public class NewTag {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NewTag{" +
			  "name='" + name + '\'' +
			  '}';
	}

}
