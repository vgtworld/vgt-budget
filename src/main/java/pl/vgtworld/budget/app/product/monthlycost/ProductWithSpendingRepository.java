package pl.vgtworld.budget.app.product.monthlycost;

import pl.vgtworld.budget.core.utils.PersistenceUtil;
import pl.vgtworld.budget.core.utils.calendar.DateRangeUtil;
import pl.vgtworld.budget.core.utils.calendar.MonthRangeDto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;

@Stateless
public class ProductWithSpendingRepository {

	@PersistenceContext
	private EntityManager em;

	public List<ProductWithSpendingDto> listProductsWithBiggestSpendingInCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return listProductsWithBiggestSpending(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 10);
	}

	public List<ProductWithSpendingDto> listProductsWithBiggestSpending(int year, int month, Integer limit) {
		MonthRangeDto monthRange = DateRangeUtil.findFirstAndLastDayOfMonth(year, month);
		Query query = em.createNativeQuery(ProductWithSpendingDto.NATIVE_QUERY_LIST_PRODUCTS, ProductWithSpendingDto.RESULT_SET_MAPPING_NAME);
		query.setParameter(1, monthRange.getStartDate());
		query.setParameter(2, monthRange.getEndDate());
		if (limit != null && limit > 0) {
			query.setMaxResults(limit);
		}
		return PersistenceUtil.getResultList(query);
	}

}
