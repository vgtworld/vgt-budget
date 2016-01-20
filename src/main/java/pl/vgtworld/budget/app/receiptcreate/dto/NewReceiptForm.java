package pl.vgtworld.budget.app.receiptcreate.dto;

import java.util.List;

public class NewReceiptForm {

	private ReceiptStore store;

	private List<ReceiptProduct> products;

	public ReceiptStore getStore() {
		return store;
	}

	public void setStore(ReceiptStore store) {
		this.store = store;
	}

	public List<ReceiptProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ReceiptProduct> products) {
		this.products = products;
	}

}
