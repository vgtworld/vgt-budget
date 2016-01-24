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

	public List<Product> listAll() {
		Query query = em.createNamedQuery(Product.QUERY_LIST_ALL);
		return PersistenceUtil.getResultList(query);
	}

}
