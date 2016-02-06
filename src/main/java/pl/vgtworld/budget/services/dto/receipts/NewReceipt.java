package pl.vgtworld.budget.services.dto.receipts;

import java.util.Date;

public class NewReceipt {

	private Integer storeId;

	private Date purchaseDate;

	private Double totalAmount;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "NewReceipt{" +
			  "storeId=" + storeId +
			  ", purchaseDate=" + purchaseDate +
			  ", totalAmount=" + totalAmount +
			  '}';
	}

}
