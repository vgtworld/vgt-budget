package pl.vgtworld.budget.app.tag.monthlycost;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;

@MappedSuperclass
@SqlResultSetMapping(
		name = TagWithSpendingDto.RESULT_SET_MAPPING_NAME,
		classes = @ConstructorResult(
				targetClass = TagWithSpendingDto.class,
				columns = {
						@ColumnResult(name = "id", type = Integer.class),
						@ColumnResult(name = "tag_name", type = String.class),
						@ColumnResult(name = "total_amount", type = BigDecimal.class)
				}
		)
)
public class TagWithSpendingDto {

	static final String NATIVE_QUERY_LIST_TAGS =
			"SELECT t.id, t.name AS tag_name, sum(rp.amount * rp.price_per_unit) AS total_amount " +
					"FROM receipt_products rp " +
					"INNER JOIN receipts r ON rp.receipt_id = r.id " +
					"INNER JOIN products p ON rp.product_id = p.id " +
					"INNER JOIN product_tags pt ON pt.product_id = p.id " +
					"INNER JOIN tags t ON pt.tag_id = t.id " +
					"WHERE r.purchase_date >= ?1 " +
					"AND r.purchase_date <= ?2 " +
					"AND r.deleted = false " +
					"GROUP BY pt.tag_id " +
					"ORDER BY total_amount DESC";

	static final String RESULT_SET_MAPPING_NAME = "TagWithSpendingDtoMapping";

	private Integer id;

	private String tagName;

	private BigDecimal totalAmount;

	public TagWithSpendingDto(Integer id, String tagName, BigDecimal totalAmount) {
		this.id = id;
		this.tagName = tagName;
		this.totalAmount = totalAmount;
	}

	public Integer getId() {
		return id;
	}

	public String getTagName() {
		return tagName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

}
