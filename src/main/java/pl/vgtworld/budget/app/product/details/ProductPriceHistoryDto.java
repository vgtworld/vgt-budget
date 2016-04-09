package pl.vgtworld.budget.app.product.details;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@SqlResultSetMapping(
		name = ProductPriceHistoryDto.RESULT_SET_MAPPING_NAME,
		classes = @ConstructorResult(
				targetClass = ProductPriceHistoryDto.class,
				columns = {
						@ColumnResult(name = "purchase_date", type = Date.class),
						@ColumnResult(name = "store_id", type = Integer.class),
						@ColumnResult(name = "store_name", type = String.class),
						@ColumnResult(name = "price", type = BigDecimal.class)
				}
		)
)
public class ProductPriceHistoryDto {

	static final String RESULT_SET_MAPPING_NAME = "ProductPriceHistoryDtoMapping";

	static final String NATIVE_QUERY = "SELECT r.purchase_date, r.store_id, s.name AS store_name, rp.price_per_unit AS price " +
			"FROM receipt_products rp " +
			"INNER JOIN receipts r ON rp.receipt_id = r.id " +
			"INNER JOIN stores s ON r.store_id = s.id " +
			"WHERE rp.product_id = ?1 " +
			"ORDER BY r.purchase_date DESC, r.id DESC";

	private Date purchaseDate;

	private Integer storeId;

	private String storeName;

	private BigDecimal price;

	public ProductPriceHistoryDto(Date purchaseDate, Integer storeId, String storeName, BigDecimal price) {
		this.purchaseDate = purchaseDate;
		this.storeId = storeId;
		this.storeName = storeName;
		this.price = price;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public BigDecimal getPrice() {
		return price;
	}

}
