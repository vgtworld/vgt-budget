package pl.vgtworld.budget.storage.products;

import pl.vgtworld.budget.core.utils.PersistenceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	public int create(Product product) {
		em.persist(product);
		return product.getId();
	}

	public Product findById(int id) {
		return em.find(Product.class, id);
	}

	public List<Product> searchByName(String phrase) {
		Query query = em.createNamedQuery(Product.QUERY_SEARCH_BY_NAME);
		query.setParameter("PHRASE", "%" + phrase + "%");
		return PersistenceUtil.getResultList(query);
	}

	public List<Product> searchRecentlyBoughtInStore(int storeId, int excludedReceiptId) {
		Query query = em.createNativeQuery(Product.NATIVE_QUERY_RECENTLY_BOUGHT_IN_STORE, Product.class);
		query.setParameter(1, storeId);
		query.setParameter(2, excludedReceiptId);
		query.setMaxResults(20);
		return PersistenceUtil.getResultList(query);
	}

	public List<Product> listAll() {
		Query query = em.createNamedQuery(Product.QUERY_LIST_ALL);
		return PersistenceUtil.getResultList(query);
	}

}
