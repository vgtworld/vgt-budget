package pl.vgtworld.budget.app.receiptedit.dto;

import java.util.Date;

public class ReceiptForm {

	private Integer storeId;

	private Date purchaseDate;

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

	@Override
	public String toString() {
		return "ReceiptForm{" +
			  "storeId='" + storeId + '\'' +
			  ", purchaseDate=" + purchaseDate +
			  '}';
	}

}
