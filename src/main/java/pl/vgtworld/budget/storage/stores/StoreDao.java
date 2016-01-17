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

	public List<Store> listAll() {
		Query query = em.createNamedQuery(Store.QUERY_LIST_ALL);
		return PersistenceUtil.getResultList(query);
	}

}