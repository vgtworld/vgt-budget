package pl.vgtworld.budget.app.receiptcreate.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewReceiptForm {

	private ReceiptStore store;

	private List<ReceiptProduct> products = new ArrayList<>();

	private Date purchaseDate;

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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getTotalAmount() {
		BigDecimal totalAmount = new BigDecimal(0);
		for (ReceiptProduct product : products) {
			totalAmount = totalAmount.add(BigDecimal.valueOf(product.getTotalPrice()));
		}
		totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return totalAmount.doubleValue();
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
