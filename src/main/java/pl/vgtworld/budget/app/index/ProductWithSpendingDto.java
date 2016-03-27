package pl.vgtworld.budget.app.index;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.math.RoundingMode;

@MappedSuperclass
@SqlResultSetMapping(
	  name = ProductWithSpendingDto.RESULT_SET_MAPPING_NAME,
	  classes = @ConstructorResult(
			targetClass = ProductWithSpendingDto.class,
			columns = {
				  @ColumnResult(name = "id", type = Integer.class),
				  @ColumnResult(name = "product_name", type = String.class),
				  @ColumnResult(name = "total_amount", type = BigDecimal.class)
			}
	  )
)
public class ProductWithSpendingDto {

	static final String NATIVE_QUERY_LIST_PRODUCTS =
		  "SELECT p.id, p.name AS product_name, sum(rp.amount * rp.price_per_unit) as total_amount " +
				"FROM receipt_products rp " +
				"INNER JOIN receipts r ON rp.receipt_id = r.id " +
				"INNER JOIN products p ON rp.product_id = p.id " +
				"WHERE r.purchase_date >= ?1 " +
				"AND r.purchase_date <= ?2 " +
				"AND r.deleted = false " +
				"GROUP BY rp.product_id " +
				"ORDER BY total_amount DESC";

	static final String RESULT_SET_MAPPING_NAME = "ProductWIthSpendingDtoMapping";

	private Integer id;

	private String productName;

	private BigDecimal totalAmount;

	public ProductWithSpendingDto(Integer id, String productName, BigDecimal totalAmount) {
		this.id = id;
		this.productName = productName;
		this.totalAmount = totalAmount.setScale(2, RoundingMode.HALF_UP);
	}

	public Integer getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

}
