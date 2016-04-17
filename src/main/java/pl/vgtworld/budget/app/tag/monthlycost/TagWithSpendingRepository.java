package pl.vgtworld.budget.app.tag.monthlycost;

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
public class TagWithSpendingRepository {

	@PersistenceContext
	private EntityManager em;

	public List<TagWithSpendingDto> listTagsWithBiggestSpendingInCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return listTagsWithBiggestSpending(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 10);
	}

	public List<TagWithSpendingDto> listTagsWithBiggestSpending(int year, int month, Integer limit) {
		MonthRangeDto monthRange = DateRangeUtil.findFirstAndLastDayOfMonth(year, month);
		Query query = em.createNativeQuery(TagWithSpendingDto.NATIVE_QUERY_LIST_TAGS, TagWithSpendingDto.RESULT_SET_MAPPING_NAME);
		query.setParameter(1, monthRange.getStartDate());
		query.setParameter(2, monthRange.getEndDate());
		if (limit != null && limit > 0) {
			query.setMaxResults(limit);
		}
		return PersistenceUtil.getResultList(query);
	}

}
