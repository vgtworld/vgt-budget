package pl.vgtworld.budget.storage.receiptproducts;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReceiptProductDao {

	@PersistenceContext
	private EntityManager em;

	public void create(ReceiptProduct receiptProduct) {
		em.persist(receiptProduct);
	}

}
