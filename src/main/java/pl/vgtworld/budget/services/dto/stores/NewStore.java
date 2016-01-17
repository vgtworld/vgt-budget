package pl.vgtworld.budget.services.dto.stores;

public class NewStore {

	private String name;

	private String city;

	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "NewStore{" +
			  "name='" + name + '\'' +
			  ", city='" + city + '\'' +
			  ", address='" + address + '\'' +
			  '}';
	}

}
