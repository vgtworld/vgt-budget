package pl.vgtworld.budget.storage.receipts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

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

	public BigDecimal getTotalAmountSum(Date from, Date to) {
		Query query = em.createNamedQuery(Receipt.QUERY_TOTAL_AMOUNT_SUM_DATE_RANGE);
		query.setParameter("DATE_FROM", from);
		query.setParameter("DATE_TO", to);
		BigDecimal result = (BigDecimal) query.getSingleResult();
		if (result == null) {
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}
		return result;
	}

	public void removeMarkedAsDeleted() {
		Query query = em.createNamedQuery(Receipt.QUERY_REMOVE_MARKED_AS_DELETED);
		query.executeUpdate();
	}

	public long countNotDeleted() {
		Query query = em.createNamedQuery(Receipt.QUERY_COUNT_NOT_DELETED);
		return (Long)query.getSingleResult();
	}

}
