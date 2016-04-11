package pl.vgtworld.budget.app.product.pricehistory;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProductPriceHistoryRepository {

	@PersistenceContext
	private EntityManager em;

	public List<ProductPriceHistoryDto> listPriceHistoryForProduct(int productId, Integer limit) {
		Query query = em.createNativeQuery(ProductPriceHistoryDto.NATIVE_QUERY, ProductPriceHistoryDto.RESULT_SET_MAPPING_NAME);
		query.setParameter(1, productId);
		if (limit != null && limit > 0) {
			query.setMaxResults(limit);
		}
		return PersistenceUtil.getResultList(query);
	}
}
