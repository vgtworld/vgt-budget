package pl.vgtworld.budget.app.receipt.product.list.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddedProductDto {

	private Integer id;

	private String productName;

	private BigDecimal amount;

	private BigDecimal pricePerUnit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public BigDecimal getTotalPrice() {
		return pricePerUnit.multiply(amount).setScale(2, RoundingMode.HALF_UP);
	}

}
