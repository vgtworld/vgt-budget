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

	public List<Receipt> listNewest() {
		Query query = em.createNamedQuery(Receipt.QUERY_LIST_NEWEST);
		return PersistenceUtil.getResultList(query);
	}

}
