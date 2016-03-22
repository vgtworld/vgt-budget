package pl.vgtworld.budget.storage.receipts;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@SqlResultSetMapping(
	  name = ReceiptWithStoreDto.RESULT_SET_MAPPING_NAME,
	  classes = @ConstructorResult(
			targetClass = ReceiptWithStoreDto.class,
			columns = {
				  @ColumnResult(name = "id", type = Integer.class),
				  @ColumnResult(name = "store_name", type = String.class),
				  @ColumnResult(name = "purchase_date", type = Date.class),
				  @ColumnResult(name = "total_amount", type = BigDecimal.class)
			}
	  )
)
public class ReceiptWithStoreDto {

	static final String RESULT_SET_MAPPING_NAME = "ReceiptWithStoreDtoMapping";

	private Integer id;

	private String store;

	private Date purchaseDate;

	private BigDecimal totalAmount;

	public ReceiptWithStoreDto(Integer id, String store, Date purchaseDate, BigDecimal totalAmount) {
		this.id = id;
		this.store = store;
		this.purchaseDate = purchaseDate;
		this.totalAmount = totalAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
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

}
