package pl.vgtworld.budget.app.store.edit.dto;

public class StoreForm {

	private String storeName;

	private String storeCity;

	private String storeAddress;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreCity() {
		return storeCity;
	}

	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	@Override
	public String toString() {
		return "NewStoreForm{" +
			  "storeName='" + storeName + '\'' +
			  ", storeCity='" + storeCity + '\'' +
			  ", storeAddress='" + storeAddress + '\'' +
			  '}';
	}

}
