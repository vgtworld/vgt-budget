package pl.vgtworld.budget.storage.receiptproducts;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ReceiptProductDao {

	@PersistenceContext
	private EntityManager em;

	public void create(ReceiptProduct receiptProduct) {
		em.persist(receiptProduct);
	}

	public ReceiptProduct findById(int receiptProductId) {
		return em.find(ReceiptProduct.class, receiptProductId);
	}

	public List<ReceiptProduct> findForReceipt(int receiptId) {
		Query query = em.createNamedQuery(ReceiptProduct.QUERY_FIND_FOR_RECEIPT);
		query.setParameter("RECEIPT_ID", receiptId);
		return PersistenceUtil.getResultList(query);
	}

	public void delete(int receiptProductId) {
		ReceiptProduct entity = findById(receiptProductId);
		if (entity != null) {
			em.remove(entity);
		}
	}

}
