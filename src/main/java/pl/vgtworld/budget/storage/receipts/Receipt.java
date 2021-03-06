package pl.vgtworld.budget.storage.receipts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "receipts")
@NamedQueries({
	  @NamedQuery(
			name = Receipt.QUERY_REMOVE_MARKED_AS_DELETED,
			query = "DELETE FROM Receipt r WHERE r.deleted = true"
	  ),
	  @NamedQuery(
			name = Receipt.QUERY_TOTAL_AMOUNT_SUM_DATE_RANGE,
			query = "SELECT sum(r.totalAmount) FROM Receipt r " +
				  "WHERE r.purchaseDate >= :DATE_FROM AND r.purchaseDate <= :DATE_TO AND r.deleted = false"
	  ),
	  @NamedQuery(
			name = Receipt.QUERY_COUNT_NOT_DELETED,
			query = "SELECT count(r) FROM Receipt r WHERE r.deleted = false"
	  )
})
public class Receipt {

	static final String QUERY_REMOVE_MARKED_AS_DELETED = "Receipt.removeMarkedAsDeleted";

	static final String QUERY_TOTAL_AMOUNT_SUM_DATE_RANGE = "Receipt.totalAmountSumDateRange";

	static final String QUERY_COUNT_NOT_DELETED = "Receipt.countNotDeleted";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "store_id", nullable = false)
	private Integer storeId;

	@Column(name = "purchase_date")
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@Column(name = "created_at")
	private Date createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
