package pl.vgtworld.budget.app.receiptcreate.dto;

import java.util.ArrayList;
import java.util.List;

public class NewReceiptForm {

	private ReceiptStore store;

	private List<ReceiptProduct> products = new ArrayList<>();

	private String purchaseDate;

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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getTotalAmount() {
		double totalAmount = 0.0;
		for (ReceiptProduct product : products) {
			totalAmount += product.getTotalPrice();
		}
		return totalAmount;
	}

	@Override
	public String toString() {
		return "NewReceiptForm{" +
			  "store=" + store +
			  ", products=" + products +
			  ", purchaseDate=" + purchaseDate +
			  '}';
	}

}
