package pl.vgtworld.budget.app.receipt;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ReceiptRepository {

	@PersistenceContext
	private EntityManager em;

	public List<ReceiptWithStoreDto> listNewestReceipts(Integer offset, Integer limit) {
		Query query = em.createNativeQuery(ReceiptWithStoreDto.NATIVE_QUERY_LIST_NEWEST, ReceiptWithStoreDto.RESULT_SET_MAPPING_NAME);
		if (offset != null) {
			query.setFirstResult(offset);
		}
		if (limit != null) {
			query.setMaxResults(limit);
		}
		return PersistenceUtil.getResultList(query);
	}

	public List<ReceiptWithStoreDto> listDeletedReceipts() {
		Query query = em.createNativeQuery(ReceiptWithStoreDto.NATIVE_QUERY_LIST_DELETED, ReceiptWithStoreDto.RESULT_SET_MAPPING_NAME);
		return PersistenceUtil.getResultList(query);
	}

}
