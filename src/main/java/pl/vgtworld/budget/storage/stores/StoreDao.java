package pl.vgtworld.budget.storage.stores;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StoreDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Store store) {
		em.persist(store);
		return store.getId();
	}

	public Store findById(int id) {
		return em.find(Store.class, id);
	}

	public List<Store> listAvailable() {
		Query query = em.createNamedQuery(Store.QUERY_LIST_AVAILABLE);
		return PersistenceUtil.getResultList(query);
	}

}
