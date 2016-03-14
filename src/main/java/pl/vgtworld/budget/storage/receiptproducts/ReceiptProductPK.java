package pl.vgtworld.budget.storage.receiptproducts;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReceiptProductPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "receipt_id", nullable = false)
	private Integer receiptId;

	@Column(name = "product_id", nullable = false)
	private Integer productId;

	public ReceiptProductPK() {
	}

	public ReceiptProductPK(Integer receiptId, Integer productId) {
		this.receiptId = receiptId;
		this.productId = productId;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ReceiptProductPK that = (ReceiptProductPK) o;

		if (receiptId != null ? !receiptId.equals(that.receiptId) : that.receiptId != null) {
			return false;
		}
		return !(productId != null ? !productId.equals(that.productId) : that.productId != null);

	}

	@Override
	public int hashCode() {
		int result = receiptId != null ? receiptId.hashCode() : 0;
		result = 31 * result + (productId != null ? productId.hashCode() : 0);
		return result;
	}

}
