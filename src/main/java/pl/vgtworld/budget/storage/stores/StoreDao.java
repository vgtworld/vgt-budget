package pl.vgtworld.budget.storage.stores;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StoreDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Store store) {
		em.persist(store);
		return store.getId();
	}

}
