package pl.vgtworld.budget.storage.receiptproducts;

import pl.vgtworld.budget.storage.products.Product;
import pl.vgtworld.budget.storage.receipts.Receipt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
			query = "SELECT rp FROM ReceiptProduct rp WHERE rp.receiptId = :RECEIPT_ID ORDER BY rp.id ASC"
	  )
})
public class ReceiptProduct {

	static final String QUERY_FIND_FOR_RECEIPT = "ReceiptProduct.findForReceipt";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "receipt_id", nullable = false)
	private Integer receiptId;

	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "price_per_unit", nullable = false)
	private BigDecimal pricePerUnit;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receipt_id", insertable = false, updatable = false)
	private Receipt receipt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
