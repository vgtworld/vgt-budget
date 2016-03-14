package pl.vgtworld.budget.app.receiptproductedit.dto;

import java.math.BigDecimal;

public class ReceiptProductForm {

	private BigDecimal amount;

	private BigDecimal unitPrice;

	private String description;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ReceiptProductForm{" +
			  "amount=" + amount +
			  ", unitPrice=" + unitPrice +
			  ", description='" + description + '\'' +
			  '}';
	}

}
