package pl.vgtworld.budget.storage.receiptproducts;

import pl.vgtworld.budget.storage.products.Product;
import pl.vgtworld.budget.storage.receipts.Receipt;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "receipt_products")
@NamedQueries({
	  @NamedQuery(
			name = ReceiptProduct.QUERY_FIND_FOR_RECEIPT,
			query = "SELECT rp FROM ReceiptProduct rp WHERE rp.id.receiptId = :RECEIPT_ID"
	  )
})
public class ReceiptProduct {

	static final String QUERY_FIND_FOR_RECEIPT = "ReceiptProduct.findForReceipt";

	@EmbeddedId
	private ReceiptProductPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receipt_id", insertable = false, updatable = false)
	private Receipt receipt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "price_per_unit", nullable = false)
	private BigDecimal pricePerUnit;

	@Column(name = "description")
	private String description;

	public ReceiptProductPK getId() {
		return id;
	}

	public void setId(ReceiptProductPK id) {
		this.id = id;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
