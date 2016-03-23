package pl.vgtworld.budget.storage.receipts;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ReceiptDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Receipt receipt) {
		em.persist(receipt);
		return receipt.getId();
	}

	public Receipt findById(int id) {
		return em.find(Receipt.class, id);
	}

	public List<ReceiptWithStoreDto> listNewest() {
		Query query = em.createNativeQuery(ReceiptWithStoreDto.NATIVE_QUERY_LIST_NEWEST, ReceiptWithStoreDto.RESULT_SET_MAPPING_NAME);
		return PersistenceUtil.getResultList(query);
	}

	public List<ReceiptWithStoreDto> listDeleted() {
		Query query = em.createNativeQuery(ReceiptWithStoreDto.NATIVE_QUERY_LIST_DELETED, ReceiptWithStoreDto.RESULT_SET_MAPPING_NAME);
		return PersistenceUtil.getResultList(query);
	}

	public void removeMarkedAsDeleted() {
		Query query = em.createNamedQuery(Receipt.QUERY_REMOVE_MARKED_AS_DELETED);
		query.executeUpdate();
	}

}
